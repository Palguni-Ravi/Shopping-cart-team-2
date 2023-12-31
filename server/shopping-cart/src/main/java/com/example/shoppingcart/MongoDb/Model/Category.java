package com.example.shoppingcart.MongoDb.Model;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "category")
@Profile("mongodb")
public class Category {

	private String _id;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	private String name;
	private String image;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
