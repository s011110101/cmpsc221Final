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
public class MultiTableQueries {
    private static Connection connection;
    private static PreparedStatement getClass;
    private static PreparedStatement getStudentID;
    private static PreparedStatement getStudentFirstName;
    private static PreparedStatement getStudentLastName;
    //private static PreparedStatement getSemesterList;
    private static ResultSet resultSet;
    public static ArrayList<ClassDescription> getAllClassDescriptions(String semester){
        ArrayList<ClassDescription> classes = new ArrayList<ClassDescription>();
        connection = DBConnection.getConnection();
        try
        {
            getClass = connection.prepareStatement("SELECT app.classentry.coursecode, description, seats from app.classentry, app.courseentry where semester = '"+semester+"' and app.classentry.coursecode = app.courseentry.coursecode order by app.classentry.coursecode");
            resultSet = getClass.executeQuery();
            while(resultSet.next())
            {
                String Coursecode = resultSet.getString(1);
                String Description = resultSet.getString(2);
                int Seats = resultSet.getInt(3);
                classes.add(new ClassDescription(Coursecode,Description,Seats));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return classes;
    }
}
