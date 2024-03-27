package com.blog.services;

import java.util.List;

import com.blog.payloads.CategoryDto;

public interface Categoryservice {

	public CategoryDto createCategory(CategoryDto categorydto);

	public CategoryDto getCategoryByID(Integer categoryid);

	public List<CategoryDto> getCategories();

	public CategoryDto updateCategory(CategoryDto categorydto, Integer categoryId);

	public void deleteCategory(Integer categoryId);

}
