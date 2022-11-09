package jdbctests;

import org.junit.jupiter.api.Test;

import utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class DBUtilPractice {


    @Test
    public void test1(){

        DBUtils.createConnection();
        String query = "select first_name, last_name,salary,job_id from employees where rownum < 6";
        List<Map<String, Object>> queryData = DBUtils.getQueryResultMap(query);

        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());
        }

        //close the connection

        DBUtils.destroy();


    }


    @Test
    public void test(){

        DBUtils.createConnection();
        String query = "select first_name, last_name,salary,job_id from employees where rownum < 2";

        Map<String, Object> rowMap = DBUtils.getRowMap(query);


            System.out.println(rowMap.toString());


        //close the connection

        DBUtils.destroy();


    }



}
