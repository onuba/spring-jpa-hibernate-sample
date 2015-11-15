package org.onuba.example.mappers;

import org.onuba.example.dto.StoreDTO;
import org.onuba.example.entity.StoreEntity;

import com.google.common.base.Function;

/**
 * Do a transformation from StoreDTO to Store Entity 
 * @author fhernans
 *
 */
public enum StoreDtoToEntity implements Function<StoreDTO, StoreEntity> {

    INSTANCIA;

    public StoreEntity apply(StoreDTO dto) {

        final StoreEntity entity = new StoreEntity();

        entity.setAddress(dto.getAddress());
        entity.setCity(dto.getCity());
        entity.setId(dto.getId());
        
        return entity;
    }

}
