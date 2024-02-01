package dev.harsh.product_service.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class TokenValidator {
    private RestTemplateBuilder restTemplateBuilder;
    /**
     *
     * Calls user service to validate the token.
     * If token is not valid in that case return optional of empty.
     * @param token String
     * @return Optional<JwtObject></JwtObject>
     * */
    public Optional<JwtObject> validateToken(String token){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity responseEntity = restTemplate.getForEntity("http://localhost:8081/api/v1/auth/validate", ResponseEntity.class);
        return Optional.of(null);
    }
}
