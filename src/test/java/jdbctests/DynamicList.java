package jdbctests;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicList {
    String dbUrl ="jdbc:oracle:thin:@44.195.19.167:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test

    public void test2() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select first_name, last_name,salary,job_id from employees where rownum <6");

        //in order to get column names we need ResultSetMetaData
        ResultSetMetaData rsmd = resultSet.getMetaData();

        //List of Maps to keep all information

        List<Map<String,Object>> queryData = new ArrayList<>();

        //number of columns
        int colCount =rsmd.getColumnCount();

        while (resultSet.next()){

            Map<String, Object> row = new HashMap<>();

            for (int i = 1; i <= colCount; i++) {

                row.put(rsmd.getColumnName(i),resultSet.getObject(i));


            }



            //add ready map row to the list
            queryData.add(row);


        }

        //print each row inside the list

        for (Map<String, Object> row : queryData) {

            System.out.println(row.toString());

        }





        //close connection
        resultSet.close();
        statement.close();
        connection.close();


    }


}
