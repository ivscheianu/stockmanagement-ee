package com.ivscheianu.stockmanagement.image;

import com.ivscheianu.base.persistence.EntityRepository;
import com.ivscheianu.base.service.AbstractEntityService;
import com.ivscheianu.base.service.EntityMapper;
import com.ivscheianu.common.blob.BlobStorageBroker;
import com.ivscheianu.stockmanagement.user.UserService;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.InputStream;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Stateless
@Transactional
public class ImageServiceImpl extends AbstractEntityService<Long, ImageDTO, ImageDO> implements ImageService {

    @Inject
    private ImageRepository imageRepository;

    @Inject
    private ImageMapper imageMapper;

    @Inject
    private BlobStorageBroker blobStorageBroker;

    @Inject
    private UserService userService;

    @Override
    protected EntityRepository<Long, ImageDO> getRepository() {
        return imageRepository;
    }

    @Override
    protected EntityMapper<ImageDTO, ImageDO> getMapper() {
        return imageMapper;
    }

    @Override
    public String uploadImage(final InputStream inputStream, final FormDataContentDisposition fileDetails) {
        final String bucketName = userService.getCurrentUser().getBucket().getName();
        final String fileName = fileDetails.getFileName();
        blobStorageBroker.upload(bucketName, fileName, inputStream);
        return blobStorageBroker.getLink(bucketName, fileName);
    }
}
