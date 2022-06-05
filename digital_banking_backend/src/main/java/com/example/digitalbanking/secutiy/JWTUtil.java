package com.example.digitalbanking.secutiy;

public class JWTUtil {
    public static final String SECRET="mySecret1234";
    public static final String AUTH_HADER="Authorization";
    public static final String PREFIX="Bearer ";
    public static final Long EXPIRE_ACCESS_TOKEN=120000L;
    public static final Long EXPIRE_REFRESH_TOKEN=31104000000L;
}

