/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.arus.psofa.model;

/**
 *
 * @author Supri
 */
public class AccountInfo extends CustomerInfo{
    private String schoolName;
    private String schoolDistrict;
    
    public AccountInfo(){
        super();
        schoolName = null;
        schoolDistrict = null;
    }
    
    public AccountInfo(String accNumber, String aCountry, String prov, String aCity, 
            String anAddress, String pCode, String pNumb, String fNumb, String aDistrict, String aName){
        super(accNumber, aCountry, prov, aCity, anAddress, pCode, pNumb, fNumb);
        schoolName = aName;
        schoolDistrict = aDistrict;
    }
    
    public String getSchoolName(){
        return schoolName;
    }
    
    public String getSchoolDistrict(){
        return schoolDistrict;
    }
    
    public void setSchoolName(String aName){
        schoolName = aName;
    }
    
    public void setSchoolDistrict(String aDistrict){
        schoolDistrict = aDistrict;
    }   
}
