package com.example.shoppingcart.Mysql.Config;

import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.cloudinary.Cloudinary;
@Configuration
public class ProductConfg {
	@Bean
	public Cloudinary getCloudinary() {
		Map<String,String> config = new HashMap<>();
		config.put("cloud_name", "diad7cgjb");
		config.put("api_key", "969798656956227");
		config.put("api_secret", "yfQK3Y6YQBxqdPV2-MVzsFstMDE");
		return new Cloudinary(config);
	}
}
