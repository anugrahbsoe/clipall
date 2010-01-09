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
* File      : Query.java
* Created   : Dec 22, 2009, 12:03:03 AM
*/

package dev.utils.db.dbcp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

/**
 * @author Erol Hira
 */
public class Sql {

    private DataSource dataSource;
    private Connection connection;    
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet rset;

    private static Sql sql;

    private Sql() throws SQLException{ }

    public static Sql createInstance(DataSource datasource) throws SQLException{
        
        sql = new Sql();        
        sql.init(datasource);
        
        return sql;
    }

    private void init(DataSource datasource) throws SQLException{
        this.dataSource = datasource;
        connection = dataSource.getConnection();
    }

    public void executeUpdate(String ddlOrDml, SqlJob sqlJob){
        
        try {
            
            pstmt = connection.prepareStatement(ddlOrDml);
            
            sqlJob.setPstmt(pstmt);
            
            sqlJob.preparePstmt();
            pstmt.executeUpdate();
            sqlJob.processExecuteUpdateResult();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{            
            try { pstmt.close(); } catch(Exception e) { }
        }
    }

    public void executeQuery(String query, SqlJob sqlJob){

        try {
            
            stmt = connection.createStatement();
            rset = stmt.executeQuery(query);

            sqlJob.setStmt(stmt);
            sqlJob.setRset(rset);

            sqlJob.processQueryResult();

        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try { rset.close(); } catch(Exception e) { }
            try { stmt.close(); } catch(Exception e) { }
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStmt() {
        return stmt;
    }

    public ResultSet getRset() {
        return rset;
    }
  
}
