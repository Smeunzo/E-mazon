package com.emazon.services.inventory.security;


public class JWTUtil {
    public static final String SECRET = "6/ovo6F1A4bw0CxvuHLXN4ttds7tnLn8Y+U7f42NQucDeybKRukEkprXO0iRbJTA";
    public static final String AUTH_HEADER = "Authorization";
    public static final String PREFIXTOKEN = "Bearer ";
    public static final String PRODUCTS_ROUTE = "/inventory/products/**";
    public static final String PRODUCT_ROUTE = "/inventory/product/**";
    public static final String CATEGORIES_ROUTE = "/inventory/categories/**";
    public static final String CATEGORY_ROUTE = "/inventory/category/**";
    public static final String RATE_ROUTE = "/inventory/rate/**";

}
