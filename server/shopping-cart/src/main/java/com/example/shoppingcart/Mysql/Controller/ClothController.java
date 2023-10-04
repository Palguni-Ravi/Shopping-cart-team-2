package com.example.shoppingcart.Mysql.Controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.shoppingcart.Mysql.Model.Cloth;
import com.example.shoppingcart.Mysql.Repository.ClothRepository;
import com.example.shoppingcart.Mysql.Service.ClothService;
import com.example.shoppingcart.Mysql.Service.ClothingSearchService;
import com.example.shoppingcart.Mysql.Service.CloudinaryImageService;

import co.elastic.clients.elasticsearch.indices.Storage;

import com.example.shoppingcart.Mysql.Repository.BrandRepository;
import com.example.shoppingcart.Mysql.Repository.CategoryRepository;
import com.example.shoppingcart.Mysql.Repository.GenderRepository;
import com.example.shoppingcart.Mysql.Model.Brands;
import com.example.shoppingcart.Mysql.Model.Category;
import com.example.shoppingcart.Mysql.Model.Gender;
import com.example.shoppingcart.Mysql.Model.User;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:5000")
@Profile("mysql")
public class ClothController {
	@Autowired
	private ClothRepository clothRepository;
	@Autowired
	private ClothingSearchService clothingSearchService;
	@Autowired
	private RestHighLevelClient elasticsearchClient;
	@Autowired
	private CloudinaryImageService cloudinaryImageService;
	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private GenderRepository genderRepository;
	@Autowired
    private ClothService clothService;
	@PostConstruct
    public void initializeData() {
        clothingSearchService.Indexdata();
    }
	private String SearchQueryFinal;
	List<String> colors = new ArrayList<>();
	List<String> brands = new ArrayList<>();
	List<String> categories = new ArrayList<>();
	List<String> genders = new ArrayList<>();
	List<String> tags = new ArrayList<>();
	List<String> colorsFilter = new ArrayList<>();
	List<String> brandsFilter = new ArrayList<>();
	List<String> categoriesFilter = new ArrayList<>();
	List<String> gendersFilter = new ArrayList<>();
	List<String> priceFilter = new ArrayList<>();
	List<String> ratingFilter = new ArrayList<>();
	@GetMapping("/clothingUrl")
	public List<Cloth> getAllContacts() {
		return clothRepository.findAll();
	}
	@GetMapping("/allBrands")
	public List<Brands> getAllBrands() {
		return brandRepository.findAll();
	}
	@GetMapping("/allCategory")
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}
	@GetMapping("/allGender")
	public List<Gender> getAllGenders() {
		return genderRepository.findAll();
	}
	
	@GetMapping("/search")
	public List<Object> searchq(@RequestParam("q") String query) {
		colors = new ArrayList<>();
		brands = new ArrayList<>();
		categories = new ArrayList<>();
		genders = new ArrayList<>();
		tags = new ArrayList<>();
		String[] values = query.split(" ");
		for (String value : values) {
			if (clothingSearchService.getUniqueColors().contains(value)) {
				colors.add(value);
			} else if (clothingSearchService.getUniqueBrands().contains(value)) {
				brands.add(value);
			} else if (clothingSearchService.getUniqueGender().contains(value)) {
				genders.add(value);
			} else if (clothingSearchService.getUniqueCategories().contains(value)) {
				categories.add(value);
			} else {
				tags.add(value);
			}
		}
		String searchQueryAndAggregation = clothingSearchService.buildSearchQueryAndAggregationNoti(colors, brands,
				genders, categories, tags);
		SearchQueryFinal = searchQueryAndAggregation;
		List<Object> finalSearch = clothingSearchService.performAggregation(searchQueryAndAggregation,
				elasticsearchClient);
		return finalSearch;
	}
	@GetMapping("/updateSearch")
	public List<Object> updateS(@RequestParam("q") String query, @RequestParam("f") String filter) {
		colorsFilter = new ArrayList<>();
		brandsFilter = new ArrayList<>();
		categoriesFilter = new ArrayList<>();
		gendersFilter = new ArrayList<>();
		priceFilter = new ArrayList<>();
		ratingFilter = new ArrayList<>();
		String[] values = query.split(" ");
		String[] filters = filter.split("&");
		for (String value : values) {
			if (clothingSearchService.getUniqueColors().contains(value)) {
				colors.add(value);
			} else if (clothingSearchService.getUniqueBrands().contains(value)) {
				brands.add(value);
			} else if (clothingSearchService.getUniqueGender().contains(value)) {
				genders.add(value);
			} else if (clothingSearchService.getUniqueCategories().contains(value)) {
				categories.add(value);
			} else {
				tags.add(value);
			}
		}
		for (String f : filters) {
			String[] keyValue = f.split("=");
			String key = keyValue[0];
			String value = keyValue[1];
			if ("Color".equals(key)) {
				colorsFilter.add(value);
			} else if ("Brand".equals(key)) {
				brandsFilter.add(value);
			} else if ("Gender".equals(key)) {
				gendersFilter.add(value);
			} else if ("Category".equals(key)) {
				categoriesFilter.add(value);
			} else if ("Price".equals(key)) {
				String[] priceBounds = value.split("-");
				if (priceBounds.length == 2) {
					String minPrice = priceBounds[0];
					String maxPrice = priceBounds[1];
					priceFilter.add(minPrice + "-" + maxPrice);
				}
			} else if ("Rating".equals(key)) {
				String[] ratingBounds = value.split("-");
				if (ratingBounds.length == 2) {
					String minRating = ratingBounds[0];
					String maxRating = ratingBounds[1];
					ratingFilter.add(minRating + "-" + maxRating);
				}
			}
		}
		String addPostFilterQuery = clothingSearchService.addPostFilter(SearchQueryFinal, colorsFilter, brandsFilter,
				priceFilter, gendersFilter, categoriesFilter, ratingFilter, elasticsearchClient);
		List<Object> finalSearch = clothingSearchService.performAggregation(addPostFilterQuery, elasticsearchClient);
		return finalSearch;
	}
	
	@PostMapping
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN')")
    public ResponseEntity<String> addCloth(@RequestBody Cloth cloth) {
        Cloth addedCloth = clothService.addCloth(cloth);

        if (addedCloth != null) {
            return ResponseEntity.ok("Cloth added successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to add cloth.");
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN')")
    public ResponseEntity<String> updateCloth(@PathVariable("id") int id, @RequestBody Cloth cloth) {
        Cloth updatedCloth = clothService.updateCloth(id, cloth);

        if (updatedCloth != null) {
            return ResponseEntity.ok("Cloth updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/admins")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public ResponseEntity<String> addAdmin(@RequestBody User admin) {
        return ResponseEntity.ok("Admin added successfully.");
    }
    
   
    @PostMapping("/add")
  //@PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN')")
  public ResponseEntity<Object> addCloth(
      @RequestParam("tag") String tag,
      @RequestParam("color") String color,
      @RequestParam("genderName") String genderName,
      @RequestParam("rating") int rating,
      @RequestParam("price") int price,
      @RequestParam("brandName") String brandName,
      @RequestParam("categoryName") String categoryName,
      @RequestParam("pincode") int pincode,
      @RequestParam("image") MultipartFile imageFile) {
      try {
    	  String imageUrl = cloudinaryImageService.upload(imageFile);
          Gender gender = genderRepository.findByName(genderName);
          if (gender == null) {
              gender = new Gender();
              gender.setName(genderName);
              gender.setImage(imageUrl);
              genderRepository.save(gender);
          }

          // Check if brand exists, if not, create a new one
          Brands brand = brandRepository.findByName(brandName);
          if (brand == null) {
              brand = new Brands();
              brand.setName(brandName);
              brand.setImage(imageUrl);
              brandRepository.save(brand);
          }

          // Check if category exists, if not, create a new one
          Category category = categoryRepository.findByName(categoryName);
          if (category == null) {
              category = new Category();
              category.setName(categoryName);
              category.setImage(imageUrl);
              categoryRepository.save(category);
          }

          
          Cloth cloth = new Cloth();
          cloth.setTag(tag);
          cloth.setColor(color);
          cloth.setGender(gender);
          cloth.setRating(rating);
          cloth.setPrice(price);
          cloth.setBrand(brand);
          cloth.setCategory(category);
          cloth.setPincode(pincode);
          cloth.setImage(imageUrl);

          clothRepository.save(cloth);

          return ResponseEntity.ok("Cloth added successfully.");
      } catch (IOException e) {
          e.printStackTrace();
          return ResponseEntity.badRequest().body("Error uploading image.");
      } catch (Exception e) {
          e.printStackTrace();
          return ResponseEntity.badRequest().body("Failed to add cloth.");
      }
  }



}