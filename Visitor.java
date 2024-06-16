import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Date;

public class Visitor {

    private int visitorId;
    private String name;
    private String phoneNumber;
    private Date date;
    private Time inTime;
    private String natureOfVisit;
    private Time outTime;
    private String visitorType;

    public void setVisitorId(int visitorId) {
        this.visitorId = visitorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getInTime() {
        return inTime;
    }

    public void setInTime(Time inTime) {
        this.inTime = inTime;
    }

    public String getNatureOfVisit() {
        return natureOfVisit;
    }

    public void setNatureOfVisit(String natureOfVisit) {
        this.natureOfVisit = natureOfVisit;
    }

    public Time getOutTime() {
        return outTime;
    }

    public void setOutTime(Time outTime) {
        this.outTime = outTime;
    }

    public String getvisitorType() {
        return this.visitorType;
    }

    public void setvisitorType(String visitorType) {
        this.visitorType = visitorType;
    }

    public void register(String name, String phoneNumber, Date date, Time inTime, Time outTime,
            String visitorType, String natureOfVisit) {

        this.name = name;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.inTime = inTime;
        this.outTime = outTime;
        this.visitorType = visitorType;
        this.natureOfVisit = natureOfVisit;

        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost/java", "root",
                    "pranav03");
            stmt = conn.createStatement();

            stmt.executeUpdate(
                    "INSERT INTO visitor (VisitorName, PhoneNo, DateofVisit, InTime, OutTime, VisitorType, NatureOfVisit) VALUES ('"
                            + name + "', '" + phoneNumber + "', '" + date + "', '" + inTime + "', '" + outTime + "', '"
                            + visitorType + "', '" + natureOfVisit + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void signOut(Time outTime) {
        this.outTime = outTime;

        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost/java", "root",
                    "pranav03");
            stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE visitor_logs SET out_time = '" + outTime + "' WHERE visitor_id = " + visitorId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
