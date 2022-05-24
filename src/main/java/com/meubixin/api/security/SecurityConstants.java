package com.meubixin.api.security;

import org.springframework.beans.factory.annotation.Value;

public class SecurityConstants {

    @Value("${security.JWT_SECRET:jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4}")
    public static String JWT_SECRET;

    @Value("${security.JWT_HEADER:Authorization")
    public static String JWT_HEADER;

}
