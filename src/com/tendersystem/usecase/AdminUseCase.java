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
import com.tendersystem.model.AdministratorBean;
import com.tendersystem.model.BidderBean;
import com.tendersystem.model.TenderBean;
import com.tendersystem.model.VendorBean;

public class AdminUseCase {
	public boolean LogInAdmin() {
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------");
		System.out.println("Enter the ID here:-");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the Password here:-");
		String password = sc.nextLine();

		AdministratorDao ad = new AdministratorDaoImp();
		try {
			AdministratorBean admin = ad.logInAdmin(id, password);
			System.out.println();
			System.out.println();
			System.out.println("Welcome, " + admin.getName() + "!");
			return true;
		} catch (Exception e) {
			System.out.println();
			System.out.println();
			System.out.println(e.getMessage());
			return false;
		}
	}

	public void RegisterVendor() throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------");
		System.out.println("Enter a id here:-");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter a name here:-");
		String name = sc.nextLine();
		System.out.println("Enter a email here:-");
		String email = sc.nextLine();
		System.out.println("Enter a address here:-");
		String address = sc.nextLine();

		AdministratorDao dao = new AdministratorDaoImp();

		try {
			String result = dao.registerVendor(id, name, email, address);
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println(result);
		} catch (VendorException e) {
			System.out.println();
			System.out.println();
			System.out.println(e.getMessage());
		}
	}

	public void ViewAllVendor() throws SQLException {
		AdministratorDao dao = new AdministratorDaoImp();

		System.out.println("--------------------------------");
		try {
			List<VendorBean> vendors = dao.viewAllVendor();
			System.out.println();
			System.out.println();
			vendors.forEach(v -> System.out.println(v));
		} catch (VendorException e) {
			System.out.println();
			System.out.println();
			System.out.println(e.getMessage());
		}
	}

	public void CreateTender() throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------");
		System.out.println("Enter a id here :-");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter a name here:-");
		String name = sc.nextLine();
		System.out.println("Enter a type here:-");
		String type = sc.nextLine();
		System.out.println("Enter a price here:-");
		int price = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter a location here:-");
		String location = sc.nextLine();

		AdministratorDao dao = new AdministratorDaoImp();

		try {
			String result = dao.createTender(id, name, type, price, location);
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println(result);
		} catch (TenderException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println(e.getMessage());
		}
	}

	public void ViewAllTender() throws SQLException {
		AdministratorDao dao = new AdministratorDaoImp();
		System.out.println("--------------------------------");
		try {
			List<TenderBean> vendors = dao.viewAllTender();
			System.out.println();
			System.out.println();
			System.out.println();
			vendors.forEach(v -> System.out.println(v));
		} catch (TenderException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println(e.getMessage());
		}
	}

	public void ViewAllBidsOfTender() throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------");
		System.out.println("Enter the tender ID here (format : TRXXXX):-");
		int tid = sc.nextInt();
		AdministratorDao dao = new AdministratorDaoImp();
		try {
			List<BidderBean> bidders = dao.viewAllBidsOfTender(tid);
			System.out.println();
			System.out.println();
			System.out.println();
			bidders.forEach(b -> System.out.println(b));

		} catch (BidderException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println(e.getMessage());
		}
	}

	public void AssignTenderToVendor() throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------");
		System.out.println("Enter the Vendor ID here :-");
		int vid = sc.nextInt();
		System.out.println("Enter the Tender ID here :-");
		int tid = sc.nextInt();

		AdministratorDao dao = new AdministratorDaoImp();

		try {
			String result = dao.assignTenderToVendor(vid, tid);
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println(result);
		} catch (BidderException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println(e.getMessage());
		}
	}

}
