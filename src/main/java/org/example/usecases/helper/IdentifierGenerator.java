package org.example.usecases.helper;

import java.util.concurrent.atomic.AtomicInteger;

public class IdentifierGenerator {

    public static final AtomicInteger id = new AtomicInteger();

    public static int generateUniqueId(){
        return id.incrementAndGet();
    }
}
