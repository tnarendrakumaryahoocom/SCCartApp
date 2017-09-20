/**
 * 
 */
package com.dineshonjava.sbmdb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dineshonjava.sbmdb.models.Ingredients;
import com.dineshonjava.sbmdb.models.Product;
import com.dineshonjava.sbmdb.models.ScProductRepository;


@RestController
@RequestMapping("/products")
public class SCcardProductController {
	
	@Autowired
	ScProductRepository scProductRepository;
	
	@RequestMapping(value = "/createproduct", method = RequestMethod.POST)
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		System.out.println("Enter ::: ");
		product = scProductRepository.save(product);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
    }
	
	
	@RequestMapping("/create")
	public Map<String, Object> create(Product product) {
		
		product.setName("p1");
		product.setDescription("dfdsfdfs");
		product.setPrice(12);
		List<Ingredients> ingredientsList =  new ArrayList<Ingredients>();
		Ingredients ingredients = new Ingredients();
		ingredients.setName("ing1");
		ingredients.setPercentage(12);
		ingredientsList.add(ingredients);
		product.setIngredients(ingredientsList);
		
		
		product = scProductRepository.save(product);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("message", "Booking created successfully");
		
	    return dataMap;
	}
	
	
	/**
	 * GET /read  --> Read all booking from the database.
	 */
	@RequestMapping("/all")
	public Map<String, Object> readAll() {
		List<Product> products = scProductRepository.findAll();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("category", "Phones");
		dataMap.put("total", products.size());
		dataMap.put("productList", products);
	    return dataMap;
	}
}
