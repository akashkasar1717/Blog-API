package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.CategoryDto;
import com.blog.services.impl.Categoryserviceimpl;

import jakarta.validation.Valid;

@RestController
public class Categorycontroller {

	private Categoryserviceimpl cservice;

	@Autowired
	public void setCservice(Categoryserviceimpl cservice) {
		this.cservice = cservice;
	}

	@PostMapping("/category")
	public ResponseEntity<CategoryDto> createcategory(@Valid @RequestBody CategoryDto categorydto) {
		System.out.println(categorydto);
		return new ResponseEntity<CategoryDto>(cservice.createCategory(categorydto), HttpStatus.CREATED);
	}

	@GetMapping("/category")
	public ResponseEntity<List<CategoryDto>> getcategories() {
		return new ResponseEntity<List<CategoryDto>>(cservice.getCategories(), HttpStatus.OK);
	}

	@GetMapping("/category/{categoryid}")
	public ResponseEntity<CategoryDto> getcategoryByID(@PathVariable Integer categoryid) {
		return new ResponseEntity<CategoryDto>(cservice.getCategoryByID(categoryid), HttpStatus.OK);
	}

	@PutMapping("/category/{categoryid}")
	public ResponseEntity<CategoryDto> updatecategory(@Valid @RequestBody CategoryDto categorydto,
			@PathVariable Integer categoryid) {
		return new ResponseEntity<CategoryDto>(cservice.updateCategory(categorydto, categoryid), HttpStatus.OK);
	}

	@DeleteMapping("/category/{categoryid}")
	public ResponseEntity<String> deletecategoryByID(@PathVariable Integer categoryid) {
		cservice.deleteCategory(categoryid);
		return new ResponseEntity<String>("Category Deleted Successfully..!", HttpStatus.OK);
	}

}
