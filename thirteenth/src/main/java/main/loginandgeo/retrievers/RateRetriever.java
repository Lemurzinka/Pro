package main.loginandgeo.retrievers;

import main.loginandgeo.json.Rate;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RateRetriever {

    private static String URL =
            "http://data.fixer.io/api/latest?access_key=1a0c4a508ca7a31399eea86626f6d148";

    @Cacheable("rates")
public Rate getRate() {
    try {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Rate> response = restTemplate.getForEntity(URL, Rate.class);
        return response.getBody();
    }catch(Exception e) {
    return new Rate();
    }
}
}
