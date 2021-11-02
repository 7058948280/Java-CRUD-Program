import java.sql.*;
import java.util.Scanner;
public class shri 
{

    public static void main(String[] args) 
    {
        Scanner scn = new Scanner(System.in);
        Scanner sct = new Scanner(System.in);
        try
        {  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost/test","root","");  
            Statement stmt=con.createStatement();  

            int ch=0;
            while (ch!=6)
            {
                System.out.println("\n\n ************* Student Database *************");
                System.out.println("1.Add Student Data");
                System.out.println("2.Update Student Data");
                System.out.println("3.Delete Student Data");
                System.out.println("4.Display Students List");
                System.out.println("5.Get Student Details");
                System.out.println("6.Exit");
                System.out.print("Enter your Choice :- ");
                ch=scn.nextInt();
                switch (ch) {
                    case 1:
                        System.out.println("\n\n=================================== Insert Student Data ===================================");
                        System.out.print("Enter Student ID :  ");
                        String ids1=sct.nextLine();
                        System.out.print("Enter Student Name :  ");
                        String sname=sct.nextLine();
                        System.out.print("Enter Student Address :  ");
                        String saddr=sct.nextLine();
                        System.out.print("Enter Student Phone NO. :  ");
                        String sphn=sct.nextLine();
                        if(isNumeric(ids1)== false)
                        {
                            System.out.println("Invalid Input Type...!");
                        }
                        int result1 = stmt.executeUpdate("INSERT INTO stud(sid, sname, addr, phone) VALUES("+ids1+",'"+sname+"','"+saddr+"','"+sphn+"')");
                        if(result1==1) 
                        {
                            System.out.println("Inserted...");
                        }
                        else
                        {
                            System.out.println("Failed to Insert Data...!");
                        }
                        break;
                    case 2:
                        System.out.println("\n\n=================================== Update Student Data ===================================");
                        System.out.print("Enter Student ID :  ");
                        String ids2=sct.nextLine();
                        System.out.print("Enter New Address :  ");
                        String addr=sct.nextLine();
                        System.out.print("Enter New Phone NO. :  ");
                        String phn=sct.nextLine();
                        if(isNumeric(ids2)== false)
                        {
                            System.out.println("Invalid Input Type...!");
                        }
                        int result2=stmt.executeUpdate("UPDATE stud SET addr='"+addr+"' WHERE sid="+ids2);
                        int result22=stmt.executeUpdate("UPDATE stud SET phone='"+phn+"' WHERE sid="+ids2);
                        if(result2==1 && result22==1) 
                        {
                            System.out.println("Updated...");
                        }
                        else
                        {
                            System.out.println("Student Does not Exist...!");
                        }
                        break;
                    case 3:
                        System.out.println("\n\n=================================== Delete Student Data ===================================");
                        System.out.print("Enter Student ID :  ");
                        String ids3=sct.nextLine();
                        if(isNumeric(ids3)== false)
                        {
                            System.out.println("Invalid Input Type...!");
                        }
                        int result3 = stmt.executeUpdate("delete from stud where sid="+ids3+"");
                        if(result3==1) 
                        {
                            System.out.println("Deleted...");
                        }
                        else
                        {
                            System.out.println("Student Does not Exist...!");
                        }
                        break;

                    case 4:
                        System.out.println("\n\n=================================== Students List ===================================");
                        ResultSet rs4=stmt.executeQuery("select * from stud");  
                        System.out.println("Student NAME"); 
                        while(rs4.next())  
                        System.out.println(rs4.getString(2)); 
                        break;

                    case 5:
                        System.out.println("\n\n=================================== Get Students Details ===================================");
                        System.out.print("Enter Student ID :  ");
                        String ids=sct.nextLine();
                        if(isNumeric(ids)== false)
                        {
                            System.out.println("Invalid Input Type...!");
                        }
                        ResultSet rs5=stmt.executeQuery("select * from stud where sid="+ids+"");  
                        while(rs5.next())  
                        System.out.println("\n"+rs5.getInt(1)+"        "+rs5.getString(2)+"      "+rs5.getString(3)+"      "+rs5.getString(4)); 
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid Choice...!");
                        break;
                }//switch close

            }//while close
            con.close();
            scn.close();
            sct.close();
        }//try close
        catch(Exception e)
        {
            System.out.println(e);
        }//catch close

            
    }//main close

    public static boolean isNumeric(String str) {
        return str != null && str.matches("[-+]?\\d*\\.?\\d+");
    }

}//class close