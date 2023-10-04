package com.example.shoppingcart.Mysql.Model;

import java.util.List;

import org.springframework.context.annotation.Profile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "clothing")
@Profile("mysql")
public class Cloth {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String tag;

	@Column
	private String image;

	// @Column/
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "gender")
	private Gender gender;
	

	@Column
	private String color;

	@Column
	private int rating;

	@Column
	private int price;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "brand")
	private Brands brand;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category")
	private Category category;

	@Column
	private int pincode;

	public Cloth() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Brands getBrand() {
		return brand;
	}

	public void setBrand(Brands brand) {
		this.brand = brand;
	}

	public int getPincode() {
		return pincode;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

}
