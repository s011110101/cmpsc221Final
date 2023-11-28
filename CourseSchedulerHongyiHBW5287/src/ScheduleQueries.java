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
import java.sql.Timestamp;
import java.util.ArrayList;
public class ScheduleQueries {
    private static Connection connection;
    private static PreparedStatement addSchedule;
    private static PreparedStatement getScheduleByStudent;
    private static PreparedStatement getStudentCount;
    private static PreparedStatement getSeats;
    private static ResultSet resultSet;
    public static void addScheduleEntry(ScheduleEntry entry){
        connection = DBConnection.getConnection();
        try
        {
            addSchedule = connection.prepareStatement("insert into app.Schedule (Semester, CourseCode, Status, TimeStamp, StudentID) values (?, ?, ?, ?, ?)");
            addSchedule.setString(1, entry.getSemester()); 
            addSchedule.setString(2, entry.getCourseCode());
            addSchedule.setString(3, entry.getStatus());
            addSchedule.setTimestamp(4, entry.getTimestamp());
            addSchedule.setString(5, entry.getStudentID());
            addSchedule.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }

    }
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> schedule = new ArrayList<ScheduleEntry>();
        try
        {
            getScheduleByStudent = connection.prepareStatement("select Semester, CourseCode, Status, TimeStamp, StudentID from app.Schedule where studentID = '"+studentID+"' and semester = '"+semester+"'");
            resultSet = getScheduleByStudent.executeQuery();
            
            while(resultSet.next())
            {
                String Semester = resultSet.getString(1);
                String CourseCode = resultSet.getString(2);
                String Status = resultSet.getString(3);
                Timestamp Timestamp = resultSet.getTimestamp(4);
                String StudentID = resultSet.getString(5);
                schedule.add(new ScheduleEntry(Semester,CourseCode, Status, Timestamp, StudentID));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return schedule;
    }
    public static int getScheduledStudentCount(String semester, String courseCode){
        connection = DBConnection.getConnection();
        int count=0;
        try
        {
            getStudentCount = connection.prepareStatement("select count(*) from app.Schedule where courseCode = '"+courseCode+"' and semester = '"+semester+"'");
            resultSet = getStudentCount.executeQuery();
            while(resultSet.next()){
                count = resultSet.getInt(1);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return count;
    }

    public static boolean seatsAvalible(String semester, String courseCode) {
        connection = DBConnection.getConnection();
        int seats=0;
        try
        {
            getSeats = connection.prepareStatement("select Seats from app.ClassEntry where courseCode = '"+courseCode+"'and semester = '"+semester+"'");
            
            resultSet = getSeats.executeQuery();
            while(resultSet.next()){
                seats = resultSet.getInt(1);
            }
            if (getScheduledStudentCount(semester, courseCode)<seats){
                return true;
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return false;
    }
    
}
