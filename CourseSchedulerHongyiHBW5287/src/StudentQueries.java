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
public class StudentQueries {
    private static Connection connection;
    private static PreparedStatement addStudent;
    private static PreparedStatement getStudentID;
    private static PreparedStatement getStudentFirstName;
    private static PreparedStatement getStudentLastName;
    //private static PreparedStatement getSemesterList;
    private static ResultSet resultSet;
    public static void addStudent(StudentEntry student){
        connection = DBConnection.getConnection();
        try
        {
            addStudent = connection.prepareStatement("insert into app.Student (StudentID, firstName, lastName) values (?, ?, ?)");
            addStudent.setString(1, student.getStudentID());
            addStudent.setString(2, student.getFirstName());
            addStudent.setString(3, student.getLastName());
            addStudent.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        
    }

    public static ArrayList<StudentEntry> getAllStudents(){
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> students = new ArrayList<StudentEntry>();
        try
        {
            getStudentID = connection.prepareStatement("select StudentID from app.Student");
            resultSet = getStudentID.executeQuery();
            
            while(resultSet.next())
            {
                String StudentID = resultSet.getString(1);
                getStudentFirstName = connection.prepareStatement("select FirstName from app.Student Where StudentID = "+StudentID);
                resultSet = getStudentFirstName.executeQuery();
                String FirstName = resultSet.getString(1);
                getStudentFirstName = connection.prepareStatement("select LastName from app.Student Where StudentID = "+StudentID);
                resultSet = getStudentLastName.executeQuery();
                String LastName = resultSet.getString(1);
                students.add(new StudentEntry(StudentID,FirstName,LastName));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return students;
    }
    public static StudentEntry getStudent(String studentID){
        //Part II
        return new StudentEntry("aaa","aaa","aaa");
    }
    public static void dropStudent(String studentID){
        //Part II
    }
}
