package com.studomia.studomia.security.socialLogin;

import com.studomia.studomia.exceptions.OAuth2AuthenticationProcessingException;
import com.studomia.studomia.security.socialLogin.oauthUser.FacebookOAuth2UserInfo;
import com.studomia.studomia.security.socialLogin.oauthUser.GithubOAuth2UserInfo;
import com.studomia.studomia.security.socialLogin.oauthUser.GoogleOAuth2UserInfo;
import com.studomia.studomia.security.socialLogin.oauthUser.OAuth2UserInfo;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.facebook.toString())) {
            return new FacebookOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.github.toString())) {
            return new GithubOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
