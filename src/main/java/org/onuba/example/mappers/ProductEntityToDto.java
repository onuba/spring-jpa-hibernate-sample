package org.onuba.example.mappers;

import org.onuba.example.dto.ProductDTO;
import org.onuba.example.entity.ProductEntity;

import com.google.common.base.Function;

/**
 * Do a transformation from ProductEntity to Product DTO 
 * @author fhernans
 *
 */
public enum ProductEntityToDto implements Function<ProductEntity, ProductDTO> {

    INSTANCE;

    public ProductDTO apply(ProductEntity entity) {

        final ProductDTO dto = new ProductDTO();

        dto.setDescription(entity.getDescription());
        dto.setName(entity.getName());
        dto.setId(entity.getId());
        dto.setPrice(entity.getPrice());
        
        return dto;
    }

}
