package com.meubixin.api.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecurityConstants {

    public static String JWT_SECRET;

    public static String JWT_HEADER;

    public SecurityConstants(
            @Value("${app.security.jwt-secret:jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4}") String jwtSecret,
            @Value("${app.security.jwt-header:Authorization}") String jwtHeader
    ) {
        SecurityConstants.JWT_SECRET = jwtSecret;
        SecurityConstants.JWT_HEADER = jwtHeader;
    }
}
