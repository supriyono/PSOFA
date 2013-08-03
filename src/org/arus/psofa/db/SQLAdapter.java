package org.arus.psofa.db;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Supri
 */
public class SQLAdapter {
    private static final String CLASS_PATH = "org.arus.psofa.db.SQLAdapter";
    private static final String CLASS_NAME = "org.sqlite.JDBC";
    private static final String DB_NAME = "database.db";
    private static final String url = "jdbc:sqlite:".concat(DB_NAME);
    private Connection con;
    private Statement stmt;
    private ResultSet rs;
    
    public SQLAdapter(){
        initDB();
    }
    
    private void initDB(){
        try{
            if(con.isClosed() || con == null){
                Class.forName(CLASS_NAME);
                con = DriverManager.getConnection(url);
                System.out.println("Connection to  " + DB_NAME + " has been succesfully initiated.");
            } else
                System.err.println("ERROR: Fail to initiate a connection to: " + DB_NAME + ", the Connection is still open!");
        } catch(ClassNotFoundException e){
            System.err.println(CLASS_PATH + ".initDB, " + e.getClass().getName() + ": " + e.getMessage());
        } catch(SQLException e){
            System.err.println(CLASS_PATH + ".initDB, " + e.getClass().getName() + ": " + e.getMessage());
        } catch(Exception e){
            System.err.println(CLASS_PATH + ".initDB, " + e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    private void closeDB(){
        try{
            con.close();
            if(!con.isClosed()){
                System.err.println("ERROR: Failed to close the connection to: " + DB_NAME + "!");
            } else
                System.out.println("Connection to " + DB_NAME + " is closed.");
        } catch(SQLException e){
            System.err.println(CLASS_PATH + ".closeDB, " + e.getClass().getName() + ": " + e.getMessage());
        } catch(Exception e){
            System.err.println(CLASS_PATH + ".closeDB, " + e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    public void createTable(String tableName, ArrayList<String> fields, ArrayList<String> datatypes){
        try{
            initDB();
            stmt = con.createStatement();
            
            String sqlCommand = "CREATE TABLE ";
            String stmtBody = null;
            for(int i=0; i<fields.size(); i++){
                if(i == 0){
                    stmtBody = fields.get(i) + " " + datatypes.get(i) + " PRIMARY KEY  NOT NULL";
                } else{
                    stmtBody += ", " + fields.get(i) + " " + datatypes.get(i) + " NOT NULL";
                }
            }
            String sqlStatement = sqlCommand + tableName + " (" + stmtBody + ");";
            
            stmt.executeUpdate(sqlStatement);
            stmt.close();
        } catch(IndexOutOfBoundsException e){
            System.err.println(CLASS_PATH + ".createTable, " + e.getClass().getName() + ": " + e.getMessage());
        } catch (SQLException e){
            System.err.println(CLASS_PATH + ".createTable, " + e.getClass().getName() + ": " + e.getMessage());
        } catch(Exception e){
            System.err.println(CLASS_PATH + ".createTable, " + e.getClass().getName() + ": " + e.getMessage());
        } finally{
            closeDB();
        }
    }
    
    public void dropTable(String tableName){
        try{
            initDB();
            stmt = con.createStatement();
            String sqlStatement = "DROP TABLE " + tableName + ";";
            stmt.executeUpdate(sqlStatement);
            stmt.close();
        } catch (SQLException e){
            System.err.println(CLASS_PATH + ".dropTable, " + e.getClass().getName() + ": " + e.getMessage());
        } catch(Exception e){
            System.err.println(CLASS_PATH + ".dropTable, " + e.getClass().getName() + ": " + e.getMessage());
        } finally{
            closeDB();
        }
    }
    
    public void addRecord(String tableName, HashMap<String, String> record){
        try{
            initDB();
            con.setAutoCommit(false);
            stmt = con.createStatement();
            
            String sqlCommand = "INSERT INTO ";
            String recordKeys = null;
            String recordValues = null;
            String[] keys = record.keySet().toArray(new String[0]);
            for(int i=0; i<keys.length; i++){
                if(i != keys.length - 1){
                    recordKeys += keys[i] + ", ";
                    if(keys[i].compareToIgnoreCase("TEXT") == 0)
                        recordValues += "'" + record.get(keys[i]) + "', ";
                    else
                        recordValues += record.get(keys[i]) + ", ";
                }
                else{
                    recordKeys += keys[i];
                    if(keys[i].compareToIgnoreCase("TEXT") == 0)
                        recordValues += "'" + record.get(keys[i]) + "'";
                    else
                        recordValues += record.get(keys[i]);
                }
            }
            String sqlStatement = sqlCommand + tableName + " (" + recordKeys + ") VALUES(" + recordValues + ");";
            
            stmt.executeUpdate(sqlStatement);
            con.commit();
            stmt.close();
        } catch(SQLException e){
            System.err.println(CLASS_PATH + ".addRecord, " + e.getClass().getName() + ": " + e.getMessage());
        } catch(ArrayStoreException e){
            System.err.println(CLASS_PATH + ".addRecord, " + e.getClass().getName() + ": " + e.getMessage());
        } catch(NullPointerException e){
            System.err.println(CLASS_PATH + ".addRecord, " + e.getClass().getName() + ": " + e.getMessage());
        } catch(Exception e){
            System.err.println(CLASS_PATH + ".addRecord, " + e.getClass().getName() + ": " + e.getMessage());
        } finally{
            closeDB();
        }
    }
    
    public void deleteRecord(String tableName, String id, String idValue){
        try{
            initDB();
            con.setAutoCommit(false);
            stmt = con.createStatement();
            
            String sqlStatement;
            if(id.compareToIgnoreCase("TEXT") == 0)
                sqlStatement = "DELETE FROM " + tableName + " WHERE " + id + " = '" + idValue + "';";
            else
                sqlStatement = "DELETE FROM " + tableName + " WHERE " + id + " = " + idValue + ";";
            
            stmt.executeUpdate(sqlStatement);
            con.commit();
            stmt.close();
        } catch(SQLException e){
            System.err.println(CLASS_PATH + ".deleteRecord, " + e.getClass().getName() + ": " + e.getMessage());
        } catch(Exception e){
            System.err.println(CLASS_PATH + ".deleteRecord, " + e.getClass().getName() + ": " + e.getMessage());
        } finally{
            closeDB();
        }
    }
    
    public void updateRecord(String tableName, HashMap<String, String> record, String condition){
        try{
            initDB();
            con.setAutoCommit(false);
            stmt = con.createStatement();
            
            String sqlCommand = "UPDATE ";
            String stmtBody = null;
            String[] keys = record.keySet().toArray(new String[0]);
            for(int i=0; i<keys.length; i++){
                if(stmtBody == null)
                    stmtBody += keys[i] + " = " + record.get(keys[i]);
                else
                    stmtBody += ", " + keys[i] + " = " + record.get(keys[i]);
            }
            String sqlStatement = sqlCommand + tableName + " SET " + stmtBody + " WHERE " + condition + ";";
            
            stmt.executeUpdate(sqlStatement);
            con.commit();
            stmt.close();
        } catch(SQLException e){
            System.err.println(CLASS_PATH + ".updateRecord, " + e.getClass().getName() + ": " + e.getMessage());
        } catch(ArrayStoreException e){
            System.err.println(CLASS_PATH + ".updateRecord, " + e.getClass().getName() + ": " + e.getMessage());
        } catch(NullPointerException e){
            System.err.println(CLASS_PATH + ".updateRecord, " + e.getClass().getName() + ": " + e.getMessage());
        } catch(Exception e){
            System.err.println(CLASS_PATH + ".updateRecord, " + e.getClass().getName() + ": " + e.getMessage());
        } finally{
            closeDB();
        }
    }
    
    public HashMap<String, ArrayList<String>> getTableContent(String tableName){
        HashMap<String, ArrayList<String>> tableContent = new HashMap<String, ArrayList<String>>();
        try{
            initDB();
            con.setAutoCommit(false);
            stmt = con.createStatement();
            
            String sqlStatement = "SELECT * FROM " + tableName + ";";
            rs = stmt.executeQuery(sqlStatement);
            
            ResultSetMetaData metaData = rs.getMetaData();
            int columnSize = metaData.getColumnCount();
            ArrayList<String> columns = new ArrayList<String>();
            ArrayList<String> types = new ArrayList<String>();
            for(int i=columnSize; i>0; i--){
                String columnName = metaData.getColumnLabel(i);
                String type = metaData.getColumnClassName(i);
                tableContent.put(columnName, new ArrayList<String>());
                columns.add(columnName);
                types.add(type);
            }
            
            while(rs.next()){
                for(int i=0; i<columnSize; i++){
                    String label = columns.get(i);
                    ArrayList<String> newValue = tableContent.get(label);
                    if("TEXT".compareToIgnoreCase(types.get(i)) == 0)
                        newValue.add(rs.getString(label));
                    else
                        newValue.add((String.valueOf(rs.getInt(label))));
                    tableContent.put(label, newValue);
                }
            }
            
            rs.close();
            stmt.close();
        } catch(SQLException e){
            System.err.println(CLASS_PATH + ".getTableContent, " + e.getClass().getName() + ": " + e.getMessage());
        } catch(Exception e){
            System.err.println(CLASS_PATH + ".getTableContent, " + e.getClass().getName() + ": " + e.getMessage());
        } finally{
            closeDB();
            return tableContent;
        }
    }
    
    public HashMap<String, String> getRecord(String tableName, String primaryKey, String keyValue){
        HashMap<String, String> record = new HashMap<String, String>();
        try{
            initDB();
            con.setAutoCommit(false);
            stmt = con.createStatement();
            
            String sqlStatement = "SELECT * FROM " + tableName + " WHERE " + primaryKey + " = " + keyValue + ";";
            rs = stmt.executeQuery(sqlStatement);
            
            ResultSetMetaData  rsm = rs.getMetaData();
            int columnSize = rsm.getColumnCount();
            ArrayList<String> columns = new ArrayList<String>();
            for(int i=columnSize; i>0; i++){
                String key = rsm.getColumnLabel(i);
                if(rsm.getColumnClassName(i).compareToIgnoreCase("TEXT") == 0)
                    record.put(key, rs.getString(key));
                else
                    record.put(key, String.valueOf(rs.getInt(key)));
            }
            
            rs.close();
            stmt.close();
        } catch(SQLException e){
            System.err.println(CLASS_PATH + ".getRecord, " + e.getClass().getName() + ": " + e.getMessage());
        } catch(Exception e){
            System.err.println(CLASS_PATH + ".getRecord, " + e.getClass().getName() + ": " + e.getMessage());
        } finally{
            closeDB();
            return record;
        }
    }
    
    public ArrayList getColumn(String tableName, String columnLabel){
        ArrayList column = null;
        try{
            initDB();
            con.setAutoCommit(false);
            stmt = con.createStatement();
            
            String sqlStatement = "SELECT " + columnLabel + " FROM " + tableName + ";";
            rs = stmt.executeQuery(sqlStatement);
            
            ResultSetMetaData rsm = rs.getMetaData();
            if(rsm.getColumnClassName(1).compareToIgnoreCase("TEXT") == 0){
                column = new ArrayList<String>();
                while(rs.next()){
                    column.add(rs.getString(columnLabel));
                }
            } else{
                column = new ArrayList<Integer>();
                while(rs.next()){
                    column.add(rs.getInt(columnLabel));
                }
            }
            
            rs.close();
            stmt.close();
        } catch(SQLException e){
            System.err.println(CLASS_PATH + ".getColumn, " + e.getClass().getName() + ": " + e.getMessage());
        } catch(Exception e){
            System.err.println(CLASS_PATH + ".getColumn, " + e.getClass().getName() + ": " + e.getMessage());
        } finally{
            closeDB();
            return column;
        }
    }
    
    public HashMap<String, ArrayList<String>> getColumns(String tableName, String[] columnlabels){
        HashMap<String, ArrayList<String>> columns = new HashMap<String, ArrayList<String>>();
        try{
            initDB();
            con.setAutoCommit(false);
            stmt = con.createStatement();
            
            String sqlStatement = "SELECT ";
            for(int i=0; i<columnlabels.length; i++){
                if(i!=0)
                    sqlStatement += ", " + columnlabels[i];
                else
                    sqlStatement += columnlabels;
            }
            sqlStatement += " FROM " + tableName + ";";
            rs = stmt.executeQuery(sqlStatement);
            
            ResultSetMetaData metaData = rs.getMetaData();
            int columnSize = metaData.getColumnCount();
            ArrayList<String> columnLabels = new ArrayList<String>();
            ArrayList<String> types = new ArrayList<String>();
            for(int i=columnSize; i>0; i--){
                String columnName = metaData.getColumnLabel(i);
                String type = metaData.getColumnClassName(i);
                columns.put(columnName, new ArrayList<String>());
                columnLabels.add(columnName);
                types.add(type);
            }
            
            while(rs.next()){
                for(int i=0; i<columnSize; i++){
                    String label = columnLabels.get(i);
                    ArrayList<String> newValue = columns.get(label);
                    if("TEXT".compareToIgnoreCase(types.get(i)) == 0)
                        newValue.add(rs.getString(label));
                    else
                        newValue.add((String.valueOf(rs.getInt(label))));
                    columns.put(label, newValue);
                }
            }
            
            rs.close();
            stmt.close();
        } catch(SQLException e){
            System.err.println(CLASS_PATH + ".getColumns, " + e.getClass().getName() + ": " + e.getMessage());
        } catch(Exception e){
            System.err.println(CLASS_PATH + ".getColumns, " + e.getClass().getName() + ": " + e.getMessage());
        } finally{
            closeDB();
            return columns;
        }
    }
    
    
    
    
    public static void main(String[] args){
        /*
        String createStatement = "CREATE TABLE CUSTOMER " + 
                "(CUSTOMER_ID  INT  PRIMARY KEY  NOT NULL," +
                " NAME  TEXT  NOT NULL," +
                " DISTRICT  TEXT  NOT NULL," +
                " ADDRESS  TEXT  NOT NULL," +
                " CITY  TEXT  NOT NULL," +
                " PROVINCE  TEXT  NOT NULL," +
                " COUNTRY  TEXT  NOT NULL," +
                " POSTAL_CODE  CHAR(6)  NOT NULL," +
                " PHONE  TEXT  NOT NULL," +
                " FAX  TEXT," +
                " EMAIL  TEXT);";
        
        String insertStatement1 = "INSERT INTO CUSTOMER " +
                "(CUSTOMER_ID,NAME,DISTRICT,ADDRESS,CITY,PROVINCE,COUNTRY,POSTAL_CODE,PHONE,FAX,EMAIL) " +
                "VALUES(1,'Point Grey Secondary School','School District #39','5350 East Blvd'," +
                "'Vancouver','B.C.','Canada','V6M3V2','+1 (604) 713-8220','+1 (604) 713-8218',NULL);";
        
        String insertStatement2 = "INSERT INTO CUSTOMER " +
                "(CUSTOMER_ID,NAME,DISTRICT,ADDRESS,CITY,PROVINCE,COUNTRY,POSTAL_CODE,PHONE,FAX,EMAIL) " +
                "VALUES(2,'Britannia Secondary School','School District #39','1001 Cotton Drive'," +
                "'Vancouver','B.C.','Canada','V5L3T4','+1 (604) 713-8266','1 (604) 713-8265',NULL);";
        
        String insertStatement3 = "INSERT INTO CUSTOMER " +
                "(CUSTOMER_ID,NAME,DISTRICT,ADDRESS,CITY,PROVINCE,COUNTRY,POSTAL_CODE,PHONE,FAX,EMAIL) " +
                "VALUES(3,'Britannia Secondary School','School District #39','3939 West 16th Avenue'," +
                "'Vancouver','B.C.','Canada','V5R3C9','+1 (604) 713-8171','1 (604) 713-8170',NULL);";
        
        String insertStatement4 = "INSERT INTO CUSTOMER " +
                "(CUSTOMER_ID,NAME,DISTRICT,ADDRESS,CITY,PROVINCE,COUNTRY,POSTAL_CODE,PHONE,FAX,EMAIL) " +
                "VALUES(4,'Point Grey Secondary School','School District #39','5350 East Blvd'," +
                "'Vancouver','B.C.','Canada','V6M3V2','+1 (604) 713-8220','+1 (604) 713-8218',NULL);";
        
        String updateStatement = "UPDATE CUSTOMER SET NAME = 'Lord Byng Secondary School' WHERE CUSTOMER_ID = 3;";
        
        String deleteStatement = "DELETE FROM CUSTOMER WHERE CUSTOMER_ID = 4;";
        
        String selectStatement = "SELECT * FROM CUSTOMER;";
        */
    }
}
