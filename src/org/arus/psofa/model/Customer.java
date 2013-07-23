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
    private AccountInfo accountInfo;
    private BillingInfo billingInfo;
    private OrderHistory orderHistory;
    
    public AccountInfo getAccountInfo(){
        return accountInfo;
    }
    
    public BillingInfo getBillingInfo(){
        return billingInfo;
    }
    
    public OrderHistory getOrderHistory(){
        return orderHistory;
    }
    
    public void setAccountInfo(AccountInfo accInfo){
        accountInfo =  accInfo;
    }
    
    public void setBillingInfo(BillingInfo billInfo){
        billingInfo = billInfo;
    }
    
    public void setOrderHistory(OrderHistory anOrderHistory){
        orderHistory = anOrderHistory;
    }
}
