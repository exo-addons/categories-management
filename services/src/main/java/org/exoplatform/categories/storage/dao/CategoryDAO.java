package org.exoplatform.categories.storage.dao;


import org.exoplatform.categories.entities.domain.CategoryEntity;
import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

public class CategoryDAO extends GenericDAOJPAImpl<CategoryEntity, Long> {

    public CategoryDAO() {

    }

    public CategoryEntity findCategoryByTitle(String categoryTitle) throws PersistenceException {

        TypedQuery<CategoryEntity> query = getEntityManager().createNamedQuery("Category.findCategoryByTitle", CategoryEntity.class)
                .setParameter("categoryTitle", categoryTitle);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    public List<CategoryEntity> findCategoriesByContext(String categoryContext) throws PersistenceException {

        TypedQuery<CategoryEntity> query = getEntityManager().createNamedQuery("Category.findCategoryByContext", CategoryEntity.class)
                .setParameter("categoryContext", categoryContext);

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }

    public List<CategoryEntity> getAllCategories() throws PersistenceException {

        TypedQuery<CategoryEntity> query = getEntityManager().createNamedQuery("Category.getAllCategories", CategoryEntity.class);

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }

    public int deleteCategoryById(int categoryId) throws PersistenceException {
        return getEntityManager().createNamedQuery("Category.deleteCategoryById")
                .setParameter("categoryId", categoryId)
                .executeUpdate();

    }

    public int deleteCategoryByTitle(int categoryTitle) throws PersistenceException {
        return getEntityManager().createNamedQuery("Category.deleteCategoryByTitle")
                .setParameter("categoryTitle", categoryTitle)
                .executeUpdate();

    }
    public CategoryEntity findCategoryById(Long categoryId) throws PersistenceException {
        return getEntityManager().find(CategoryEntity.class,categoryId);
    }

    public void clear() {
        getEntityManager().clear();
    }


}
