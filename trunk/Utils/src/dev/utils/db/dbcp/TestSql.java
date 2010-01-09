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
* File      : TestSql.java
* Created   : Dec 22, 2009, 12:42:11 AM
*/

package dev.utils.db.dbcp;

import java.sql.SQLException;

/**
 * @author Erol Hira
 */
public class TestSql {

    public void test(){

        try {

            ManualPoolingDataSource.getInstance().init("/db.properties");

            Sql sql = Sql.createInstance(ManualPoolingDataSource.getInstance().getDataSource());

            SqlJob sqlJob = new SqlJob(sql) {

                @Override
                public void preparePstmt() throws SQLException {
                    pstmt.setString(1, "Ciga");
                }
                
            };

            sql.executeUpdate("insert into test(name) values(?)", sqlJob);

            sqlJob = new SqlJob(sql) {

                @Override
                public void processQueryResult() throws SQLException {

                    while(rset.next()){
                        System.out.println(rset.getString("name"));
                    }
                }


            };

            sql.executeQuery("select * from test", sqlJob);

            

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
