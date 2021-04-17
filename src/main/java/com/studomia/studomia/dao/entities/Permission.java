package com.studomia.studomia.dao.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_permission")
public class Permission {

    public static final String PREFIX ="SCOPE_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="permission_id")
    private Long permissionId;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @ManyToMany(targetEntity = Role.class ,mappedBy="permissions")
    private Set<Role> roles;

    public Permission(){
        roles = new HashSet<Role>();
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    @Override
    public boolean equals(Object permission)
    {
        if (permission == null)
            return false;
        if (permission == this)
            return true;

        return this.getPermissionId() ==((Permission) permission ).getPermissionId();
    }

}
