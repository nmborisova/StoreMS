package bg.smg.service;

import bg.smg.model.Category;

public class CategoryService implements CategoryServiceI {
    public Category getCategoryById(long category_id){
        return new Category();
    }
}
