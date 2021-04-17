package com.studomia.studomia.security.socialLogin;

import com.studomia.studomia.security.socialLogin.GoogleOAuth2UserInfo;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomOidcJwtUserService extends OidcUserService
{

//    @Autowired
//    private UserRepository userRepository;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException
    {
        OidcUser oidcUser = super.loadUser(userRequest);
        Map attributes = oidcUser.getAttributes();

        GoogleOAuth2UserInfo userInfo = new GoogleOAuth2UserInfo();//it can be used by facebook: i guess
        userInfo.setEmail((String) attributes.get("email"));
        userInfo.setId((String) attributes.get("sub"));
        userInfo.setImageUrl((String) attributes.get("picture"));
        userInfo.setName((String) attributes.get("name"));
        updateUser(userInfo);


//        User user = repository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found: " + username));
//        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Arrays.asList(authority));

        return oidcUser;
    }

    private void updateUser(GoogleOAuth2UserInfo userInfo) {
//        User user = userRepository.findByEmail(userInfo.getEmail());
//        if(user == null) {
//            user = new User();
//        }
//        user.setEmail(userInfo.getEmail());
//        user.setImageUrl(userInfo.getImageUrl());
//        user.setName(userInfo.getName());
//        user.setUserType(UserType.google);
//        userRepository.save(user);
    }


}
