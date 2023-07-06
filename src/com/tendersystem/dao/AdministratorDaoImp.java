package com.tendersystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tendersystem.connection.JDBC;
import com.tendersystem.exception.AdministratorException;
import com.tendersystem.exception.BidderException;
import com.tendersystem.exception.TenderException;
import com.tendersystem.exception.VendorException;
import com.tendersystem.model.AdministratorBean;
import com.tendersystem.model.BidderBean;
import com.tendersystem.model.TenderBean;
import com.tendersystem.model.VendorBean;
//import com.tendersystem.utility.JDBC;

public class AdministratorDaoImp implements AdministratorDao {

	private Connection con;

	public AdministratorDaoImp() {
		this.con = JDBC.getConnection();
	}

	@Override
	public AdministratorBean logInAdmin(int id, String password) throws AdministratorException, SQLException {
		AdministratorBean result = null;

		PreparedStatement ps = this.con.prepareStatement("select * from admin where adminId=? AND adpass=?");

		ps.setInt(1, id);
		ps.setString(2, password);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			String n = rs.getString("adname");
			String e = rs.getString("ademail");
			String a = rs.getString("ad_address");

			result = new AdministratorBean(n, e, a);

		} else {
			throw new AdministratorException("Invalid Admin !");
		}

		return result;
	}

	@Override
	public String registerVendor(int vId, String vname, String vemail, String vaddress)
			throws VendorException, SQLException {
		String result = "Insertion failed!";

		PreparedStatement ps = this.con.prepareStatement("insert into vender values(?,?,?,?)");

		ps.setInt(1, vId);

		ps.setString(2, vname);
		ps.setString(3, vemail);
		ps.setString(4, vaddress);

		int x = ps.executeUpdate();

		if (x > 0) {
			result = "Insertion successfully.";
		}

		return result;
	}

	@Override
	public List<VendorBean> viewAllVendor() throws VendorException, SQLException {
		List<VendorBean> vendors = new ArrayList<>();

		PreparedStatement ps = this.con.prepareStatement("select * from vender");

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			int i = rs.getInt("vId");
			String n = rs.getString("vname");
			String e = rs.getString("vemail");
			String a = rs.getString("vaddress");

			vendors.add(new VendorBean(i, n, e, a));
		}

		if (vendors.size() == 0) {
			throw new VendorException("No vendor found!");
		}

		return vendors;
	}

	@Override
	public String createTender(int tId, String tname, String ttype, int tprice, String tlocation)
			throws TenderException, SQLException {

		String result = "Tender creation failed";

		PreparedStatement ps = this.con.prepareStatement("insert into tender values(?,?,?,?,?)");

		ps.setInt(1, tId);
		ps.setString(2, tname);
		ps.setString(3, ttype);
		ps.setInt(4, tprice);
		ps.setString(5, tlocation);

		int x = ps.executeUpdate();
		if (x > 0) {
			result = "Tender created successfully.";
		}

		return result;
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
	public List<BidderBean> viewAllBidsOfTender(int tid) throws BidderException, SQLException {
		List<BidderBean> bidders = new ArrayList<>();

		PreparedStatement ps = this.con.prepareStatement("select * from bidder where tender_id=?");

		ps.setInt(1, tid);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			int i = rs.getInt("bno");
			int vi = rs.getInt("vender_id");
			int ti = rs.getInt("tender_id");
			int p = rs.getInt("bprice");
			String s = rs.getString("bstatus");

			bidders.add(new BidderBean(i, vi, ti, p, s));
		}

		if (bidders.size() == 0) {
			throw new BidderException("No bidders found!");
		}

		return bidders;
	}

	@Override
	public String assignTenderToVendor(int vender_Id, int tender_Id) throws BidderException, SQLException {
		String result = "Invalid details provided!";

		PreparedStatement ps = this.con
				.prepareStatement("update bidder set bstatus = 'selected' where vender_id=? AND tender_id=?");

		ps.setInt(1, vender_Id);
		ps.setInt(2, tender_Id);

		int x = ps.executeUpdate();

		if (x > 0) {
			result = "Tender assigned successfully...";
		}

		return result;
	}



}
