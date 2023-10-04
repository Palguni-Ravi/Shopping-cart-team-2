package com.example.shoppingcart.MongoDb.Model;

import org.springframework.context.annotation.Profile;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document(collection = "clothing")
@Profile("mongodb")
public class Cloth {

  @Id
  private int id;

  private String tag;
  private String image;
  @DBRef
  private Gender gender;
  @DBRef
  private Brand brand; 
  @DBRef
  private Category category; 
  private String color;
  private int rating;
  private int price;
  private int pincode;
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
public Gender getGender() {
	return gender;
}
public void setGender(Gender gender) {
	this.gender = gender;
}
public Brand getBrand() {
	return brand;
}
public void setBrand(Brand brand) {
	this.brand = brand;
}
public Category getCategory() {
	return category;
}
public void setCategory(Category category) {
	this.category = category;
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
public int getPincode() {
	return pincode;
}
public void setPincode(int pincode) {
	this.pincode = pincode;
}
}