package com.yanh.service.impl;


import com.yanh.mapper.CategoryMapper;
import com.yanh.pojo.Category;
import com.yanh.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public void addCategory(Category category) {
        categoryMapper.add(category);
    }

    @Override
    public List<Category> getCategories() {
        return categoryMapper.getAll();
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.findById(id);
    }

    @Override
    public void update(Category category) {
        categoryMapper.update(category);
    }

    @Override
    public void delete(Integer id) {
        categoryMapper.delete(id);
    }

    @Override
    public Integer getCount(Integer id) {
        return categoryMapper.findById(id).getCount();
    }
}
