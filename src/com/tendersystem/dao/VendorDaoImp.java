package com.tendersystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tendersystem.connection.JDBC;
import com.tendersystem.exception.BidderException;
import com.tendersystem.exception.TenderException;
import com.tendersystem.exception.VendorException;
import com.tendersystem.model.BidderBean;
import com.tendersystem.model.TenderBean;
import com.tendersystem.model.VendorBean;

public class VendorDaoImp implements VendorDao {
	int master_id;

	private Connection con;

	public VendorDaoImp() {
		this.con = JDBC.getConnection();
	}

	@Override
	public VendorBean logInVendor(int id) throws VendorException, SQLException {
		this.master_id = id;

		VendorBean vendor = null;

		PreparedStatement ps = this.con.prepareStatement("select * from vender where vId=? ");

		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			int i = rs.getInt("vId");
			System.out.println(i);
			String n = rs.getString("vname");
			String e = rs.getString("vemail");
			String a = rs.getString("vaddress");

			vendor = new VendorBean(i, n, e, a);
		} else {
			throw new VendorException("Invalid credentials!");
		}

		return vendor;
	}

	@Override
	public List<TenderBean> viewAllTender() throws TenderException, SQLException {
		List<TenderBean> tenders = new ArrayList<>();

		PreparedStatement ps = this.con.prepareStatement("select * from tender");

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			int i = rs.getInt("tId");
			String n = rs.getString("tname");
			String t = rs.getString("ttype");
			int p = rs.getInt("tprice");
			String l = rs.getString("tlocation");

			tenders.add(new TenderBean(i, n, t, p, l));
		}

		if (tenders.size() == 0) {
			throw new TenderException("No vendor found!");
		}

		return tenders;
	}

	@Override
	public BidderBean viewStatusOfBid(int id) throws BidderException, SQLException {
		BidderBean bidder = null;

		PreparedStatement ps = this.con.prepareStatement("select * from bidder where bno=?");

		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			int i = rs.getInt("bno");
			int vi = rs.getInt("vender_Id");
			int ti = rs.getInt("tender_Id");
			int p = rs.getInt("bprice");
			String s = rs.getString("bstatus");

			bidder = new BidderBean(i, vi, ti, p, s);
		} else {
			throw new BidderException("Invalid Bidder ID !");
		}

		return bidder;
	}

	@Override
	public List<BidderBean> viewOwnBidHistory() throws BidderException, SQLException {
		List<BidderBean> bidders = new ArrayList<>();

		PreparedStatement ps = this.con.prepareStatement("select * from bidder where vender_Id=?");

		ps.setInt(1, master_id);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			int i = rs.getInt("bid");
			int vi = rs.getInt("vid");
			int ti = rs.getInt("tid");
			int p = rs.getInt("bprice");
			String s = rs.getString("bstatus");

			bidders.add(new BidderBean(i, vi, ti, p, s));
		}

		if (bidders.size() == 0) {
			throw new BidderException("No bids found!");
		}

		return bidders;
	}

	@Override
	public String placeBidAgainstTender(int vid, int tid, int price) throws TenderException, SQLException {

		String result = "Bidding failed!";

		this.master_id = vid;

		PreparedStatement ps1 = this.con.prepareStatement("select * from tender where tId=?");

		ps1.setInt(1, tid);

		ResultSet rs = ps1.executeQuery();

		if (rs.next()) {

			PreparedStatement ps2 = this.con
					.prepareStatement("insert into bidder(vender_Id, tender_Id, bprice) values(?,?,?)");

			ps2.setInt(1, master_id);
			ps2.setInt(2, tid);
			ps2.setInt(3, price);

			int x = ps2.executeUpdate();

			if (x > 0) {
				result = "Bidded successfully...";
			}

		} else {
			throw new TenderException("Tender didn't found with this " + tid);
		}

		return result;
	}

}