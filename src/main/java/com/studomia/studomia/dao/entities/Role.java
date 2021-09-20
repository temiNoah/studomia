package com.studomia.studomia.dao.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.studomia.studomia.model.RoleName;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "role")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Role  {
    public static final String PREFIX ="ROLE_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Long roleId;

//    @Column(name="name")
//    private String name;

    @Column(name = "ROLE_NAME")
    //@Enumerated(EnumType.STRING)
   // @NaturalId
    private String name;


    @Column(name="description")
    private String description;

    @ManyToMany(targetEntity = Permission.class)
    @JoinTable(
            name = "t_role_permission",
            joinColumns = @JoinColumn(name = "role_id",nullable=true, updatable=true,referencedColumnName = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id",updatable = true,nullable = true,referencedColumnName = "permission_id")
    )
    private Set<Permission> permissions;


//    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
//    @JsonIgnore
//    private Set<User> userList = new HashSet<>();


    public Role()
    {
        permissions = new HashSet<>();
    }

    public Long getRoleId() {
        return this.roleId;
    }
    public void setRoleId(Long id) {
        this.roleId = id;
    }
//    public String getName() {
//        return this.name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
    public boolean isAdminRole() {
    return null != this && this.name.equals(RoleName.ROLE_ADMIN);
}

    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Set<Permission> getPermissions()
    {
        return this.permissions;
    }

//    public Set<User> getUserList() {
//        return userList;
//    }
//
//    public void setUserList(Set<User> userList) {
//        this.userList = userList;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object role)
    {
        if (role == null)
            return false;
        if (role == this)
            return true;

        return this.getRoleId() ==((Role) role ).getRoleId();
    }
}

