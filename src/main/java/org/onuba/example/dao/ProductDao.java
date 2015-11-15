package org.onuba.example.dao;

import java.util.List;

import org.onuba.example.dto.ProductDTO;
import org.onuba.example.dto.StoreDTO;
import org.onuba.example.entity.ProductEntity;

public interface ProductDao extends DAO<ProductEntity, Integer> {

    /**
     * Get all products.
     * 
     * @return
     */
    List<ProductDTO> getProductsInfo();

    /**
     * Get all stores where a product is
     * 
     * @param productId
     *            Product Id.
     * @return
     */
    List<StoreDTO> getStoresForProduct(Integer productId);

    /**
     * Get the actual stock for a product in a concrete store
     * 
     * @param productId
     *            Product Id.
     *            
     * @param soreIdId
     *            Store Id.
     * @return -1 if there is not products for that store, the number of products otherwise
     */
    Integer getProductStockInStore(Integer productId, Integer storeId);
    
    /**
     * Get the actual stock for a product in a concrete store
     * 
     * @param stock
     *            Number of products in a store
     *            
     * @param productId
     *            Product Id.
     *            
     * @param soreIdId
     *            Store Id.
     * 
     */
    void setProductStockInStore(Integer stock, Integer productId, Integer storeId);
    
}
