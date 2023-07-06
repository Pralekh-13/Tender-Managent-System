package com.tendersystem.usecase;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.tendersystem.dao.AdministratorDao;
import com.tendersystem.dao.AdministratorDaoImp;
import com.tendersystem.dao.VendorDao;
import com.tendersystem.dao.VendorDaoImp;
import com.tendersystem.exception.BidderException;
import com.tendersystem.exception.TenderException;
import com.tendersystem.exception.VendorException;
import com.tendersystem.model.BidderBean;
import com.tendersystem.model.TenderBean;
import com.tendersystem.model.VendorBean;

public class VendorUseCase {
	public boolean LogInVendor() throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------");
		System.out.println("Enter the ID here:-");
		int id = sc.nextInt();

		VendorDao dao = new VendorDaoImp();

		try {
			VendorBean vendor = dao.logInVendor(id);
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("Welcome, " + vendor.getName() + "!");
			return true;
		} catch (VendorException e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	public void ViewCurrentTender() throws SQLException {
		AdministratorDao dao = new AdministratorDaoImp();
		System.out.println("--------------------------------");
		try {
			List<TenderBean> vendors = dao.viewAllTender();
			System.out.println();
			System.out.println();
			vendors.forEach(v -> System.out.println(v));
		} catch (TenderException e) {
			System.out.println();
			System.out.println();
			System.out.println(e.getMessage());
		}
	}

	public void PlaceBidAgainstTender() throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------");
		System.out.println("Enter the Vendor ID  here:-");
		int vid = sc.nextInt();
		System.out.println("Enter the Tender ID  here:-");
		int tid = sc.nextInt();
		System.out.println("Enter the Bidding price here:-");
		int bprice = sc.nextInt();
		sc.nextLine();

		VendorDao dao = new VendorDaoImp();

		try {
			String result = dao.placeBidAgainstTender(vid, tid, bprice);
			System.out.println();
			System.out.println();
			System.out.println(result);
		} catch (TenderException e) {
			System.out.println();
			System.out.println();
			System.out.println(e.getMessage());
		}
	}

	public void ViewStatusOfBid() throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------");
		System.out.println("Enter the Bid S. No. here:-");
		int id = sc.nextInt();

		VendorDao dao = new VendorDaoImp();
		try {
			BidderBean bidder = dao.viewStatusOfBid(id);
			System.out.println();
			System.out.println();
			System.out.println(bidder.getStatus());
		} catch (BidderException e) {
			System.out.println();
			System.out.println();
			System.out.println(e.getMessage());
		}
	}

	public void ViewOwnBidHistory() throws SQLException {
		VendorDao dao = new VendorDaoImp();
		System.out.println("--------------------------------");
		try {
			List<BidderBean> bidders = dao.viewOwnBidHistory();
			System.out.println();
			System.out.println();
			bidders.forEach(b -> System.out.println(b));
		} catch (BidderException e) {
			System.out.println();
			System.out.println();
			System.out.println(e.getMessage());
		}
	}

}
