package org.onuba.example.mappers;

import org.onuba.example.dto.ProductDTO;
import org.onuba.example.entity.ProductEntity;

import com.google.common.base.Function;

/**
 * Do a transformation from ProductDTO to Product Entity 
 * @author fhernans
 *
 */
public enum ProductDtoToEntity implements Function<ProductDTO, ProductEntity> {

    INSTANCIA;

    public ProductEntity apply(ProductDTO dto) {

        final ProductEntity entity = new ProductEntity();

        entity.setDescription(dto.getDescription());
        entity.setName(dto.getName());
        entity.setId(dto.getId());
        entity.setPrice(dto.getPrice());
        
        return entity;
    }

}
