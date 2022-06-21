package com.ivscheianu;

import com.ivscheianu.stockmanagement.auth.AuthController;
import com.ivscheianu.stockmanagement.image.ImageController;
import com.ivscheianu.stockmanagement.product.ProductController;
import com.ivscheianu.stockmanagement.role.RoleEnum;
import com.ivscheianu.stockmanagement.stock.StockController;
import com.ivscheianu.stockmanagement.user.UserController;
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
            StockController.class,
            ProductController.class,
            MultiPartFeature.class,
            AuthController.class,
            UserController.class,
            ImageController.class
        );
    }
}