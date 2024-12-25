package main.loginandgeo.config;


import main.loginandgeo.dto.AccountDTO;
import org.springframework.security.core.Authentication;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.loginandgeo.services.GeneralService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Map;

public class AuthHandler implements AuthenticationSuccessHandler {
    private final GeneralService generalService;

    public AuthHandler(GeneralService generalService) {
        this.generalService = generalService;
    }

    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication ) throws IOException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        OAuth2User user = token.getPrincipal();

        Map<String, Object> attributes = user.getAttributes();


       AccountDTO accountDTO = AccountDTO.of(
        (String) attributes.get("email"),
               (String) attributes.get("name"),
               (String) attributes.get("picture")
               );

       generalService.addAccount(accountDTO);

       response.sendRedirect("/rate");

    }

}
