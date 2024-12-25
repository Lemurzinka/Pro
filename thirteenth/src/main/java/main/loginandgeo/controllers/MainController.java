package main.loginandgeo.controllers;


import jakarta.servlet.http.HttpServletRequest;
import main.loginandgeo.dto.AccountDTO;
import main.loginandgeo.json.Rate;
import main.loginandgeo.retrievers.RateRetriever;
import main.loginandgeo.services.GeneralService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {


private final RateRetriever rateRetriever;
    private final GeneralService generalService;

   public MainController(RateRetriever rateRetriever, GeneralService generalService) {
       this.rateRetriever = rateRetriever;
       this.generalService = generalService;
   }

    public AccountDTO accountDTO(OAuth2AuthenticationToken token) {
        Map<String, Object> attrs = token.getPrincipal().getAttributes();

        String email = (String) attrs.get("email");
        String name = (String) attrs.get("name");
        String pictureUrl = (String) attrs.get("picture");

        return AccountDTO.of(email, name, pictureUrl);
    }

    @GetMapping("/rate")
    public Rate rate(HttpServletRequest request) {
       return rateRetriever.getRate();
    }

    @GetMapping("/user")
    public Map<String, String> getUser(OAuth2AuthenticationToken token) {
       Map<String, String> response = new HashMap<>();
   if(token == null){
       response.put("name", "Guest");
   } else {
       String name = (String) token.getPrincipal().getAttributes().get("name");
       response.put("name", name);
   }
   return response;
   }


}
