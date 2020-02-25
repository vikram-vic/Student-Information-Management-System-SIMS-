package sims;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

//import java.sql.ResultSet;

public class DatabaseIO 
{
    Connection myConn = null;
    Statement stmt = null;
    PreparedStatement prepStmt = null;
    //ResultSet res = null;
    
    String schemaName = "studentinfoschema";
    
    public DatabaseIO() 
    {
        try
        {
            myConn = DatabaseCon.connect();
            stmt = myConn.createStatement();
            createTables();               
        }
        
        catch(SQLException e)
        {
           System.out.println("Connection could not be established!"); 
           System.err.println(e.getMessage());
        }
    }
    
    
    final public void createTables()
    {
        String cr8schema = "CREATE SCHEMA " + schemaName;
        
        String basicInfo =  "CREATE TABLE IF NOT EXISTS" + schemaName +".basic_info ("+ 
                            "basic_info_Name VARCHAR(30) NOT NULL, "+
                            "ClgId VARCHAR(10) NOT NULL, "+
                            "DOB DATE NOT NULL, "+
                            "Course VARCHAR(15) NOT NULL, "+
                            "CurrentSem INT(1) UNSIGNED NOT NULL, "+
                            "Contact VARCHAR(10) NOT NULL, "+
                            "PRIMARY KEY (basic_info_Name);";
        
        String sscInfo =    "CREATE TABLE IF NOT EXISTS" + schemaName +".ssc_info ("+
                            "basic_info_Name VARCHAR(30) NULL,"+
                            "PassYear INT(4) UNSIGNED NOT NULL,"+
                            "RollNo INT(10) UNSIGNED NOT NULL,"+
                            "Board VARCHAR(5) NOT NULL,"+
                            "Medium VARCHAR(10) NOT NULL,"+
                            "School VARCHAR(30) NOT NULL,"+
                            "City VARCHAR(10) NOT NULL,"+
                            "Percentage INT(3) UNSIGNED NOT NULL,"+
                            "INDEX Name_idx (basic_info_Name ASC) VISIBLE, "+ //ASC = Ascending
                            "CONSTRAINT FK_SName FOREIGN KEY (basic_info_Name) REFERENCES "+
                            ""+ schemaName +".basic_info (basic_info_Name) "+
                            "ON DELETE CASCADE "+
                            "ON UPDATE CASCADE);";
        
        String hsscInfo = "CREATE TABLE IF NOT EXISTS" + schemaName +".hssc_info ("+
                          "basic_info_Name VARCHAR(30) NULL, "+
                          "PassYear INT(4) UNSIGNED NULL, "+
                          "RollNo INT(10) UNSIGNED NULL, "+
                          "Board VARCHAR(5) NULL, "+
                          "Medium VARCHAR(10) NULL, "+
                          "School VARCHAR(30) NULL, "+
                          "City VARCHAR(10) NULL, "+
                          "Percentage INT(3) UNSIGNED NULL, "+
                          "MathPercentage INT(3) UNSIGNED NULL, "+
                          "ChemistryPercentage INT(3) UNSIGNED NULL, "+
                          "PhysicsPercentage INT(3) NULL, "+
                          "INDEX Name_idx (basic_info_Name ASC), "+
                          "CONSTRAINT FK_HName FOREIGN KEY (basic_info_Name) "+
                          "REFERENCES "+ schemaName +".basic_info (basic_info_Name) "+
                          "ON DELETE CASCADE " +
                          "ON UPDATE CASCADE);";
        
        String dipInfo = "CREATE TABLE IF NOT EXISTS" + schemaName +".diploma_info (" +
                         "basic_info_Name VARCHAR(30) NULL, "+
                         "PassYear INT(4) UNSIGNED NULL, "+
                         "School VARCHAR(30) NULL, "+
                         "City VARCHAR(10) NULL, "+
                         "Percentage1 INT(3) UNSIGNED NULL, "+
                         "Percentage2 INT(3) UNSIGNED NULL, "+
                         "Percentage3 INT(3) UNSIGNED NULL, "+
                         "Percentage4 INT(3) UNSIGNED NULL, "+
                         "Percentage5 INT(3) UNSIGNED NULL, "+
                         "Percentage6 INT(3) UNSIGNED NULL, "+
                         "INDEX Name_idx (basic_info_Name ASC), "+
                         "CONSTRAINT FK_DName FOREIGN KEY (basic_info_Name) "+
                         "REFERENCES "+ schemaName +".basic_info (basic_info_Name) "+
                         "ON DELETE CASCADE "+
                         "ON UPDATE CASCADE );";
        
        String uInfo = "CREATE TABLE IF NOT EXISTS" + schemaName +".ug_info (" +
                       "basic_info_Name VARCHAR(30) NULL," +
                       "School VARCHAR(30) NULL," +
                       "City VARCHAR(10) NULL," +
                       "Percentage1 INT(3) UNSIGNED NULL," +
                       "Percentage2 INT(3) UNSIGNED NULL," +
                       "Percentage3 INT(3) UNSIGNED NULL," +
                       "Percentage4 INT(3) UNSIGNED NULL," +
                       "Percentage5 INT(3) UNSIGNED NULL," +
                       "Percentage6 INT(3) UNSIGNED NULL," +
                       "Percentage7 INT(3) UNSIGNED NULL," +
                       "Percentage8 INT(3) UNSIGNED NULL," +
                       "INDEX Name_idx (basic_info_Name ASC), "+
                       "CONSTRAINT FK_UName FOREIGN KEY (basic_info_Name) "+
                       "REFERENCES "+ schemaName +".basic_info (basic_info_Name) "+
                       "ON DELETE CASCADE " +
                       "ON UPDATE CASCADE);";
        
        String pInfo = "CREATE TABLE IF NOT EXISTS" + schemaName +".pg_info (" +
                       "basic_info_Name VARCHAR(30) NULL, "+
                       "School VARCHAR(30) NULL, "+
                       "City VARCHAR(10) NULL, "+
                       "Percentage1 INT(3) UNSIGNED NULL, "+
                       "Percentage2 INT(3) UNSIGNED NULL, "+
                       "Percentage3 INT(3) UNSIGNED NULL, "+
                       "Percentage4 INT(3) UNSIGNED NULL, "+
                       "INDEX Name_idx (basic_info_Name ASC), "+
                       "CONSTRAINT FK_PName FOREIGN KEY (basic_info_Name) "+
                       "REFERENCES "+ schemaName +".basic_info (basic_info_Name) "+
                       "ON DELETE CASCADE "+
                       "ON UPDATE CASCADE);";
        
        String oInfo = "CREATE TABLE IF NOT EXISTS" + schemaName +".other_info (" +
                       "basic_info_Name VARCHAR(30) NULL, "+
                       "CollegeEmail VARCHAR(50) NOT NULL, "+
                       "PersonalEmail VARCHAR(50) NULL, "+
                       "FatherContact VARCHAR(10) NOT NULL, "+
                       "FamilyContact VARCHAR(10) NULL, "+
                       "AdditionalInfo TEXT NULL, "+
                       "INDEX Name_idx (basic_info_Name ASC), "+
                       "UNIQUE INDEX PEmail_UNIQUE (PersonalEmail ASC), "+
                       "UNIQUE INDEX CEmail_UNIQUE (CollegeEmail ASC), "+
                       "CONSTRAINT FK_OName FOREIGN KEY (basic_info_Name) "+
                       "REFERENCES "+ schemaName +".basic_info (basic_info_Name) "+                                       
                       "ON DELETE CASCADE "+
                       "ON UPDATE CASCADE);";
        
        try
        { 
            stmt.executeUpdate(cr8schema);  
            stmt.executeUpdate(basicInfo);
            stmt.executeUpdate(sscInfo);
            stmt.executeUpdate(hsscInfo);
            stmt.executeUpdate(dipInfo);
            stmt.executeUpdate(uInfo);
            stmt.executeUpdate(pInfo);
            stmt.executeUpdate(oInfo);
            
            System.out.println("Tables Created");
        }
       
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
               
    }
    
