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
        //private static ArrayList<String> faculty = new ArrayList<String>();
        try
        {
            connection.prepareStatement("DELETE FROM APP.COURSEENTRY");
            System.out.println("cleared");
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}

