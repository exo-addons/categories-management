package org.exoplatform.categories.entities.dto;


import org.exoplatform.categories.entities.domain.CategoryEntity;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class CategoryDTO {


    protected Long id;

    @NotBlank
    @Size(min = 10, max = 50)
    protected String title;

    @Size(min = 10, max = 256)
    protected String description;

    protected String icon;

    protected String context;

    public CategoryDTO(CategoryEntity categoryEntity) {

        this.id = categoryEntity.getId();

        this.title = categoryEntity.getTitle();

        this.icon = categoryEntity.getIcon();

        this.description = categoryEntity.getDescription();

        this.context = categoryEntity.getContext();
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
    public String toString() {
        return "CategoryDTO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", context=" + context +
                "}";
    }
}
