/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.arus.psofa.model;

/**
 *
 * @author Supri
 */
public class BillingInfo extends CustomerInfo{
    private String billRecipient;
    
    public BillingInfo(){
        super();
        billRecipient = null;
    }
    
    public BillingInfo(String accNumber, String aCountry, String prov, String aCity, 
            String anAddress, String pCode, String pNumb, String fNumb, String aRecipient){
        super(accNumber, aCountry, prov, aCity, anAddress, pCode, pNumb, fNumb);
        billRecipient = aRecipient;
    }
    
    public String getRecipient(){
        return billRecipient;
    }
    
    public void setRecipient(String aRecipient){
        billRecipient = aRecipient;
    }
}
