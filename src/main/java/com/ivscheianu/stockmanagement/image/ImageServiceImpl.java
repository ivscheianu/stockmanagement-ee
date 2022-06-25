package com.ivscheianu.stockmanagement.image;

import com.ivscheianu.base.persistence.EntityRepository;
import com.ivscheianu.base.service.AbstractEntityService;
import com.ivscheianu.base.service.EntityMapper;
import com.ivscheianu.common.blob.BlobStorageBroker;
import com.ivscheianu.stockmanagement.bucket.BucketDTO;
import com.ivscheianu.stockmanagement.product.ProductDTO;
import com.ivscheianu.stockmanagement.product.ProductService;
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

    @Inject
    private ProductService productService;

    @Override
    protected EntityRepository<Long, ImageDO> getRepository() {
        return imageRepository;
    }

    @Override
    protected EntityMapper<ImageDTO, ImageDO> getMapper() {
        return imageMapper;
    }

    @Override
    public ImageDTO uploadImageForProduct(final String barcode,
                                          final InputStream inputStream,
                                          final FormDataContentDisposition fileDetails) {
        final ProductDTO product = productService.getByBarcode(barcode);
        final BucketDTO bucket = userService.getCurrentUser().getBucket();
        final String bucketName = bucket.getName();
        final String fileName = fileDetails.getFileName();
        blobStorageBroker.publicUpload(bucketName, fileName, inputStream);
        final String link = blobStorageBroker.getLink(bucketName, fileName);
        final ImageDTO image = ImageDTO
            .builder()
            .bucket(bucket)
            .location(link)
            .product(product)
            .storageType(StorageType.BUCKET.getId())
            .identifier(fileName)
            .build();
        return save(image);
    }
}
