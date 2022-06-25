package com.ivscheianu;

import com.github.phillipkruger.apiee.ApieeService;
import com.ivscheianu.stockmanagement.auth.AuthController;
import com.ivscheianu.stockmanagement.image.ImageController;
import com.ivscheianu.stockmanagement.product.ProductController;
import com.ivscheianu.stockmanagement.role.RoleEnum;
import com.ivscheianu.stockmanagement.stock.StockController;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import java.util.Set;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@DeclareRoles({RoleEnum.Constants.ROLE_ADMIN, RoleEnum.Constants.ROLE_USER})
@ApplicationPath("resources")
public class App extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        return Set.of(
            ApieeService.class,
            MultiPartFeature.class,
            AuthController.class,
            StockController.class,
            ProductController.class,
            ImageController.class
        );
    }
}