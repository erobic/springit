package com.erobic.springit.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by robik on 1/1/17.
 */
@Entity
public class Role extends ReferenceEntity {

    private static final long serialVersionUID = 1;

    public Role() {
    }
}
