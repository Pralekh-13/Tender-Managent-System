package com.tendersystem.dao;

import java.sql.SQLException;
import java.util.List;

import com.tendersystem.exception.BidderException;
import com.tendersystem.exception.TenderException;
import com.tendersystem.exception.VendorException;
import com.tendersystem.model.BidderBean;
import com.tendersystem.model.TenderBean;
import com.tendersystem.model.VendorBean;

public interface VendorDao {
	public VendorBean logInVendor(int id) throws VendorException, SQLException;

	public List<TenderBean> viewAllTender() throws TenderException, SQLException;

	public BidderBean viewStatusOfBid(int id) throws BidderException, SQLException;

	public List<BidderBean> viewOwnBidHistory() throws BidderException, SQLException;

	public String placeBidAgainstTender(int vid, int tid, int bprice) throws TenderException, SQLException;

}
