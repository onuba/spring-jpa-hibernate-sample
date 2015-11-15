package org.onuba.example.dao;

import java.util.List;

import org.onuba.example.dto.ProductDTO;
import org.onuba.example.dto.StoreDTO;
import org.onuba.example.entity.StoreEntity;

public interface StoreDao extends DAO<StoreEntity, Integer> {

    /**
     * Get all stores
     * 
     * @return
     */
    List<StoreDTO> getAllStores();

    /**
     * For a concrete store Id, retrieve all the products in stock
     * 
     * @param storeId
     *            Store Id
     * @return
     */
    List<ProductDTO> getProductsForStore(Integer storeId);
    
}
