package com.tendersystem.model;

public class BidderBean {
	private int id;
	private int vid;
	private int tid;
	private int price;
	private String status;

	public BidderBean() {
		super();
	}

	public BidderBean(int id, int vid, int tid, int price, String status) {
		super();
		this.id = id;
		this.vid = vid;
		this.tid = tid;
		this.price = price;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVid() {
		return vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Bidder [id=" + id + ", vid=" + vid + ", tid=" + tid + ", price=" + price + ", status=" + status + "]";
	}

}
