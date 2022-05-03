package com.ivscheianu;

import com.google.common.collect.ImmutableSet;
import com.ivscheianu.product.ProductResource;

import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("resources")
public class App extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        return ImmutableSet.of(
            ProductResource.class
        );
    }
}