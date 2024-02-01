package dev.harsh.product_service.security;

import lombok.Data;

import java.util.Date;

@Data
public class JwtObject {
    private String email;
    private Long createdAt;
    private Long expiryAt;
    private String[] roles;
}
