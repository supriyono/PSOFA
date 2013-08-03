/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.arus.psofa.model;

/**
 *
 * @author Supri
 */
public class CustomerAcct extends AccountInfo{
    private String schoolName;
    private String schoolDistrict;
    
    public CustomerAcct(){
        super();
        schoolName = null;
        schoolDistrict = null;
    }
    
    public CustomerAcct(int accNumber, String aName, String aDistrict, String aCountry, String prov, String aCity, 
            String anAddress, String pCode, String pNumb, String fNumb){
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
