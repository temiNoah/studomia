package com.studomia.studomia.security;


import com.studomia.studomia.dao.entities.Student;
import com.studomia.studomia.dao.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

public class UserPrincipal  implements UserDetails {
    private Long id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    private User user;

//    public UserPrincipal(OidcIdToken token , OidcUserInfo oidcUserInfo){
//        super(token, oidcUserInfo);
//    }

    public UserPrincipal(Long id, String email, String password, Collection<? extends GrantedAuthority> authorities,User user) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.user = user;
    }

    public static UserPrincipal create(User user,Long id) {
//        List<GrantedAuthority> authorities = Collections.
//                singletonList(new SimpleGrantedAuthority("ROLE_USER"));

//        Student student;
//
//        if(user instanceof Student)
//            student= (Student) user;

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

//        user.getRoles().forEach(
//                                   role -> {
//                                              authorities.add(new SimpleGrantedAuthority(role.getName()  ));
//                                            }
//                               );



        return new UserPrincipal(
                id,//Long.parseLong(UUID.randomUUID().toString()),
                user.getEmail(),
                user.getPassword(),
                authorities,user
        );
    }

    public static UserPrincipal create(User user, Map<String, Object> attributes) {
      //  UserPrincipal userPrincipal = UserPrincipal.create(user);
      //  userPrincipal.setAttributes(attributes);
       // userPrincipal.setUser(user);
        return null;//userPrincipal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

//    @Override
//    public Map<String, Object> getAttributes() {
//        return attributes;
//    }
//
//    public void setAttributes(Map<String, Object> attributes) {
//        this.attributes = attributes;
//    }
//
//    @Override
//    public String getName() {
//        return String.valueOf(id);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserPrincipal that = (UserPrincipal) obj;
        return Objects.equals(getId(), that.getId());
    }

}

