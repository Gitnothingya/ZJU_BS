package com.yanh.controller;



import com.yanh.pojo.Category;
import com.yanh.pojo.Result;
import com.yanh.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result addCategory(@RequestBody @Validated(Category.Add.class) Category category){
        categoryService.addCategory(category);
        return Result.success();
    }

    @GetMapping
    public Result getCategories() {
        List<Category> categories = categoryService.getCategories();
        return Result.success(categories);
    }

    @GetMapping("/detail")
    public Result getDetail(Integer id) {
        Category c = categoryService.findById(id);
        return Result.success(c);
    }

    @PutMapping
    public Result update(@RequestBody @Validated(Category.Update.class) Category category) {
        categoryService.update(category);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(Integer id){
        if (id == 1) {
            return Result.error("不允许删除默认分类");
        }
        if (categoryService.getCount(id) > 0) {
            return Result.error("当前分类不为空,不允许删除");
        }
        categoryService.delete(id);
        return Result.success();
    }

}
