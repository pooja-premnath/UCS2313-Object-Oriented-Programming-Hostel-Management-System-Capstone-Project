import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.*;
import java.util.Scanner;

public class Student {
    Scanner scan = new Scanner(System.in);
    private String username;
    private String password;
    private int regNumber;
    private String name;
    private String phoneNumber;
    private String email;
    private int year;
    private String department;
    private String roomNumber;
    private String roomType;
    private String parentName;
    private String parentEmail;
    private String blockNumber;
    public boolean isValidLogin;

    // DONE
    Student(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // DONE
    public int getRegNumber() {
        return this.regNumber;
    }

    // DONE
    public void setRegNumber(int regNumber) {
        this.regNumber = regNumber;
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
    public int getYear() {
        return this.year;
    }

    // DONE
    public void setYear(int year) {
        this.year = year;
    }

    // DONE
    public String getDepartment() {
        return this.department;
    }

    // DONE
    public void setDepartment(String department) {
        this.department = department;
    }

    // DONE
    public String getRoomNumber() {
        return this.roomNumber;
    }

    // DONE
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    // DONE
    public String getRoomType() {
        return this.roomType;
    }

    // DONE
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    // DONE
    public String getParentName() {
        return this.parentName;
    }

    // DONE
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    // DONE
    public String getParentEmail() {
        return this.parentEmail;
    }

    // DONE
    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }

    // DONE
    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
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
                    .executeQuery("SELECT * FROM student WHERE RegisterNumber = '" + username +
                            "' AND StudentPassword = '" + password + "'");
            if (rs.next()) {
                setRegNumber(Integer.parseInt(rs.getString("RegisterNumber")));
                setName(rs.getString("Name"));
                setPhoneNumber(rs.getString("PhoneNo"));
                setEmail(rs.getString("EmailID"));
                setYear(Integer.parseInt(rs.getString("Year")));
                setDepartment(rs.getString("Department"));
                setRoomNumber(rs.getString("RoomNo"));
                setRoomType(rs.getString("RoomType"));
                setParentName(rs.getString("ParentName"));
                setParentEmail(rs.getString("ParentEmail"));
                setBlockNumber(rs.getString("BlockNo"));

                isValidLogin = true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Close the JDBC objects
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
    public void viewRecord() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Register the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "pranav03");

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM student WHERE RegisterNumber = " + this.regNumber);
            if (rs.next()) {
                // Print the student's record
                System.out.print("Register Number: " + rs.getString("RegisterNumber") + "\t");
                System.out.print("Name: " + rs.getString("Name") + "\t");
                System.out.print("Phone number: " + rs.getString("PhoneNo") + "\t");
                System.out.print("Email: " + rs.getString("EmailID") + "\t");
                System.out.print("Year: " + rs.getInt("Year") + "\t");
                System.out.print("Department: " + rs.getString("Department") + "\t");
                System.out.print("Room number: " + rs.getInt("RoomNo") + "\t");
                System.out.print("Room type: " + rs.getString("RoomType") + "\t");
                System.out.print("Parent name: " + rs.getString("ParentName") + "\t");
                System.out.print("Parent email: " + rs.getString("ParentEmail") + "\t");
                System.out.println("Block number: " + rs.getString("BlockNo"));
            } else {
                // Print an error message if the student was not found
                System.out.println("Error: Student not found");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
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
    }

    // DONE
    public int outpassStatus() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Register the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "pranav03");

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM outpasslogs WHERE RegisterNumber = " + this.regNumber);
            if (rs.next()) {
                // Print the outpass status
                System.out.print("Parent Validation Status: " + rs.getString("ParentValidation") + "\t");
                System.out.print("Warden Validation Status: " + rs.getString("WardenValidation") + "\t");
                if (rs.getString("ParentValidation").equals("A") && rs.getString("WardenValidation").equals("A")) {
                    System.out.println("Outpass Approved");
                    return 1;
                } else if (rs.getString("ParentValidation").equals("D")
                        || rs.getString("WardenValidation").equals("D")) {
                    System.out.println("Outpass denied");
                } else {
                    System.out.println("Outpass Not Approved Yet");
                }

            } else {
                // Print a message if the student has not requested an outpass
                System.out.println("Error: Outpass not found");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
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
        return 0;
    }

    public void viewOutpass() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // Register the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "pranav03");
            if (outpassStatus() == 1) {
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT * FROM outpasslogs WHERE RegisterNumber = " + this.regNumber);
                if (rs.next()) {

                    System.out.print("OutpassNumber: " + rs.getInt("OutpassNumber") + "\t");
                    System.out.print("Name: " + rs.getString("Name") + "\t");
                    System.out.print("RegisterNumber: " + rs.getString("RegisterNumber") + "\t");
                    System.out.print("BlockNumber: " + rs.getString("BlockNumber") + "\t");
                    System.out.print("RoomNumber: " + rs.getInt("RoomNumber") + "\t");
                    System.out.print("Purpose: " + rs.getString("Purpose") + "\t");
                    System.out.print("Year: " + rs.getInt("Year") + "\t");
                    System.out.print("OutDate: " + rs.getDate("OutDate") + "\t");
                    System.out.print("InDate: " + rs.getDate("InDate") + "\t");
                    System.out.print("OutTime: " + rs.getTime("OutTime") + "\t");
                    System.out.print("InTime: " + rs.getTime("InTime") + "\t");
                    System.out.print("WardenValidation: " + rs.getString("WardenValidation") + "\t");
                    System.out.println("ParentValidation: " + rs.getString("ParentValidation"));

                    // System.out.println("Parent consent: " + rs.getString("ParentConsent"));
                } else {
                    // Print a message if the student has not requested an outpass
                    System.out.println("Error: Outpass not found");
                }

            }
        } catch (SQLException |

                ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
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
    }

    // DONE
    void requestOutPass() {
        System.out.print("Enter the purpose of the outpass: ");
        String purpose = scan.nextLine();
        System.out.print("Enter the out date (yyyy-MM-dd): ");
        String outDateInput = scan.nextLine();
        System.out.print("Enter In Date (yyyy-MM-dd): ");
        String inDateInput = scan.nextLine();
        System.out.print("Enter In Time (HH:mm): ");
        String inTimeInput = scan.nextLine();
        System.out.print("Enter Out Time (HH:mm): ");
        String outTimeInput = scan.nextLine();
        try {
            outTimeInput = outTimeInput + ":00";
            inTimeInput = inTimeInput + ":00";
            Date outDate = java.sql.Date.valueOf(outDateInput);
            Date inDate = java.sql.Date.valueOf(inDateInput);
            Time outTime = Time.valueOf(outTimeInput);
            Time inTime = Time.valueOf(inTimeInput);
            // Date inTime = timeformatter.parse(inTimeInput);
            // Connect to the database
            Connection conn = null;
            Statement stmt = null;
            try {
                // Register the JDBC driver
                Class.forName("com.mysql.jdbc.Driver");
                // Open a connection to the database
                conn = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "pranav03");

                // Execute an INSERT query to add the outpass details to the database
                stmt = conn.createStatement();
                stmt.executeUpdate(
                        "INSERT INTO outpasslogs (Name,RegisterNumber,BlockNumber,RoomNumber,Purpose,Year,OutDate,InDate,OutTime,InTime) VALUES ('"
                                + this.name + "', '" + this.regNumber + "', '" + this.blockNumber + "', "
                                + this.roomNumber + ", '" + purpose
                                + "', " + this.year + ", '" + outDate + "', '" + inDate + "', '" + outTime + "', '"
                                + inTime + "');");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                // Close the JDBC objects
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
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }
}
