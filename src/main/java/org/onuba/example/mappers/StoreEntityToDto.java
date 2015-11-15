package org.onuba.example.mappers;

import org.onuba.example.dto.StoreDTO;
import org.onuba.example.entity.StoreEntity;

import com.google.common.base.Function;

/**
 * Do a transformation from StoreEntity to Store DTO 
 * @author fhernans
 *
 */
public enum StoreEntityToDto implements Function<StoreEntity, StoreDTO> {

    INSTANCE;

    public StoreDTO apply(StoreEntity entity) {

        final StoreDTO dto = new StoreDTO();

        dto.setAddress(entity.getAddress());
        dto.setCity(entity.getCity());
        dto.setId(entity.getId());
        
        return dto;
    }

}