    ////////////////////////////////////////////////////////////////////
    //Insert Values
    
    void InsertValues()
    {
        Editing1Controller Edit1 = new Editing1Controller();
        
        /*String query = "insert into " + schemaName+".basic_info VALUES(" 
                + Edit1.getNameVal(),  
            + ","+ s2 +","+ s3 +","+ s4 +","+ s5 +","+ s6 +","+ s7 + ");"*/
        
        
        
    }
    
    
    
    
    
    void setBasicinfo(String s1, String s2, String s3, String s4, int s5, 
            String s6, String s7)
    {   String query = "insert into studentinfoschema.basic_info VALUES(" + s1 
            + ","+ s2 +","+ s3 +","+ s4 +","+ s5 +","+ s6 +","+ s7 + ");";
        try
        {
            prepStmt = myConn.prepareCall(query);
            prepStmt.executeUpdate();
            prepStmt = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    void setSscinfo(String s1, int s2, int s3, String s4, String s5, String s6, 
            String s7, int s8)
    {
        
        String query = "insert into studentinfoschema.ssc_info VALUES(" + s1 
                + "," + s2 + "," + s3 + "," + s4 + "," + s5 + "," + s6 + "," 
                + s7 + "," + s8 + ");";
        try
        {
            prepStmt = myConn.prepareCall(query);
            prepStmt.executeUpdate();
            prepStmt = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }   
    }
    
    void setHsscinfo(String s1, int s2, int s3, String s4, String s5, String s6,
             String s7, int s8, int s11, int s10, int s9)
    {
        String query = "insert into studentinfoschema.hssc_info VALUES(" + s1 
                + "," + s2 + "," + s3  +"," + s4 + "," + s5 + "," + s6 + "," 
                + s7 + "," + s8 + "," + s11 + "," + s10 + "," + s9 + ");";
        try
        {
            prepStmt = myConn.prepareCall(query);
            prepStmt.executeUpdate();
            prepStmt = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }   
    }
    
    void setDipinfo(String s1, int s2, int s3, int s4, int s5, int s6, int s7)
    {
        String query = "insert into studentinfoschema.diploma_info VALUES(" + s1 
                + ", " + s2 + ", " + s3 + ", " + s4 + ", " + s5 + ", " + s6 
                + ", " + s7 + ");";
        try
        {
            prepStmt = myConn.prepareCall(query);
            prepStmt.executeUpdate();
            prepStmt = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }   
    }
    
    void setUginfo(String s1, int s2, int s3, int s4, int s5, int s6, int s7, 
            int s8, int s9)
    {
        String query = "insert into studentinfoschema.ug_info VALUES (" + s1 
                + ", " + s2 + ", " + s3 + ", " + s4 + ", " + s5 + ", " + s6 
                + ", " + s7 + ", " + s8 +", "+ s9 + " );";
        try
        {
            prepStmt = myConn.prepareCall(query);
            prepStmt.executeUpdate();
            prepStmt = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }   
    }
    
    void setPginfo(String s1, int s2, int s3, int s4, int s5)
    {
        String query = "insert into studentinfoschema.pg_info VALUES(" + s1 + ", " 
                + s2 + ", " + s3 + ", " + s4 + ", " + s5 + ");";
        try
        {
            prepStmt = myConn.prepareCall(query);
            prepStmt.executeUpdate();
            prepStmt = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }   
    }
    
    void setUlinfo(String s1, String s2, String s3, String s4, String s5, 
            String s6, String s7)
    {
        String query = "insert into studentinfoschema.other_info VALUES (" + s1
                + "," + s2 + "," + s3 + "," + s4 + "," + s5 + "," + s6 + "," 
                + s7 + ");";
        
        try
        {
            prepStmt = myConn.prepareCall(query);
            prepStmt.executeUpdate();
            prepStmt = null;
            System.out.println("sent!");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        } 
    }
    
    //////////////////////////////Update Table////////////////////////////////    
    /*void UpBasicinfo(String s1, String s2, String s3, String s4, int s5, String s6, String s7)
    {   
        
        String query = "UPDATE studentinfoschema.basic_info SET name = " + s1 
                + ", clgID = " + s2 + ", dept = " + s3 + ", cedu = " + s4 
                + ", csem = " + s5 + ", contact = " + s6 + ", dob = " + s7 
                + " WHERE name = '" + Editing1Controller.oldName + "';";
        try
        {
            prepStmt = myConn.prepareCall(query);
            prepStmt.executeUpdate();
            prepStmt = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    void UpSscinfo(String s1, int s2, int s3, String s4, String s5, String s6, String s7, int s8)
    {   
        System.out.println(s4);
        String sql = "UPDATE studentinfoschema.ssc_info SET name = " + s1 + ", Syop = " 
                + s2 + ", Srn = " + s3 + ", SBoard = " + s4 + ", SMedium = " + s5 
                + ", SSch = " + s6 + ", Scity = " + s7 + " WHERE name = '" 
                + Editing1Controller.oldName +"';";
        try
        {
            prepStmt = myConn.prepareCall(sql);
            prepStmt.executeUpdate();
            prepStmt = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    void UpHsscinfo(String s1, int s2, int s3, String s4, String s5, String s6, 
            String s7, int s8, int s11, int s10, int s9)
    {   
        String sql = "UPDATE studentinfoschema.hssc_info SET name = " + s1 + ", Hyop = " 
                + s2 + ", Hrn = " + s3 + ", HBoard = " + s4 + ", HMedium = " + s5 
                + ", HSch = " + s6 + ", Hcity = " + s7 + ", Hptg = " + s8 
                + ", math = " + s11 + ", chemistry = " + s10 + ", physics = " + s9 
                + " WHERE name = '" + Editing1Controller.oldName + "';";
        try
        {
            prepStmt = myConn.prepareCall(sql);
            prepStmt.executeUpdate();
            prepStmt = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    void UpDipinfo(String s1, int s2, int s3, int s4, int s5, int s6, int s7)
    {   
        String sql = "UPDATE studentinfoschema.diploma_info SET name =" + s1 
                + ", Dptng1 = " + s2 + ", Dptng2 = " + s3 + ", Dptng3 = " + s4 
                + ", Dptng4 = " + s5 + ", Dptng5 = " + s6 + ", Dptng6 = " + s7 
                + " WHERE name = '" + Editing1Controller.oldName + "';";
        try
        {
            prepStmt = myConn.prepareCall(sql);
            prepStmt.executeUpdate();
            prepStmt = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    void UpUginfo(String s1, int s2, int s3, int s4, int s5, int s6, int s7, 
            int s8, int s9)
    {   
        String sql = "UPDATE studentinfoschema.ug_info SET name = " + s1 
                + ", Eptng1 = " + s2 + ", Eptng2 = " + s3 + ", Eptng3 = " + s4 
                + ", Eptng4 = " + s5 + ", Eptng5 = " + s6 + ", Eptng6 = " + s7 
                + ", Eptng7 = " + s8 + ", Eptng8 = " + s9 + " WHERE name = '" 
                + Editing1Controller.oldName + "';";
        try
        {
            prepStmt = myConn.prepareCall(sql);
            prepStmt.executeUpdate();
            prepStmt = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    void UpPginfo(String s1, int s2, int s3, int s4, int s5)
    {   
        String sql = "UPDATE studentinfoschema.pg_info SET name = " + s1 
                + ", Pptng1 = " + s2 + ", Pptng2 = " + s3 + ", Pptng3 = " + s4 
                + ", Pptng4 = " + s5 + " WHERE name = '" + Editing1Controller.oldName + "';";
        try
        {
            prepStmt = myConn.prepareCall(sql);
            prepStmt.executeUpdate();
            prepStmt = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    void UpUlinfo(String s1, String s2, String s3, String s4, String s5, String s6, String s7)
    {   
       
        String sql = "UPDATE studentinfoschema.other_info SET name = " + s1 
                + ", Cemail = " + s2 + ", PEmail = " + s3 + ", FatherCntct = " 
                + s4 + ", FamCntct = " + s5 + ", certification = " + s6 + ", AddInfo = " 
                + s7 + " WHERE name = '" + Editing1Controller.oldName + "';";
        try
        {
            prepStmt = myConn.prepareCall(sql);
            prepStmt.executeUpdate();
            prepStmt = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    
    //////////////////////////////Delete Table///////////////////////////// 
    void DelBasicinfo()
    {   String sql = "DELETE FROM studentinfoschema.basic_info WHERE name = '" 
            + Editing1Controller.oldName + "' ;";
        try
        {
            prepStmt = myConn.prepareCall(sql);
            prepStmt.executeUpdate();
            prepStmt = null;
        }
        catch(Exception e)
        {
            
            e.printStackTrace();
        }
    }*/
    
}
