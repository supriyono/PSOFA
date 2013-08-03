/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.arus.psofa.model;

/**
 *
 * @author Supri
 */
public class Customer {
    private CustomerAcct accountInfo;
    private BillingAcct billingInfo;
    private Order orderHistory;
    
    public CustomerAcct getAccountInfo(){
        return accountInfo;
    }
    
    public BillingAcct getBillingInfo(){
        return billingInfo;
    }
    
    public Order getOrderHistory(){
        return orderHistory;
    }
    
    public void setAccountInfo(CustomerAcct accInfo){
        accountInfo =  accInfo;
    }
    
    public void setBillingInfo(BillingAcct billInfo){
        billingInfo = billInfo;
    }
    
    public void setOrderHistory(Order anOrderHistory){
        orderHistory = anOrderHistory;
    }
}
