/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author hongyi
 */
public class clearDataBase {
    private static Connection connection;
    public static void clearDB(){
        
        connection = DBConnection.getConnection();
        try{
            //connection.prepareStatement("DELETE FROM ClassQueries WHERE CONDITION 1=1");
            connection.prepareStatement("DELETE FROM APP.CLASSDESCRIPTION WHERE 1=1");
            connection.prepareStatement("DELETE FROM APP.CLASSENTRY WHERE 1=1");
            connection.prepareStatement("DELETE FROM APP.COURSEENTRY WHERE 1=1");
            connection.prepareStatement("DELETE FROM APP.STUDENTENTRY WHERE 1=1");
            //connection.prepareStatement("DELETE FROM ClassQueries WHERE CONDITION 1=1");
            System.out.println("executed"); 
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}

