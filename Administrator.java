import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Administrator {
    // Member variables
    private String username;
    private String password;
    boolean isValidLogin = false;
    Scanner scan = new Scanner(System.in);

    // Constructor to initialize the member variables
    public Administrator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Functions
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

            // Execute a SELECT query to retrieve the admin's login details from the
            // database
            stmt = conn.createStatement();
            rs = stmt
                    .executeQuery("SELECT * FROM admin WHERE username = '" + username +
                            "' AND password = '" + password + "'");
            // If the query returns a result, the login is successful
            if (rs.next()) {
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
    void addRecord() {
        // Get the student details from the user
        System.out.print("Enter student's name: ");
        String name = scan.nextLine();
        System.out.print("Enter student's regno: ");
        int regno = scan.nextInt();
        scan.nextLine();
        System.out.print("Enter student's phone number: ");
        String phoneNumber = scan.nextLine();
        System.out.print("Enter student's email: ");
        String email = scan.nextLine();
        System.out.print("Enter student's year: ");
        int year = scan.nextInt();
        scan.nextLine();
        System.out.print("Enter student's department: ");
        String department = scan.nextLine();
        System.out.print("Enter student's room number: ");
        String roomNumber = scan.nextLine();
        System.out.print("Enter student's room type: ");
        String roomType = scan.nextLine();
        System.out.print("Enter student's parent's name: ");
        String parentName = scan.nextLine();
        System.out.print("Enter student's parent's email: ");
        String parentEmail = scan.nextLine();
        System.out.print("Enter student's password: ");
        String password = scan.nextLine();
        System.out.print("Enter student's block: ");
        String blockNumber = scan.nextLine();
        System.out.print("Enter parent's ID: ");
        String parentID = scan.nextLine();
        System.out.print("Enter parents's phone number: ");
        String parentPhoneNumber = scan.nextLine();
        System.out.print("Enter parent's password: ");
        String parentPassword = scan.nextLine();

        // Connect to the database
        Connection conn = null;
        Statement stmt = null;
        try {
            // Register the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "pranav03");

            // Execute an INSERT query to add the student's details to the database
            stmt = conn.createStatement();
            stmt.executeUpdate(
                    "INSERT INTO student (RegisterNumber, Name, PhoneNo, EmailID, Year, Department, RoomNo, RoomType, ParentName, ParentEmail,StudentPassword,BlockNo) VALUES ("
                            + regno + ", '" + name + "', '" + phoneNumber + "', '" + email + "', " + year + ", '"
                            + department + "', "
                            + roomNumber + ", '" + roomType + "', '" + parentName + "', '" + parentEmail + "', '"
                            + password + "', '" + blockNumber + "')");

            stmt.executeUpdate(
                    "INSERT INTO parent (ParentID, ParentName, ParentPhoneNumber, ParentEmail, RegisterNumber, ParentPassword ) VALUES ('"
                            + parentID + "', '" + parentName + "', '" + parentPhoneNumber + "', '" + parentEmail
                            + "', '"
                            + regno + "', '"
                            + parentPassword + "')");
            stmt.executeUpdate("INSERT INTO hostelrooms VALUES ('" + name + "', '" + regno + "', 'O', '" + roomType
                    + "', " + roomNumber + ",'" + blockNumber + "');");
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
    }

    // DONE
    void modifyRecord() {
        System.out.print("Enter the regno of the student whose record you want to modify: ");
        int regno = scan.nextInt();
        scan.nextLine();
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "pranav03");
            stmt = conn.createStatement();
            ResultSet rs = stmt
                    .executeQuery("SELECT * from student where RegisterNUMBER='" + regno + "'");
            if (rs.next()) {
                String updateQuery = "UPDATE student SET ";
                String updateParentQuery = "UPDATE parent SET ";
                boolean addUpdate = true;
                while (addUpdate) {
                    System.out.print(
                            "Enter the field you want to update (name, phone_number, email, year, department, room_number, room_type, parent_name, parent_email, parent_password): ");
                    String field = scan.nextLine();

                    if (field.equals("name")) {
                        System.out.print("Enter the student's new name: ");
                        String name = scan.nextLine();
                        updateQuery += "Name='" + name + "',";

                    } else if (field.equals("phone_number")) {
                        System.out.print("Enter the student's new phone number: ");
                        String phoneNumber = scan.nextLine();
                        updateQuery += "PhoneNo='" + phoneNumber + "',";

                    } else if (field.equals("email")) {
                        System.out.print("Enter the student's new email: ");
                        String email = scan.nextLine();
                        updateQuery += "EmailID='" + email + "',";

                    } else if (field.equals("year")) {
                        System.out.print("Enter the student's new year: ");
                        int year = scan.nextInt();
                        updateQuery += "Year=" + year + ",";
                        scan.nextLine();

                    } else if (field.equals("department")) {
                        System.out.print("Enter the student's new department: ");
                        String department = scan.nextLine();
                        updateQuery += "Department='" + department + "',";

                    } else if (field.equals("room_number")) {
                        System.out.print("Enter the student's new room number: ");
                        Integer roomNumber = scan.nextInt();
                        updateQuery += "RoomNo=" + roomNumber + ",";

                    } else if (field.equals("room_type")) {
                        System.out.print("Enter the student's new room type: ");
                        String roomType = scan.nextLine();
                        updateQuery += "RoomType='" + roomType + "',";

                    } else if (field.equals("parent_name")) {
                        System.out.print("Enter the student's new parent's name: ");
                        String parentName = scan.nextLine();
                        updateQuery += "ParentName='" + parentName + "',";
                        updateParentQuery += "ParentName='" + parentName + "',";

                    } else if (field.equals("parent_email")) {
                        System.out.print("Enter the student's new parent's email: ");
                        String parentEmail = scan.nextLine();
                        updateQuery += "ParentEmail='" + parentEmail + "',";
                        updateParentQuery += "ParentEmail='" + parentEmail + "',";

                    } else if (field.equals("parent_password")) {
                        System.out.print("Enter parent password: ");
                        String parentPassword = scan.nextLine();
                        System.out.println("parent password: " + parentPassword);
                        updateParentQuery += "ParentPassword='" + parentPassword + "',";

                    } else {
                        System.out.println("Invalid field. Please try again.");
                    }

                    System.out.print("Do you want to update more fields (y/n)? ");
                    String Update = scan.nextLine();
                    if (Update.equals("n")) {
                        addUpdate = false;
                    }
                }

                if (!updateQuery.equals("UPDATE student SET ")) {
                    updateQuery = updateQuery.substring(0, updateQuery.length() - 1);
                    updateQuery += " WHERE RegisterNumber='" + regno + "';";
                    stmt.executeUpdate(updateQuery);
                }
                if (!updateParentQuery.equals("UPDATE parent SET ")) {
                    updateParentQuery = updateParentQuery.substring(0, updateParentQuery.length() - 1);
                    updateParentQuery += " WHERE RegisterNumber='" + regno + "';";
                    System.out.println("parent query: " + updateParentQuery);
                    stmt.executeUpdate(updateParentQuery);
                }
            } else {
                System.out.println("Student record does not exist");
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

    // DONE
    void searchStudentRecord(int regno) {
        // Connect to the database
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // Register the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "pranav03");

            // Execute a SELECT query to search for the student's details
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM student WHERE RegisterNumber='" + regno + "'");

            // Print the student's details
            while (rs.next()) {
                System.out.print("Name: " + rs.getString("Name") + "\t");
                System.out.print("Regno: " + rs.getInt("RegisterNumber") + "\t");
                System.out.print("Phone number: " + rs.getString("PhoneNo") + "\t");
                System.out.print("Email: " + rs.getString("EmailID") + "\t");
                System.out.print("Year: " + rs.getInt("Year") + "\t");
                System.out.print("Department: " + rs.getString("Department") + "\t");
                System.out.print("Room number: " + rs.getString("RoomNo") + "\t");
                System.out.print("Room type: " + rs.getString("RoomType") + "\t");
                System.out.print("Parent's name: " + rs.getString("ParentName") + "\t");
                System.out.println("Parent's email: " + rs.getString("ParentEmail"));
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
    }

    // DONE
    void viewVisitorLog() {
        // Connect to the database
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // Register the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            // Open a connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "pranav03");

            // Execute a SELECT query to view the visitor logs
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM visitor");

            // Print the visitor logs
            while (rs.next()) {
                System.out.print("Visitor's name: " + rs.getString("VisitorName") + "\t");
                System.out.print("Visitor's phone number: " + rs.getString("PhoneNo") + "\t");
                System.out.print("Visitor's date of visit: " + rs.getString("DateofVisit") + "\t");
                System.out.print("Nature of visit: " + rs.getString("NatureOfVisit") + "\t");
                System.out.print("Entry time: " + rs.getTimestamp("InTime") + "\t");
                System.out.print("Exit time: " + rs.getTimestamp("OutTime") + "\t");
                System.out.println("Type of visitor: " + rs.getString("VisitorType"));
                System.out.println();
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
    }

    // DONE
    void viewOutpassLog() {
        // Connect to the database
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // Register the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            // Open a connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "pranav03");

            // Execute a SELECT query to view the outpass logs
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM outpasslogs");

            // Print the outpass logs
            while (rs.next()) {
                System.out.print("Student's name: " + rs.getString("Name") + "\t");
                System.out.print("Student's regno: " + rs.getString("RegisterNumber") + "\t");
                System.out.print("Student's block number: " + rs.getString("BlockNumber") + "\t");
                System.out.print("Student's room number: " + rs.getInt("RoomNumber") + "\t");
                System.out.print("Student year: " + rs.getInt("Year") + "\t");
                System.out.print("Purpose: " + rs.getString("Purpose") + "\t");
                System.out.print("Warden's approval: " + rs.getString("WardenValidation") + "\t");
                System.out.print("Parent's approval: " + rs.getString("ParentValidation") + "\t");
                System.out.print("Out Date: " + rs.getTimestamp("OutDate") + "\t");
                System.out.print("Out Time: " + rs.getTimestamp("OutTime") + "\t");
                System.out.print("In Date: " + rs.getTimestamp("InDate") + "\t");
                System.out.println("In Time: " + rs.getTimestamp("InTime"));
                System.out.println();
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
    }

    void validateOutpass() {
        // Connect to the database
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // Register the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "pranav03");

            // Execute a SELECT query to view the outpass records with a status of pending
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM outpasslogs WHERE WardenValidation='P'");

            // Print the outpass records
            while (rs.next()) {
                int outpassNo = rs.getInt("OutpassNumber");
                System.out.print("OutpassNumber: " + outpassNo + "\t");
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
                System.out.println("ParentValidation: " + rs.getString("ParentValidation") + "\t");
                System.out.print("Enter the new status (approved(A) or rejected(D)): ");
                String status = scan.next();
                stmt = conn.createStatement();
                stmt.executeUpdate(
                        "UPDATE outpasslogs SET WardenValidation='" + status + "' WHERE OutpassNumber=" + outpassNo);
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
    }

    // DONE
    void viewParentDetails(long regno) {
        // Connect to the database
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // Register the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            // Open a connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "pranav03");

            // Execute a SELECT query to view the parent details
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM parent WHERE RegisterNumber='" + regno + "'");
            // Print the parent details
            while (rs.next()) {
                System.out.print("Student's regno: " + rs.getInt("RegisterNumber") + "\t");
                System.out.print("Parent's ID: " + rs.getString("ParentID") + "\t");
                System.out.print("Parent's name: " + rs.getString("ParentName") + "\t");
                System.out.print("Parent's phone number: " + rs.getString("ParentPhoneNumber") + "\t");
                System.out.println("Parent's email: " + rs.getString("ParentEmail"));
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
    }

    // DONE (unless all the rooms are already in the table coz the block already has
    // so many rooms predefined.)
    void viewHostelRoomAllocation() {
        // Connect to the database
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // Register the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            // Open a connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "pranav03");

            // Execute a SELECT query to view the hostel room allocations
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM hostelrooms WHERE OccupancyStatus='O'");

            // Print the hostel room allocations
            while (rs.next()) {
                System.out.print("Student's Name: " + rs.getString("OccupantName") + "\t");
                System.out.print("Student's Register Number: " + rs.getString("RegisterNumber") + "\t");
                System.out.print("Student's Room Number: " + rs.getInt("RoomNumber") + "\t");
                System.out.print("Room type: " + rs.getString("RoomType") + "\t");
                System.out.println("Block Number: " + rs.getString("BlockNumber"));
                System.out.println();
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
    }

    // DONE
    void modifyHostelRoomAllocation() {
        System.out.print("Enter the regno of the student whose hostel record you want to modify: ");
        int regno = scan.nextInt();
        scan.nextLine();

        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "pranav03");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from hostelrooms where RegisterNumber='" + regno + "';");
            if (rs.next()) {
                System.out.print("Enter the student's new room number: ");
                Integer roomno = scan.nextInt();
                scan.nextLine();
                System.out.print("Enter the student's new block number: ");
                String blockno = scan.nextLine();
                System.out.print("Enter the student's new room type: ");

                String roomtype = scan.nextLine();
                ResultSet rstemp = stmt.executeQuery(
                        "SELECT * from hostelrooms where RoomNumber =" + roomno + " and BlockNumber='" + blockno + "'");
                if (!rstemp.next()) {
                    String updateQuery = "UPDATE hostelrooms SET RoomNumber=" + roomno + ", BlockNumber='" + blockno
                            + "', RoomType='" + roomtype + "' WHERE RegisterNumber='" + regno + "';";
                    stmt.executeUpdate(updateQuery);
                } else {
                    System.out.println("Room has been occupied!");
                }
            }
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
    }
}
