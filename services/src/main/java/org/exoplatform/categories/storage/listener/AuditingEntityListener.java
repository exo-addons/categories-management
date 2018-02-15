package org.exoplatform.categories.storage.listener;

import org.exoplatform.categories.entities.domain.CategoryEntity;

import javax.persistence.*;

public class AuditingEntityListener {

    public AuditingEntityListener() {

    }

    @PostPersist
    public void methodInvokedAfterPersist(CategoryEntity categoryEntity) {

    }
    @PostUpdate
    public void methodInvokedAfterUpdate(CategoryEntity categoryEntity) {

    }

    @PostRemove
    public void methodInvokedAfterRemove(CategoryEntity categoryEntity) {

    }
}
