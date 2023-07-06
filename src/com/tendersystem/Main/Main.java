package com.tendersystem.Main;

import java.sql.SQLException;
import java.util.Scanner;

import com.tendersystem.usecase.AdminUseCase;
import com.tendersystem.usecase.VendorUseCase;

public class Main {

	public static void administratorMain() throws SQLException {
		Scanner sc = new Scanner(System.in);
		AdminUseCase au = new AdminUseCase();
		System.out.println("--------------------------------");
		System.out.println("1. Register a new vendor");
		System.out.println("2. View all the vendors");
		System.out.println("3. Create a new tender");
		System.out.println("4. View all the tenders");
		System.out.println("5. View all the bidds of a tender");
		System.out.println("6. Assign tender to a vendor");
		System.out.println("");
		System.out.println("");
		System.out.println("0. Logout");
		System.out.println("99. Exit the application");
		int input1 = sc.nextInt();
		switch (input1) {
		case 0:
			System.out.println("Logout Successfully...");
			Main.main(null);
			break;

		case 1:
			au.RegisterVendor();
			Main.administratorMain();
			break;

		case 2:
			au.ViewAllVendor();
			Main.administratorMain();
			break;

		case 3:
			au.CreateTender();
			Main.administratorMain();
			break;

		case 4:
			au.ViewAllTender();
			Main.administratorMain();
			break;

		case 5:
			au.ViewAllBidsOfTender();
			Main.administratorMain();
			break;

		case 6:
			au.AssignTenderToVendor();
			Main.administratorMain();
			break;

		case 99:
			System.out.println("Thanks for visiting,");
			System.out.println("Have a great day.....!!");
			return;

		default:
			System.out.println("Please enter a valid input!");
			Main.administratorMain();
		}
	}

	public static void vendorMain() throws SQLException {
		Scanner sc = new Scanner(System.in);
		VendorUseCase vu = new VendorUseCase();
		System.out.println("--------------------------------");
		System.out.println("1. View all the current tenders");
		System.out.println("2. Place a bid against a tender");
		System.out.println("3. View status of bid");
		System.out.println("4. View the bid history");
		System.out.println("");
		System.out.println("");
		System.out.println("0. Logout");
		System.out.println("99. Exit the application");
		int input2 = sc.nextInt();
		switch (input2) {
		case 0:
			System.out.println("Logout successfully...");
			Main.main(null);
			break;

		case 1:
			vu.ViewCurrentTender();
			Main.vendorMain();
			break;

		case 2:
			vu.PlaceBidAgainstTender();
			Main.vendorMain();
			break;

		case 3:
			vu.ViewStatusOfBid();
			Main.vendorMain();
			break;

		case 4:
			vu.ViewOwnBidHistory();
			Main.vendorMain();
			break;

		case 99:
			System.out.println("Thanks for visiting,");
			System.out.println("Have a great day.....!!");
			break;

		default:
			System.out.println("Please enter a valid input");
		}

	}

	public static void administratorAuthentication() throws SQLException {
		AdminUseCase au = new AdminUseCase();
		if (au.LogInAdmin()) {
			administratorMain();
		}
	}

	public static void vendorAuthentication() throws SQLException {
		VendorUseCase vu = new VendorUseCase();
		if (vu.LogInVendor()) {
			vendorMain();
		}
	}

	public static void launch() throws SQLException {
		Scanner sc = new Scanner(System.in);
		AdminUseCase au = new AdminUseCase();
		VendorUseCase vu = new VendorUseCase();
		System.out.println("WELCOME TO THE TENDER APPLICATION");
		System.out.println("--------------------------------");
		System.out.println("--------------------------------");
		System.out.println("Please enter a valid input to do the following operations:-");
		System.out.println("1. Administrator");
		System.out.println("2. Vendor");
		System.out.println("");
		System.out.println("");
		System.out.println("99. Exit the application");
		int input = sc.nextInt();
		switch (input) {
		case 1:
			System.out.println("Please enter your admin id and password to login:-");
			if (au.LogInAdmin()) {
				administratorMain();
			}
			while (!au.LogInAdmin()) {
				administratorAuthentication();
			}
			break;

		case 2:
			System.out.println("Please enter your vendor id  login:-");
			if (vu.LogInVendor()) {
				vendorMain();
			}

			while (!vu.LogInVendor()) {
				vendorAuthentication();
			}
			break;

		case 99:
			System.out.println("Thanks for visiting ,");
			System.out.println("Have a great day.....!!");
			break;

		default:
			System.out.println("Please enter a valid input");
		}
	}

	public static void main(String[] args) throws SQLException {
		launch();
	}
}