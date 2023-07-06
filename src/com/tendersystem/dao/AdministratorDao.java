package com.tendersystem.dao;

import java.sql.SQLException;
import java.util.List;

import com.tendersystem.exception.AdministratorException;
import com.tendersystem.exception.BidderException;
import com.tendersystem.exception.TenderException;
import com.tendersystem.exception.VendorException;
import com.tendersystem.model.AdministratorBean;
import com.tendersystem.model.BidderBean;
import com.tendersystem.model.TenderBean;
import com.tendersystem.model.VendorBean;

public interface AdministratorDao {
	public AdministratorBean logInAdmin(int id, String password) throws AdministratorException, SQLException;
	
	public String registerVendor(int id, String name, String email, String address) throws VendorException, SQLException;

    public List<VendorBean> viewAllVendor() throws VendorException, SQLException;
    
    public String createTender(int id, String name, String type, int price, String location) throws TenderException, SQLException;

    public List<TenderBean> viewAllTender() throws TenderException, SQLException;
    
    public List<BidderBean> viewAllBidsOfTender(int tid) throws BidderException, SQLException;
    
    public String assignTenderToVendor(int vid, int tid) throws BidderException, SQLException;
    
}
