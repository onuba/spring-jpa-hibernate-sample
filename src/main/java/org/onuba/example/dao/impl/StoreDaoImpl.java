package org.onuba.example.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Query;

import org.onuba.example.dao.StoreDao;
import org.onuba.example.dto.ProductDTO;
import org.onuba.example.dto.StoreDTO;
import org.onuba.example.entity.ProductEntity;
import org.onuba.example.entity.StoreEntity;
import org.onuba.example.mappers.ProductEntityToDto;
import org.onuba.example.mappers.StoreEntityToDto;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

@Repository("storeDao")
public class StoreDaoImpl extends DAOImpl<StoreEntity, Integer> implements StoreDao {

    /**
     * Get all stores
     * 
     * @return
     */
    public List<StoreDTO> getAllStores() {
        
        final Query q = createNamedQuery(StoreEntity.ALL);
        
        return Lists.transform(list(q), StoreEntityToDto.INSTANCE);
    }
    

    /**
     * For a concrete store Id, retrieve all the products in stock
     * 
     * @param storeId
     *            Store Id
     * @return
     */
    public List<ProductDTO> getProductsForStore(Integer storeId) {
        
        final StoreEntity store = findById(storeId);
        // Map to List<ProductEntity>
        final List<ProductEntity> productList = store.getProductsInStore().stream().map(ps -> ps.getProduct()).collect(Collectors.toList()); 
        
        return Lists.transform(productList, ProductEntityToDto.INSTANCE);
    }
}
