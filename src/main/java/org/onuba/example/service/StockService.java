package org.onuba.example.service;

import java.util.List;

import org.onuba.example.dto.ProductDTO;
import org.onuba.example.dto.StoreDTO;

/**
 * Stock service interface
 * 
 * @author fhernans
 *
 */
public interface StockService {

    /**
     * Get a store with the supplied id
     * 
     * @param storeId Store Id
     *  
     * @return
     */
    StoreDTO getStoreById(Integer storeId);
    
    /**
     * Get all stores
     * 
     * @return
     */
    List<StoreDTO> getAllStores();

    /**
     * For a concrete product Id, retrieve all the stores that have in stock that product
     * 
     * @param productId
     *            Product Id
     * @return
     */
    List<StoreDTO> getStoresForProduct(Integer productId);
    
    /**
     * Get a product with the supplied id.
     * 
     * @param productId Product ID
     * 
     * @return
     */
    ProductDTO getProductById(Integer productId);
    
    /**
     * Get all products.
     * 
     * @return
     */
    List<ProductDTO> getProductsInfo();

    /**
     * Get all products for a store
     * 
     * @param storeId
     *            Store Id.
     * @return
     */
    List<ProductDTO> getProductsForStore(Integer storeId);
    
    /**
     * Get the actual stock for a product in a concrete store
     * 
     * @return
     */
    Integer getProductStockInStore(Integer productId, Integer storeId);
    
    /**
     * Set the actual stock for a product in a concrete store
     * 
     * @param stock
     *            new stock
     *            
     * @param productId
     *            Product Id.
     *            
     * @param storeId
     *            Store Id.                      
     * @return
     */
    void setProductStockInStore(Integer stock, Integer productId, Integer storeId);
}
