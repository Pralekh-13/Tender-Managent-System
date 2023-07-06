package com.tendersystem.model;

public class TenderBean {
	private int id;
	private String name;
	private String type;
	private int price;
	private String location;

	public TenderBean() {
		super();
	}

	public TenderBean(int id, String name, String type, int price, String location) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Tender [id=" + id + ", name=" + name + ", type=" + type + ", price=" + price + ", location="
				+ location + "]";
	}

}
