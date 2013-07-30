/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.arus.psofa.db;
import java.sql.*;
import java.util.ArrayList;
import org.arus.psofa.model.AccountInfo;
import org.arus.psofa.model.BillingInfo;

/**
 *
 * @author Supri
 */
public class SQLiteJDBC {
    private String className;
    private String fileName;
    private String url;
    private Connection con;
    private Statement stmt;
    private String sql;
    
    public SQLiteJDBC(String fileNameWithoutExtention){
        fileName = fileNameWithoutExtention;
        url = "jdbc:sqlite:".concat(fileName.concat(".db"));
        className = "org.sqlite.JDBC";
    }
    
    public void createAccountInfoTable(){
        try{
            Class.forName(className);
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            sql =   "CREATE TABLE ACCOUNT_INFO " +
                    "(ACCOUNT_ID  INT  PRIMARY KEY  NOT NULL, " +
                    "SCHOOL_NAME  TEXT  NOT NULL, " +
                    "SCHOOL_DISTRICT  TEXT  NOT NULL, " +
                    "COUNTRY  TEXT  NOT NULL, " +
                    "PROVINCE  TEXT  NOT NULL, " +
                    "CITY  TEXT  NOT NULL, " +
                    "ADDRESS  TEXT  NOT NULL, " +
                    "POSTAL_CODE  CHAR(7)  NOT NULL, " +
                    "PHONE_NUMBER  TEXT  NOT NULL, " +
                    "FAX_NUMBER  TEXT);";
            stmt.executeUpdate(sql);
            stmt.close();
            con.close();
        } catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
    public void createBillingInfoTable(){
        try{
            Class.forName(className);
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            sql =   "CREATE TABLE BILLING_INFO " +
                    "(ACCOUNT_ID  INT  PRIMARY KEY  NOT NULL, " +
                    "BILL_RECIPIENT  TEXT  NOT NULL, " +
                    "COUNTRY  TEXT  NOT NULL, " +
                    "PROVINCE  TEXT  NOT NULL, " +
                    "CITY  TEXT  NOT NULL, " +
                    "ADDRESS  TEXT  NOT NULL, " +
                    "POSTAL_CODE  CHAR(7)  NOT NULL, " +
                    "PHONE_NUMBER  TEXT  NOT NULL, " +
                    "FAX_NUMBER  TEXT);";
            stmt.executeUpdate(sql);
            stmt.close();
            con.close();
        } catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
    public ArrayList<AccountInfo> getAccountInfoTable(){
        ArrayList<AccountInfo> table;
        table = new ArrayList<>();
        try{
            Class.forName(className);
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ACCOUNT_INFO;");
            
            while(rs.next()){
                AccountInfo ai = new AccountInfo();
                ai.setAccNumber(rs.getInt("account_id"));
                ai.setAddress(rs.getString("address"));
                ai.setCity(rs.getString("city"));
                ai.setCountry(rs.getString("country"));
                ai.setFax(rs.getString("fax_number"));
                ai.setPhone(rs.getString("phone_number"));
                ai.setPostalCode(rs.getString("postal_code"));
                ai.setProvince(rs.getString("province"));
                ai.setSchoolDistrict(rs.getString("school_district"));
                ai.setSchoolName(rs.getString("school_name"));
                table.add(ai);
            }
            
            rs.close();
            stmt.close();
            con.close();
        } catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally{
            return table;
        }
    }
    
    public AccountInfo getAccountInfoWhichSchool(String schoolName){
        AccountInfo ai = new AccountInfo();
        try{
            Class.forName(className);
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            sql = "SELECT * FROM ACCOUNT_INFO WHERE SCHOOL_NAME = '" + schoolName + "';";
            ResultSet rs = stmt.executeQuery(sql);
            
            ai.setAccNumber(rs.getInt("account_id"));
            ai.setAddress(rs.getString("address"));
            ai.setCity(rs.getString("city"));
            ai.setCountry(rs.getString("country"));
            ai.setFax(rs.getString("fax"));
            ai.setPhone(rs.getString("phone"));
            ai.setPostalCode(rs.getString("postal_code"));
            ai.setProvince(rs.getString("province"));
            ai.setSchoolDistrict(rs.getString("school_district"));
            ai.setSchoolName(rs.getString("school_name"));
            
            rs.close();
            stmt.close();
            con.close();
        } catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally{
            return ai;
        }
    }
    
    public AccountInfo getAccountInfoWhichId(int accountNumber){
        AccountInfo ai = new AccountInfo();
        try{
            Class.forName(className);
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            sql = "SELECT * FROM ACCOUNT_INFO WHERE ACCOUNT_ID = '" + accountNumber + "';";
            ResultSet rs = stmt.executeQuery(sql);
            
            ai.setAccNumber(rs.getInt("account_id"));
            ai.setAddress(rs.getString("address"));
            ai.setCity(rs.getString("city"));
            ai.setCountry(rs.getString("country"));
            ai.setFax(rs.getString("fax"));
            ai.setPhone(rs.getString("phone"));
            ai.setPostalCode(rs.getString("postal_code"));
            ai.setProvince(rs.getString("province"));
            ai.setSchoolDistrict(rs.getString("school_district"));
            ai.setSchoolName(rs.getString("school_name"));
            
            rs.close();
            stmt.close();
            con.close();
        } catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally{
            return ai;
        }
    }
    
    public ArrayList<AccountInfo> getAccountInfoWhichCountry(String aCountry){
        ArrayList<AccountInfo> aList = new ArrayList<>();
        try{
            Class.forName(className);
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            sql = "SELECT * FROM ACCOUNT_INFO WHERE COUNTRY = '" + aCountry + "';";
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                AccountInfo ai = new AccountInfo();
                ai.setAccNumber(rs.getInt("account_id"));
                ai.setAddress(rs.getString("address"));
                ai.setCity(rs.getString("city"));
                ai.setCountry(rs.getString("country"));
                ai.setFax(rs.getString("fax"));
                ai.setPhone(rs.getString("phone"));
                ai.setPostalCode(rs.getString("postal_code"));
                ai.setProvince(rs.getString("province"));
                ai.setSchoolDistrict(rs.getString("school_district"));
                ai.setSchoolName(rs.getString("school_name"));
                aList.add(ai);
            }
            
            rs.close();
            stmt.close();
            con.close();
        } catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally{
            return aList;
        }
    }  
    
    public ArrayList<AccountInfo> getAccountInfoWhichProvince(String aProvince){
        ArrayList<AccountInfo> aList = new ArrayList<>();
        try{
            Class.forName(className);
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            sql = "SELECT * FROM ACCOUNT_INFO WHERE PROVINCE = '" + aProvince + "';";
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                AccountInfo ai = new AccountInfo();
                ai.setAccNumber(rs.getInt("account_id"));
                ai.setAddress(rs.getString("address"));
                ai.setCity(rs.getString("city"));
                ai.setCountry(rs.getString("country"));
                ai.setFax(rs.getString("fax"));
                ai.setPhone(rs.getString("phone"));
                ai.setPostalCode(rs.getString("postal_code"));
                ai.setProvince(rs.getString("province"));
                ai.setSchoolDistrict(rs.getString("school_district"));
                ai.setSchoolName(rs.getString("school_name"));
                aList.add(ai);
            }
            
            rs.close();
            stmt.close();
            con.close();
        } catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally{
            return aList;
        }
    }  
    
