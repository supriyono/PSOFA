/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.arus.psofa.model;

/**
 *
 * @author Supri
 */
public abstract class AccountInfo {
    private int accountNumber;
    private String country;
    private String province;
    private String city;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private String faxNumber;
    
    public AccountInfo(){
        country = null;
        province = null;
        city = null;
        address = null;
        postalCode = null;
        phoneNumber = null;
        faxNumber = null;
    }
    
    public AccountInfo(int accNumber, String aCountry, String prov, String aCity, 
            String anAddress, String pCode, String pNumb, String fNumb){
        accountNumber = accNumber;
        country = aCountry;
        province = prov;
        city = aCity;
        address = anAddress;
        postalCode = pCode;
        phoneNumber = pNumb;
        faxNumber = fNumb;
    }
    
    public int getAccNumber(){
        return accountNumber;
    }
    
    public String getCountry(){
        return country;
    }
    
    public String getProvince(){
        return province;
    }
    
    public String getCity(){
        return city;
    }
    
    public String getAddress(){
        return address;
    }
    
    public String getPostalCode(){
        return postalCode;
    }
    
    public String getPhone(){
        return phoneNumber;
    }
    
    public String getFax(){
        return faxNumber;
    }
    
    public void setAccNumber(int accNumber){
        accountNumber = accNumber;
    }
    
    public void setCountry(String aCountry){
        country = aCountry;
    }
    
    public void setProvince(String prov){
        province = prov;
    }
    
    public void setCity(String aCity){
        city = aCity;
    }
    
    public void setAddress(String anAddress){
        address = anAddress;
    }
    
    public void setPostalCode(String pCode){
        postalCode = pCode;
    }
    
    public void setPhone(String phone){
        phoneNumber = phone;
    }
    
    public void setFax(String fax){
        faxNumber = fax;
    }
}
