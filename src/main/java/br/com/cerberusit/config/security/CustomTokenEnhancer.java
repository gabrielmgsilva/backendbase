package br.com.cerberusit.config.security;

import br.com.cerberusit.controller.response.MenuAccessResponseDto;
import br.com.cerberusit.controller.response.ProfileMenuAccessResponseDto;
import br.com.cerberusit.security.UserPrincipal;
import org.modelmapper.ModelMapper;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        UserPrincipal userAuth = (UserPrincipal) authentication.getPrincipal();

        Map<String, Object> addInfo = new HashMap<>();
        addInfo.put("user", userAuth.getUsername());
        addInfo.put("profile", userAuth.getUser().getProfile().getDescription());
        addInfo.put("menu", userAuth.getMenuAccess());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfo);
        return accessToken;
    }

}
