package com.ivscheianu.stockmanagement.image;

import com.ivscheianu.base.service.EntityService;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.InputStream;

public interface ImageService extends EntityService<Long, ImageDTO> {
    ImageDTO uploadImageForProduct(final String barcode, final InputStream inputStream, final FormDataContentDisposition fileDetails);
}
