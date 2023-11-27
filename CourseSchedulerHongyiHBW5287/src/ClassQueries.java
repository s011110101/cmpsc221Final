/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hongyi
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassQueries {
    private static Connection connection;
    private static PreparedStatement addClass;
    private static PreparedStatement getClassSeats;
    private static PreparedStatement getCourseList;
    //private static PreparedStatement getSemesterList;
    private static ResultSet resultSet;
    
    public static void addClass(ClassEntry chosenClass)
    {
        connection = DBConnection.getConnection();
        try
        {
            addClass = connection.prepareStatement("insert into app.ClassEntry (semester, CourseCode, Seats) values (?, ?, ?)");
            addClass.setString(1, chosenClass.getSemester());
            addClass.setString(2, chosenClass.getCourseCode());
            addClass.setInt(3, chosenClass.getSeats());
            addClass.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    public static int getClassSeats(String semester, String courseCode){
        connection = DBConnection.getConnection();
        int result = 0;
        try
        {
            getClassSeats = connection.prepareStatement("select seats from app.courseEntry WHERE coursecode = "+courseCode+" AND semester = "+semester);
            resultSet = getClassSeats.executeQuery();
            result = (Integer) resultSet.getObject(1);
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return result;
        
    }
    public static ArrayList<String> getAllCourseCodes(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<String> courseCodes = new ArrayList<String>();
        try
        {
            getCourseList = connection.prepareStatement("select courseCode from app.courseEntry WHERE semester = "+semester);
            resultSet = getCourseList.executeQuery();
            
            while(resultSet.next())
            {
                courseCodes.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return courseCodes;
        
    }
    public static void dropClass(String semester, String courseCode){
    //part II
    }
    
}