    public ArrayList<AccountInfo> getAccountInfoWhichCity(String aCity){
        ArrayList<AccountInfo> aList = new ArrayList<>();
        try{
            Class.forName(className);
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            sql = "SELECT * FROM ACCOUNT_INFO WHERE CITY = '" + aCity + "';";
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                AccountInfo ai = new AccountInfo();
                ai.setAccNumber(rs.getInt("account_id"));
                ai.setAddress(rs.getString("address"));
                ai.setCity(rs.getString("city"));
                ai.setCountry(rs.getString("country"));
                ai.setFax(rs.getString("fax"));
                ai.setPhone(rs.getString("phone"));
                ai.setPostalCode(rs.getString("postal_code"));
                ai.setProvince(rs.getString("province"));
                ai.setSchoolDistrict(rs.getString("school_district"));
                ai.setSchoolName(rs.getString("school_name"));
                aList.add(ai);
            }
            
            rs.close();
            stmt.close();
            con.close();
        } catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally{
            return aList;
        }
    }  
    
    public ArrayList<AccountInfo> getAccountInfoWhichDistrict(String schoolDistrict){
        ArrayList<AccountInfo> aList = new ArrayList<>();
        try{
            Class.forName(className);
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            sql = "SELECT * FROM ACCOUNT_INFO WHERE SCHOOL_DISTRICT = '" + schoolDistrict + "';";
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                AccountInfo ai = new AccountInfo();
                ai.setAccNumber(rs.getInt("account_id"));
                ai.setAddress(rs.getString("address"));
                ai.setCity(rs.getString("city"));
                ai.setCountry(rs.getString("country"));
                ai.setFax(rs.getString("fax"));
                ai.setPhone(rs.getString("phone"));
                ai.setPostalCode(rs.getString("postal_code"));
                ai.setProvince(rs.getString("province"));
                ai.setSchoolDistrict(rs.getString("school_district"));
                ai.setSchoolName(rs.getString("school_name"));
                aList.add(ai);
            }
            
            rs.close();
            stmt.close();
            con.close();
        } catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally{
            return aList;
        }
    }
    
    public ArrayList<BillingInfo> getBillingInfoTable(){
        ArrayList<BillingInfo> table;
        table = new ArrayList<>();
        try{
            Class.forName(className);
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM BILLING_INFO;");
            
            while(rs.next()){
                BillingInfo ai = new BillingInfo();
                ai.setAccNumber(rs.getInt("account_id"));
                ai.setAddress(rs.getString("address"));
                ai.setCity(rs.getString("city"));
                ai.setCountry(rs.getString("country"));
                ai.setFax(rs.getString("fax_number"));
                ai.setPhone(rs.getString("phone_number"));
                ai.setPostalCode(rs.getString("postal_code"));
                ai.setProvince(rs.getString("province"));
                ai.setRecipient(rs.getString("bill_recipient"));
                table.add(ai);
            }
            
            rs.close();
            stmt.close();
            con.close();
        } catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally{
            return table;
        }
    }
    
    public BillingInfo getBillingInfoById(int accountNumber){
        BillingInfo bi = new BillingInfo();
        try{
            Class.forName(className);
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            sql = "SELECT * FROM BILLING_INFO WHERE ACCOUNT_ID = " + accountNumber + ";";
            ResultSet rs = stmt.executeQuery(sql);
            
            bi.setAccNumber(rs.getInt("account_id"));
            bi.setAddress(rs.getString("address"));
            bi.setCity(rs.getString("city"));
            bi.setCountry(rs.getString("country"));
            bi.setFax(rs.getString("fax"));
            bi.setPhone(rs.getString("phone"));
            bi.setPostalCode(rs.getString("postal_code"));
            bi.setProvince(rs.getString("province"));
            bi.setRecipient(rs.getString("bill_recipient"));
            
            rs.close();
            stmt.close();
            con.close();
        } catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally{
            return bi;
        }
    }
    
    public int getAccountNumbersOfBillRecipient(String billRecipient){
        int accNumber = -1;
        try{
            Class.forName(className);
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            sql = "SELECT ACCOUNT_ID FROM BILLING_INFO WHERE BILL_RECIPIENT = " + billRecipient + ";";
            ResultSet rs = stmt.executeQuery(sql);
            
            accNumber = rs.getInt("account_id");
            
            rs.close();
            stmt.close();
            con.close();
        } catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally{
            return accNumber;
        }
    }
    
    public void addAccountInfo(AccountInfo ai){
        try{
            Class.forName(className);
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            
            sql = "INSERT INTO ACCOUNT_INFO (ACCOUNT_ID, SCHOOL_NAME, SCHOOL_DISTRICT, COUNTRY, PROVINCE, CITY, ADDRESS, POSTAL_CODE, PHONE_NUMBER, FAX_NUMBER) "
                    + "VALUES(" + ai.getAccNumber() 
                    + ", '" + ai.getSchoolName() + "'"
                    + ", '" + ai.getSchoolDistrict() + "'"
                    + ", '" + ai.getCountry() + "'"
                    + ", '" + ai.getProvince() + "'"
                    + ", '" + ai.getCity() + "'"
                    + ", '" + ai.getAddress() + "'"
                    + ", '" + ai.getPostalCode() + "'"
                    + ", '" + ai.getPhone() + "'"
                    + ", '" + ai.getFax() + "'"
                    + ");";
            stmt.executeUpdate(sql);
            
            stmt.close();
            con.commit();
            con.close();
        } catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
    public void addBillingInfo(BillingInfo bi){
        try{
            Class.forName(className);
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            
            sql = "INSERT INTO BILLING_INFO (ACCOUNT_ID, BILL_RECIPIENT, COUNTRY, PROVINCE, CITY, ADDRESS, POSTAL_CODE, PHONE_NUMBER, FAX_NUMBER) "
                    + "VALUES(" + bi.getAccNumber() 
                    + ", '" + bi.getRecipient() + "'"
                    + ", '" + bi.getCountry() + "'"
                    + ", '" + bi.getProvince() + "'"
                    + ", '" + bi.getCity() + "'"
                    + ", '" + bi.getAddress() + "'"
                    + ", '" + bi.getPostalCode() + "'"
                    + ", '" + bi.getPhone() + "'"
                    + ", '" + bi.getFax() + "'"
                    + ");";
            stmt.executeUpdate(sql);
            
            stmt.close();
            con.commit();
            con.close();
        } catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
    public void deleteAccountInfoRecord(int accountNumber){
        try{
            Class.forName(className);
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            
            sql = "DELETE FROM ACCOUNT_INFO WHERE ACCOUNT_ID = " + accountNumber + ";";
            stmt.executeUpdate(sql);
            con.commit();
            
            stmt.close();
            con.close();
        } catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } 
    }
    
    public void deleteAccountInfoRecord(String schoolName){
        try{
            Class.forName(className);
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            
            sql = "DELETE FROM ACCOUNT_INFO WHERE SCHOOL_NAME = '" + schoolName + "';";
            stmt.executeUpdate(sql);
            con.commit();
            
            stmt.close();
            con.close();
        } catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
    public void deleteBillingInfoRecord(int accountNumber){
        try{
            Class.forName(className);
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            
            sql = "DELETE FROM BILLING_INFO WHERE ACCOUNT_ID = " + accountNumber + ";";
            stmt.executeUpdate(sql);
            con.commit();
            
            stmt.close();
            con.close();
        } catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
    public void deleteBillingInfoRecord(String billRecipient){
        try{
            Class.forName(className);
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            
            sql = "DELETE FROM BILLING_INFO WHERE BILL_RECIPIENT = '" + billRecipient + "';";
            stmt.executeUpdate(sql);
            con.commit();   
            
            stmt.close();
            con.close();
        } catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
    public void executeUpdateStatement(String statement){
        if(statement.contains("INSERT") || statement.contains("UPDATE") || statement.contains("DELETE")){
            try{
                Class.forName(className);
                con = DriverManager.getConnection(url);
                con.setAutoCommit(false);
                stmt = con.createStatement();
                
                sql = statement;
                stmt.executeUpdate(sql);
                con.commit();
                
                stmt.close();
                con.close();
            } catch(ClassNotFoundException | SQLException e){
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        } else System.err.println("Statement is not doing any update (insert, delete, update) operation!");
    }
    
    
    public static void main(String[] args){
        SQLiteJDBC db = new SQLiteJDBC("database");
        //db.createBillingInfoTable();
        /*
        BillingInfo bi = new BillingInfo(000003, "Vancouver School Board (#39)", 
                "Canada", "British Columbia", "Vancouver", "1580 West Broadway", "V6J 5K8", 
                "+1 (604) 713-5000", null);
        db.addBillingInfo(bi);
        */
        ArrayList<AccountInfo> accountInfoTable = db.getAccountInfoTable();
        ArrayList<BillingInfo> billingInfoTable = db.getBillingInfoTable();
        for(AccountInfo info : accountInfoTable){
            System.out.println("SCHOOL NAME: " + info.getSchoolName());
            System.out.println("ACCOUNT NUMBER: " + info.getAccNumber());
            System.out.println("SCHOOL DISTRICT: " + info.getSchoolDistrict());
            System.out.println("ADDRESS: " + info.getAddress() + " " + info.getCity() + ", " + info.getProvince() + " " + info.getCountry() + " " + info.getPostalCode());
            System.out.println("PHONE: " + info.getPhone());
            System.out.println("FAX: " + info.getFax());
            System.out.println();
            System.out.println();
        }
        
        for(BillingInfo info : billingInfoTable){
            System.out.println("ACCOUNT NUMBER: " + info.getAccNumber());
            System.out.println("BILL RECIPIENT: " + info.getRecipient());
            System.out.println("ADDRESS: " + info.getAddress() + " " + info.getCity() + ", " + info.getProvince() + " " + info.getCountry() + " " + info.getPostalCode());
            System.out.println("PHONE: " + info.getPhone());
            System.out.println("FAX: " + info.getFax());
            System.out.println();
            System.out.println();
        }
    }  
}
