package com.studomia.studomia.security.socialLogin;

import com.studomia.studomia.dao.AdminRepository;
import com.studomia.studomia.dao.ExpertRepository;
import com.studomia.studomia.dao.SSOuserRepository;
import com.studomia.studomia.dao.StudentRepository;
import com.studomia.studomia.dao.entities.*;
import com.studomia.studomia.exceptions.OAuth2AuthenticationProcessingException;
import com.studomia.studomia.security.UserPrincipal;
import com.studomia.studomia.security.socialLogin.oauthUser.GoogleOAuth2UserInfo;
import com.studomia.studomia.security.socialLogin.oauthUser.OAuth2UserInfo;
import com.studomia.studomia.utils.UserType;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import javax.swing.text.html.Option;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomOidcJwtUserService extends OidcUserService  //DefaultOAuth2UserService
{

    @Autowired
    private SSOuserRepository ssOuserRepository;
//    @Autowired
//    private StudentRepository studentRepository;
//
//    @Autowired
//    private ExpertRepository expertRepository;
//
//    @Autowired
//    private AdminRepository adminRepository;

    //    @Override
//    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
//        OidcUser oidcUser = super.loadUser(userRequest);
//    }
       @Override
     public OidcUser loadUser(OidcUserRequest oAuth2UserRequest) throws OAuth2AuthenticationException
       {

        OidcUser oAuth2User = super.loadUser(oAuth2UserRequest);

        Map attributes = oAuth2User.getAttributes();

//        GoogleOAuth2UserInfo userInfo = new GoogleOAuth2UserInfo(attributes);//it can be used by facebook: i guess
//        userInfo.setEmail((String) attributes.get("email"));
//        userInfo.setId((String) attributes.get("sub"));
//        userInfo.setImageUrl((String) attributes.get("picture"));
//        userInfo.setName((String) attributes.get("name"));
//        updateUser(userInfo);


//        User user = repository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found: " + username));
//        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Arrays.asList(authority));

        try {
            return processOAuth2User(oAuth2UserRequest,oAuth2User);//(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
       // return oidcUser;
    }

    private OidcUser processOAuth2User(OidcUserRequest oAuth2UserRequest, OidcUser oAuth2User ){//(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {

        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());

        if(StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        Optional<SSOuser> userOptional = ssOuserRepository.findByEmail(oAuth2UserInfo.getEmail());

        SSOuser ssOuser;

        if(userOptional.isPresent())
        {
            ssOuser = userOptional.get();

                    if(!ssOuser.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                        throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
                                ssOuser.getProvider() + " account. Please use your " + ssOuser.getProvider() +
                                " account to login.");
                    }
            ssOuser = updateExistingUser(ssOuser, oAuth2UserInfo);
        }
         else {
            ssOuser = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }

          //UserPrincipal.create(ssOuser);

        //

           //oAuth2User.getAttributes()

         return oAuth2User;
    }

//    private Optional<User> getUser(String username){
//
//        User user = null;
//
//        Optional<Student> student = studentRepository.findByUsername(username);
//        if(student.isPresent())
//        {
//            user =  student.get();
//            user.setUserType(UserType.STUDENT);
//
//            return Optional.of(user);
//        }
//
//        Optional<Expert> expert = expertRepository.findByUsername(username);
//        if(expert.isPresent()){
//
//            user = expert.get() ;
//            user.setUserType(UserType.EXPERT);
//
//            return Optional.of(user);
//        }
//
//        Optional<Admin> admin = adminRepository.findByUsername(username);
//        if(admin.isPresent()){
//
//            user = admin.get() ;
//            user.setUserType(UserType.ADMIN);
//
//            return Optional.of(user);
//        }
//
//       return Optional.of(user);
//
//    }



    private SSOuser registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        SSOuser user = new SSOuser();

        user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        user.setProviderId(oAuth2UserInfo.getId());
        user.setFirstName(oAuth2UserInfo.getName());
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setImageUrl(oAuth2UserInfo.getImageUrl());

        LocalDateTime today = LocalDateTime.now();
        String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MMM-yy"));
        user.setCreatedAt(formattedDate);
        user.setUpdatedAt(formattedDate);


        return ssOuserRepository.save(user);
    }

    private SSOuser updateExistingUser(SSOuser existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setFirstName(oAuth2UserInfo.getName());
        existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
        return ssOuserRepository.save(existingUser);
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
