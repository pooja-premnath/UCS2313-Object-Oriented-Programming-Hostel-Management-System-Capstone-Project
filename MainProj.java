import java.sql.Time;
import java.util.*;

public class MainProj {
  public static void main(String[] args) {
    final Scanner scan = new Scanner(System.in);
    System.out.println("Choose from:\n1. Admin\n2. Student\n3. Parent\n4. Visitor\n");
    int choice = scan.nextInt();
    if (choice == 1) {
      System.out.print("Enter Admin username: ");
      String username = scan.next();
      System.out.print("Enter Admin Password: ");
      String password = scan.next();

      Administrator admin = new Administrator(username, password);
      admin.login();

      if (admin.isValidLogin) {
        int adminChoice = 0;
        System.out.println("Logged in");
        while (adminChoice != 10) {

          System.out.println(
              "Choose from:\n1. Add Record\n2. Modify Record\n3. Search Student Record\n4. View Visitor Log\n5. View Outpass Log\n6. Validate Outpass\n7. View Parent Details\n8. View Hostel Room Allocation\n9. Modify Room Allocation\n10. Log out");
          adminChoice = scan.nextInt();
          switch (adminChoice) {
            case 1:
              admin.addRecord();
              break;
            case 2:
              admin.modifyRecord();
              break;
            case 3:
              System.out.print("Enter student register number: ");
              int regno = scan.nextInt();
              admin.searchStudentRecord(regno);
              break;
            case 4:
              admin.viewVisitorLog();
              break;
            case 5:
              admin.viewOutpassLog();
              break;
            case 6:
              admin.validateOutpass();
              break;
            case 7:
              System.out.print("Enter student register number: ");
              regno = scan.nextInt();
              admin.viewParentDetails(regno);
              break;
            case 8:
              admin.viewHostelRoomAllocation();
              break;
            case 9:
              admin.modifyHostelRoomAllocation();
              break;
            default:
              break;
          }
        }
      } else {
        System.out.println("Cannot login");
      }
    }

    else if (choice == 2) {
      System.out.print("Enter Student Reg No: ");
      String username = scan.next();
      System.out.print("Enter Student Password: ");
      String password = scan.next();
      Student student = new Student(username, password);
      student.login();
      if (student.isValidLogin) {
        System.out.println("Logged in");
        int studentChoice = 0;
        while (studentChoice != 5) {
          System.out.println(
              "Choose from:\n1. View Details\n2. Outpass Status\n3. View Outpass\n4. Request Outpass\n5. Log out");
          studentChoice = scan.nextInt();
          switch (studentChoice) {
            case 1:
              student.viewRecord();
              break;
            case 2:
              student.outpassStatus();
              break;
            case 3:
              student.viewOutpass();
              break;
            case 4:
              student.requestOutPass();
              break;
            default:
              break;
          }
        }
      } else {
        System.out.println("Invalid Login!");
      }
    }

    else if (choice == 3) {
      System.out.print("Enter Parent ID: ");
      String username = scan.next();
      System.out.print("Enter Parent Password: ");
      String password = scan.next();
      Parent parent = new Parent(username, password);
      parent.login();
      if (parent.isValidLogin) {
        System.out.println("Logged in");
        int parentChoice = 0;
        while (parentChoice != 2) {
          try {
            System.out.println(
                "Choose from:\n1.Outpass Validation \n2.Log out\n");
            parentChoice = scan.nextInt();
            switch (parentChoice) {
              case 1:
                parent.outpassValidate();
                break;
              default:
                break;
            }
          } catch (NoSuchElementException e) {
          }
        }
      } else {
        System.out.println("Invalid Login!");
      }

    } else if (choice == 4) {
      int visitorChoice = 0;
      Visitor visitor = new Visitor();

      while (visitorChoice != 2) {
        System.out.println("Choose from:\n1.Add Visitor Entry\n2. Log out\n");
        visitorChoice = scan.nextInt();
        // scan.nextLine();
        if (visitorChoice == 1) {
          scan.nextLine();
          System.out.println("Enter visitor name: ");
          String name = scan.nextLine();
          System.out.println("Enter visitor phone number: ");
          String phoneNumber = scan.nextLine();
          System.out.println("Enter date of visit (in yyyy-mm-dd format): ");
          Date date = java.sql.Date.valueOf(scan.nextLine());
          System.out.println("Enter in time (in hh:mm format): ");
          String inTime = scan.nextLine();
          System.out.println("Enter out time (in hh:mm format): ");
          String outTime = scan.nextLine();
          inTime = inTime + ":00";
          outTime = outTime + ":00";
          Time inTimeVar = Time.valueOf(inTime);
          Time outTimeVar = Time.valueOf(outTime);

          System.out.println("Enter visitor type: ");
          String visitorType = scan.nextLine();
          System.out.println("Enter nature of visit: ");
          String natureOfVisit = scan.nextLine();
          if (visitorChoice == 1) {
            visitor.register(name, phoneNumber, date, inTimeVar, outTimeVar, visitorType, natureOfVisit);
          }
          if (visitorChoice == 2) {
            break;
          }
        }
      }
      scan.close();
    }
  }
}