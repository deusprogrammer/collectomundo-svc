package com.trinary.collecto.utils;

public class CouchBaseHelper {
    public static Long extractIdNumber(String couchbaseId) {
        if (couchbaseId == null) {
            return null;
        }

        return Long.parseLong(couchbaseId.substring(couchbaseId.indexOf(":") + 1));
    }
}