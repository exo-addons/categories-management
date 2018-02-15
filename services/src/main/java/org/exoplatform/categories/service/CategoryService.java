package org.exoplatform.categories.service;

import org.exoplatform.categories.entities.domain.CategoryEntity;
import org.exoplatform.categories.storage.dao.CategoryDAO;

import java.util.List;

public class CategoryService {

    protected final CategoryDAO categoryStorage;

    public CategoryService(CategoryDAO categoryStorage) {

        this.categoryStorage = categoryStorage;

    }

    public CategoryEntity createCategory (CategoryEntity categoryEntity) {

        CategoryEntity categoryE = categoryStorage.create(categoryEntity);

        return categoryE;

    }

    public CategoryEntity updateCategory (CategoryEntity categoryEntity) {

        CategoryEntity categoryE = categoryStorage.update(categoryEntity);

        return categoryE;

    }

    public void deleteCategory (CategoryEntity categoryEntity) {

        categoryStorage.delete(categoryEntity);

    }

    public CategoryEntity findCategoryByTitle(String categoryTitle) {

        return categoryStorage.findCategoryByTitle(categoryTitle);
    }

    public List<CategoryEntity> findcategoriesByContext (String categoryContext) {

        return categoryStorage.findCategoriesByContext(categoryContext);

    }


    public void getAllBadges () {
        categoryStorage.findAll();

    }
}
