import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

interface UserAuth {
    public boolean login();
}

public class Parent implements UserAuth {
    public boolean isValidLogin;
    private String parentID;
    private String name;
    private String phoneNumber;
    private String email;
    private String regNumber;
    private String username;
    private String password;

    Parent(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // DONE
    public String getParentID() {
        return this.parentID;
    }

    // DONE
    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    // DONE
    public String getName() {
        return this.name;
    }

    // DONE
    public void setName(String name) {
        this.name = name;
    }

    // DONE
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    // DONE
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // DONE
    public String getEmail() {
        return this.email;
    }

    // DONE
    public void setEmail(String email) {
        this.email = email;
    }

    // DONE
    public String getRegNumber() {
        return this.regNumber;
    }

    // DONE
    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    // DONE
    public boolean login() {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Register the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost/java", "root",
                    "pranav03");

            stmt = conn.createStatement();
            rs = stmt
                    .executeQuery("SELECT * FROM parent WHERE ParentID = '" + username +
                            "' AND ParentPassword = '" + password + "'");
            if (rs.next()) {
                setParentID(rs.getString("ParentID"));
                setName(rs.getString("ParentName"));
                setPhoneNumber(rs.getString("ParentPhoneNumber"));
                setEmail(rs.getString("ParentEmail"));
                setRegNumber(rs.getString("RegisterNumber"));
                isValidLogin = true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isValidLogin;

    }

    // DONE
    public void outpassValidate() {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "pranav03");

            stmt = conn.createStatement();
            ResultSet rs = stmt
                    .executeQuery(
                            "SELECT * FROM outpasslogs WHERE RegisterNumber = '" + this.regNumber
                                    + "' AND ParentValidation = 'P'");

            while (rs.next()) {
                final Scanner input = new Scanner(System.in);
                int requestID = rs.getInt("OutpassNumber");
                String studentName = rs.getString("Name");
                Date startDate = rs.getDate("OutDate");
                Date endDate = rs.getDate("InDate");
                String purpose = rs.getString("Purpose");
                System.out.println("Outpass request for student: " + studentName);
                System.out.println("From: " + startDate + " To: " + endDate);
                System.out.println("Purpose: " + purpose);
                System.out.println("Approve (A) or Deny (D)?");
                String response = input.nextLine();
                stmt = conn.createStatement();
                if (response.equals("A")) {
                    stmt.executeUpdate(
                            "UPDATE outpasslogs SET ParentValidation = 'A' WHERE OutpassNumber = " + requestID);
                } else if (response.equals("D")) {
                    stmt.executeUpdate(
                            "UPDATE outpasslogs SET ParentValidation = 'D' WHERE OutpassNumber = " + requestID);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}