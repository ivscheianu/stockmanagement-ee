package com.ivscheianu.stockmanagement.image;

import com.ivscheianu.stockmanagement.role.RoleEnum;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import java.io.InputStream;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Stateless
@Path("images")
public class ImageController {

    @Inject
    private ImageService imageService;

    @POST
    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @RolesAllowed({RoleEnum.Constants.ROLE_USER})
    public String uploadNewFile(@FormDataParam("file") final InputStream inputStream,
                                @FormDataParam("file") final FormDataContentDisposition fileDetails,
                                @Context final UriInfo uriInfo) {
        return imageService.uploadImage(inputStream, fileDetails);
    }
}
