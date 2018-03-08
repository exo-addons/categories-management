package org.exoplatform.categories.entities.domain;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "Category")
@ExoEntity
@Table(name = "CATEGORIES_MANAGEMENT_CATEGORY")
@NamedQueries( {
        @NamedQuery(
                name = "Category.getAllCategories",
                query = "SELECT category FROM Category category"
        ),
        @NamedQuery(
                name = "Category.findCategoryByTitle",
                query = "SELECT category FROM Category category where category.title = :categoryTitle"
        ),
        @NamedQuery(
                name = "Category.findCategoryByContext",
                query = "SELECT category FROM Category category where category.context = :categoryContext"
        ),
        @NamedQuery(
                name = "Category.deleteCategoryByTitle",
                query = "DELETE FROM Category category WHERE category.title = :categoryTitle "
        ),
        @NamedQuery(
                name = "Category.deleteCategoryById",
                query = "DELETE FROM Category category WHERE category.id = :categoryId "
        )
})
public class CategoryEntity extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "TITLE", unique = true,   nullable = false)
    protected String title;

    @Column(name = "DESCRIPTION")
    protected String description;

    @Column(name = "ICON")
    protected String icon;

    @Column(name = "CONTEXT")
    protected String context;

    public CategoryEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CategoryEntity badgeEntity = (CategoryEntity) o;
        return !(badgeEntity.getId() == null || getId() == null) && Objects.equals(getId(), badgeEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Badge{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                ", context='" + context + '\'' +
                "}";
    }
}
