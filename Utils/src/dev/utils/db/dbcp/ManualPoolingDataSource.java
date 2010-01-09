/**
* This file is part of dev.utils.
*
* dev.utils is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* You should have received a copy of the GNU General Public License
* along with dev.utils.  If not, see <http://www.gnu.org/licenses/>.
*
* File      : ManualPoolingDataSource.java
* Created   : Dec 20, 2009, 6:26:23 PM
*/

package dev.utils.db.dbcp;

import dev.utils.resources.PropertiesUtils;
import java.io.IOException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

//
// Here are the dbcp-specific classes.
// Note that they are only used in the setupDataSource
// method. In normal use, your classes interact
// only with the standard JDBC API
//
import java.util.Properties;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;


/**
 * @author Erol Hira
 */
public class ManualPoolingDataSource {

    private String username;
    private String password;
    private String url;             // The connection URL to be passed to our JDBC driver to establish a connection.
    private String driverClassName; // The fully qualified Java class name of the JDBC driver to be used, ex: oracle.jdbc.driver.OracleDriver
    
    private boolean defaultAutoCommit = true;   // The default auto-commit state of connections created by this pool.
    private boolean defaultReadOnly;            // The default read-only state of connections created by this pool. If not set then the setReadOnly method will not be called. (Some drivers don't support read only mode, ex: Informix)

    private int initialSize = 0;    // The initial number of connections that are created when the pool is started. Since: 1.2
    private int maxActive = 8;      // The maximum number of active connections that can be allocated from this pool at the same time, or negative for no limit.
    private int maxIdle = 8;        // The maximum number of connections that can remain idle in the pool, without extra ones being released, or negative for no limit.
    private int minIdle = 0;        // The minimum number of connections that can remain idle in the pool, without extra ones being created, or zero to create none.
    private int maxWait = -1;       // The maximum number of milliseconds that the pool will wait (when there are no available connections) for a connection to be returned before throwing an exception, or -1 to wait indefinitely.

    private String  validationQuery = "SELECT 1";                   // The SQL query that will be used to validate connections from this pool before returning them to the caller. If specified, this query MUST be an SQL SELECT statement that returns at least one row.
    private boolean testOnBorrow = true;                            // The indication of whether objects will be validated before being borrowed from the pool. If the object fails to validate, it will be dropped from the pool, and we will attempt to borrow another. NOTE - for a true value to have any effect, the validationQuery parameter must be set to a non-null string.
    private boolean testOnReturn = false;                           // The indication of whether objects will be validated before being returned to the pool. NOTE - for a true value to have any effect, the validationQuery parameter must be set to a non-null string.
    private boolean testWhileIdle = false;                          // The indication of whether objects will be validated by the idle object evictor (if any). If an object fails to validate, it will be dropped from the pool. NOTE - for a true value to have any effect, the validationQuery parameter must be set to a non-null string.
    private int     timeBetweenEvictionRunsMillis = -1;             // The number of milliseconds to sleep between runs of the idle object evictor thread. When non-positive, no idle object evictor thread will be run. 
    private int     numTestsPerEvictionRun = 3;                     // The number of objects to examine during each run of the idle object evictor thread (if any).
    private long    minEvictableIdleTimeMillis = 1000 * 60 * 30;    // The minimum amount of time an object may sit idle in the pool before it is eligable for eviction by the idle object evictor (if any).
    private String  connectionInitSqls;                             // A Collection of SQL statements that will be used to initialize physical connections when they are first created. These statements are executed only once - when the configured connection factory creates the connection.

    private boolean poolPreparedStatements = false; // Enable prepared statement pooling for this pool.
    private int     maxOpenPreparedStatements = 0;  // The maximum number of open statements that can be allocated from the statement pool at the same time, or zero for no limit.

    private DataSource dataSource;

    private static ManualPoolingDataSource manualPoolingDataSource = new ManualPoolingDataSource();

    private ManualPoolingDataSource(){        
    }

    public static ManualPoolingDataSource getInstance(){
        return manualPoolingDataSource;
    }

    public DataSource getDataSource(){
        return dataSource;
    }

    public void init(String propertiesFileName) throws Exception{

        loadProperties(propertiesFileName);

        Class.forName(driverClassName);

        dataSource = setupDataSource(url);
    }    

