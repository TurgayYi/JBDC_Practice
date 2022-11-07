package jdbctests;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class Jdbc_examples {

    String dbUrl ="jdbc:oracle:thin:@44.195.19.167:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from departments");

        resultSet.next();


        while (resultSet.next()){

            System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2) +
                    " - " + resultSet.getInt(3) + " - " + resultSet.getInt(4) );

        }


        //close connection
        resultSet.close();
        statement.close();
        connection.close();


    }

    @Test

    public void test2() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from departments");

        resultSet.last();
        System.out.println("resultSet.getRow() = " + resultSet.getRow());

        resultSet.beforeFirst();
        while (resultSet.next()){
            System.out.println(resultSet.getString(2));
        }




        resultSet.close();
        statement.close();
        connection.close();


    }


    @Test
    public void test3() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from departments");


        //get the database related data inside the dbMetaData()
        DatabaseMetaData dbMetaData = connection.getMetaData();

        System.out.println("dbMetaData.getUserName() = " + dbMetaData.getUserName());
        System.out.println("dbMetaData.getDatabaseProductName() = " + dbMetaData.getDatabaseProductName());
        System.out.println("dbMetaData.getDatabaseProductVersion() = " + dbMetaData.getDatabaseProductVersion());
        System.out.println("dbMetaData.getDriverName() = " + dbMetaData.getDriverName());
        System.out.println("dbMetaData.getDriverVersion() = " + dbMetaData.getDriverVersion());


        //get the resultsetmetadata //rsmd
        ResultSetMetaData rsMetaData = resultSet.getMetaData();

        //how many columns ?
        int colCount = rsMetaData.getColumnCount();
        System.out.println(colCount);

        //get column names
        System.out.println(rsMetaData.getColumnName(1));

        //print all the column names
        for (int i = 1; i <=rsMetaData.getColumnCount() ; i++) {

            System.out.println(rsMetaData.getColumnName(i));

        }



        resultSet.close();
        statement.close();
        connection.close();


    }



}
