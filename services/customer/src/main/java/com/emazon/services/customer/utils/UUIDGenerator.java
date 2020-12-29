package com.emazon.services.customer.utils;

import java.util.UUID;

public final class UUIDGenerator{

    public static String generate(){
        return UUID.randomUUID().toString().replaceAll("-","").toUpperCase().substring(0,6);
    }

}
