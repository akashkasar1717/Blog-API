package com.blog.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CategoryDto;
import com.blog.repositories.CategoryRepo;
import com.blog.services.Categoryservice;

@Service
public class Categoryserviceimpl implements Categoryservice {

	private CategoryRepo crepo;

	@Autowired
	public void setCrepo(CategoryRepo crepo) {
		this.crepo = crepo;
	}

	public ModelMapper getModelmapper() {
		return modelmapper;
	}

	public void setModelmapper(ModelMapper modelmapper) {
		this.modelmapper = modelmapper;
	}

	@Autowired
	private ModelMapper modelmapper;

	@Override
	public CategoryDto createCategory(CategoryDto categorydto) {
		Category category = this.modelmapper.map(categorydto, Category.class);
		crepo.save(category);
		return this.modelmapper.map(category, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryByID(Integer categoryid) {
		Category c = crepo.findById(categoryid)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryid));
		return this.modelmapper.map(c, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> clist = crepo.findAll();
		List<CategoryDto> cdto = new ArrayList<CategoryDto>();
		for (Category cl : clist) {
			cdto.add(modelmapper.map(cl, CategoryDto.class));
		}
		return cdto;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categorydto, Integer categoryId) {
		Category c = crepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));
		c.setCategoryname(categorydto.getCategoryname());
		c.setCategorydiscription(categorydto.getCategorydiscription());
		crepo.save(c);
		return this.modelmapper.map(c, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		crepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));
		crepo.deleteById(categoryId);
	}

}
