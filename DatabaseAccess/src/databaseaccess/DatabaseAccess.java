/**
 *
 *Test program for database access in Java
 *
*/

package databaseaccess;

import java.io.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author Davi
 */


class DatabaseShow {
    //Prints all entries in the database
    static void printAllEntries(){
        try {
            String host = "jdbc:derby://localhost:1527/sample";
            String uName = "app";
            String uPass = "app";

            Connection con = DriverManager.getConnection(host, uName, uPass);
            
            Statement stmt = con.createStatement();
            String SQL = "SELECT * FROM CUSTOMER";
            
            ResultSet rs = stmt.executeQuery(SQL);
            
            int c_id;
            String name;
            String email;
            int limit;
            
            while(rs.next()){
                c_id = rs.getInt(1);
                name = rs.getString(4);
                email = rs.getString(11);
                limit = rs.getInt(12);
                
                System.out.println(c_id + " " + name + " " + email + " " + limit);
            }
            
            stmt.close();
            rs.close();
            
        } catch (SQLException err) {
            System.out.print(err.getMessage());
        }
    }    
}

class DatabaseEdit{
    //Inserts Davi Lima Paulino into database
    static void insertNewEntry(){
        try {
            String host = "jdbc:derby://localhost:1527/sample";
            String uName = "app";
            String uPass = "app";

            Connection con = DriverManager.getConnection(host, uName, uPass);
            
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String SQL = "SELECT * FROM CUSTOMER";
            
            ResultSet rs = stmt.executeQuery(SQL);
            
            rs.moveToInsertRow();
            
            rs.updateInt(1, 235);
            rs.updateString(2, "N");
            rs.updateString(3, "10096");
            rs.updateString(4, "Davi Lima Paulino");
            rs.updateString(5, "Road Street 23");
            //rs.updateString(6, "Apt 5");
            rs.updateString(7, "City");
            rs.updateString(8, "ST");
            rs.updateString(9, "905-884-2178");
            rs.updateString(10, "905-884-2178");
            rs.updateString(11, "davilimap@gmail.com");
            rs.updateInt(12, 999999999);
            
            rs.insertRow();
            
            stmt.close();
            rs.close();
            
        } catch (SQLException err) {
            System.out.print(err.getMessage());
        }
    }
    
    //Changes Davi Lima Paulino to Bob Smith
    static void editEntry(){
        try {
            String host = "jdbc:derby://localhost:1527/sample";
            String uName = "app";
            String uPass = "app";

            Connection con = DriverManager.getConnection(host, uName, uPass);
            
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String SQL = "SELECT * FROM CUSTOMER";
            
            ResultSet rs = stmt.executeQuery(SQL);
            
            if(rs.last()){
                rs.updateString(4, "Bob Smith");
                rs.updateRow();
            }
            
            stmt.close();
            rs.close();
            
        } catch (SQLException err) {
            System.out.print(err.getMessage());
        }
    }
    
    //Deletes the last entry in the database
    static void deleteEntry(){
        try {
            String host = "jdbc:derby://localhost:1527/sample";
            String uName = "app";
            String uPass = "app";

            Connection con = DriverManager.getConnection(host, uName, uPass);
            
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String SQL = "SELECT * FROM CUSTOMER";
            
            ResultSet rs = stmt.executeQuery(SQL);
            
            if(rs.last()){
                rs.deleteRow();
            }
            
            stmt.close();
            rs.close();
            
        } catch (SQLException err) {
            System.out.print(err.getMessage());
        }
    }
}

public class DatabaseAccess {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        BufferedReader stream = new BufferedReader(new InputStreamReader(System.in));
        
        //First, print all of the entries to show intial database state
        DatabaseShow.printAllEntries();
        stream.readLine();
        
        //Insert new entry and print all entries to show change
        DatabaseEdit.insertNewEntry();
        DatabaseShow.printAllEntries();
        stream.readLine();
        
        //Edit the inserted entry and prints to show changes
        DatabaseEdit.editEntry();
        DatabaseShow.printAllEntries();
        stream.readLine();
        
        //Deletes the inserted entry and prints to show the changes
        DatabaseEdit.deleteEntry();
        DatabaseShow.printAllEntries();
        stream.readLine();
    }
    
}