    public DataSource setupDataSource(String connectURI) {
        //
        // First, we'll need a ObjectPool that serves as the
        // actual pool of connections.
        //
        // We'll use a GenericObjectPool instance, although
        // any ObjectPool implementation will suffice.
        //

        GenericObjectPool.Config config = new GenericObjectPool.Config();
        config.maxActive = maxActive;   // ex: 150;
        config.maxIdle = maxIdle;       // ex: 100;
        config.minIdle = minIdle;       //30;
        config.maxWait = maxWait;       //1000;
        config.testOnBorrow = testOnBorrow;
        config.testOnReturn = testOnReturn;
        config.testWhileIdle = testWhileIdle;
        config.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
        config.numTestsPerEvictionRun = numTestsPerEvictionRun;
        config.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
        
        ObjectPool connectionPool = new GenericObjectPool(null, config);

        //
        // Next, we'll create a ConnectionFactory that the
        // pool will use to create Connections.
        // We'll use the DriverManagerConnectionFactory,
        // using the connect string passed in the command line
        // arguments.
        //
        Properties connProps = new Properties();
        connProps.setProperty("user", username);
        connProps.setProperty("password", password);
        //p.setProperty("useUnicode", "true");
        //p.setProperty("characterEncoding", "UTF-8");

        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(connectURI, connProps);

        //
        // Now we'll create the PoolableConnectionFactory, which wraps
        // the "real" Connections created by the ConnectionFactory with
        // the classes that implement the pooling functionality.
        //
        PoolableConnectionFactory poolableConnectionFactory = 
                            new PoolableConnectionFactory(
                                    connectionFactory,connectionPool,null,
                                    validationQuery,defaultReadOnly,defaultAutoCommit);

        //
        // Finally, we create the PoolingDriver itself,
        // passing in the object pool we created.
        //
        return new PoolingDataSource(connectionPool);
    }

    private void loadProperties(String propertiesFileName) throws IOException {

        Properties props = new Properties();
        props.load(getClass().getResourceAsStream(propertiesFileName));
        
        username            = PropertiesUtils.getStringValue("username", username, props);
        password            = PropertiesUtils.getStringValue("password", password, props);
        url                 = PropertiesUtils.getStringValue("url", url, props);
        driverClassName     = PropertiesUtils.getStringValue("driverClassName", driverClassName, props);
        defaultAutoCommit   = PropertiesUtils.getBooleanValue("defaultAutoCommit", defaultAutoCommit, props);
        defaultReadOnly     = PropertiesUtils.getBooleanValue("defaultReadOnly", defaultReadOnly, props);
        maxActive           = PropertiesUtils.getIntValue("maxActive", maxActive, props);
        maxIdle             = PropertiesUtils.getIntValue("maxIdle", maxIdle, props);
        initialSize         = PropertiesUtils.getIntValue("initialSize", initialSize, props);
        minIdle             = PropertiesUtils.getIntValue("minIdle", minIdle, props);
        maxWait             = PropertiesUtils.getIntValue("maxWait", maxWait, props);
        validationQuery     = PropertiesUtils.getStringValue("validationQuery", validationQuery, props);
        testOnBorrow        = PropertiesUtils.getBooleanValue("testOnBorrow", testOnBorrow, props);
        testOnReturn        = PropertiesUtils.getBooleanValue("testOnReturn", testOnReturn, props);
        testWhileIdle       = PropertiesUtils.getBooleanValue("testWhileIdle", testWhileIdle, props);
        timeBetweenEvictionRunsMillis   = PropertiesUtils.getIntValue("timeBetweenEvictionRunsMillis", timeBetweenEvictionRunsMillis, props);
        numTestsPerEvictionRun          = PropertiesUtils.getIntValue("numTestsPerEvictionRun", numTestsPerEvictionRun, props);
        minEvictableIdleTimeMillis      = PropertiesUtils.getLongValue("minEvictableIdleTimeMillis", minEvictableIdleTimeMillis, props);
        connectionInitSqls              = PropertiesUtils.getStringValue("connectionInitSqls", connectionInitSqls, props);
        poolPreparedStatements          = PropertiesUtils.getBooleanValue("poolPreparedStatements", poolPreparedStatements, props);
        maxOpenPreparedStatements       = PropertiesUtils.getIntValue("maxOpenPreparedStatements", maxOpenPreparedStatements, props);

    }

    public static void main(String[] args) {

        //
        // Now, we can use JDBC DataSource as we normally would.
        //
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        try {
            System.out.println("Creating connection.");
            //conn = dataSource.getConnection();
            System.out.println("Creating statement.");
            stmt = conn.createStatement();
            System.out.println("Executing statement.");
            rset = stmt.executeQuery(args[1]);
            System.out.println("Results:");
            int numcols = rset.getMetaData().getColumnCount();
            while(rset.next()) {
                for(int i=1;i<=numcols;i++) {
                    System.out.print("\t" + rset.getString(i));
                }
                System.out.println("");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try { rset.close(); } catch(Exception e) { }
            try { stmt.close(); } catch(Exception e) { }
            try { conn.close(); } catch(Exception e) { }
        }
    }
}

