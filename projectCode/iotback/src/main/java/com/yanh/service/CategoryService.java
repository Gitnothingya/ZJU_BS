package com.yanh.service;

import com.yanh.pojo.Category;

import java.util.List;

public interface CategoryService {
    void addCategory(Category category);

    List<Category> getCategories();

    Category findById(Integer id);

    void update(Category category);

    void delete(Integer id);

    Integer getCount(Integer id);
}
