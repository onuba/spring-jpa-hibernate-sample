package org.onuba.example.facade.impl;

import java.util.List;

import org.onuba.example.dto.ProductDTO;
import org.onuba.example.dto.StoreDTO;
import org.onuba.example.facade.StockFacade;
import org.onuba.example.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("stockFacade")
public class StockFacadeImpl implements StockFacade {

    /**
     * Logger de la aplicación, usando la libreria logback.
     */
    private static final transient Logger LOGGER = LoggerFactory.getLogger(StockFacadeImpl.class);

    @Autowired
    @Qualifier("stockService")
    private StockService ls;


    /**
     * Get a store with the supplied id
     * 
     * @param storeId Store Id
     *  
     * @return
     */
    @Transactional(readOnly = true)
    public StoreDTO getStoreById(Integer storeId) {
        return ls.getStoreById(storeId);
    }
    
    /**
     * Get all stores
     * 
     * @return
     */
    @Transactional(readOnly = true)
    public List<StoreDTO> getAllStores() {
        return ls.getAllStores();
    }

    /**
     * For a concrete product Id, retrieve all the stores that have in stock that product
     * 
     * @param productId
     *            Product Id
     * @return
     */
    @Transactional(readOnly = true)
    public List<StoreDTO> getStoresForProduct(Integer productId) {
        return ls.getStoresForProduct(productId);
    }
    
    /**
     * Get a product with the supplied id.
     * 
     * @param productId Product ID
     * 
     * @return
     * 
     */
    @Transactional(readOnly = true)
    public ProductDTO getProductById(Integer productId) {
        return ls.getProductById(productId);
    }
    
    /**
     * Get all products.
     * 
     * @return
     */
    @Transactional(readOnly = true)
    public List<ProductDTO> getProductsInfo() {
        return ls.getProductsInfo();
    }

    /**
     * Get all products for a store
     * 
     * @param storeId
     *            Store Id.
     * @return
     */
    @Transactional(readOnly = true)
    public List<ProductDTO> getProductsForStore(Integer storeId) {
        return ls.getProductsForStore(storeId);
    }
    
    /**
     * Set the actual stock for a product in a concrete store
     * 
     * @param productId
     *            Product Id.
     *            
     * @param storeId
     *            Store Id.
     *            
     * @return
     */
    @Transactional(readOnly = true)
    public Integer getProductStockInStore(Integer productId, Integer storeId) {
        
        return ls.getProductStockInStore(productId, storeId);
    }
    
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
     *            
     * @return
     */
    @Transactional
    public void setProductStockInStore(Integer stock, Integer productId, Integer storeId) {
        
        ls.setProductStockInStore(stock, productId, storeId);
    }
    
    /**
     * Set the actual stock for a product in all stores
     * 
     * @param stock
     *            new stock
     *            
     * @param productId
     *            Product Id.
     *            
     * @param storeId
     *            Store Id.
     *            
     * @return
     */
    @Transactional
    public void setProductStockInAllStore(Integer stock, Integer productId) {
        
        final List<StoreDTO> stores = ls.getStoresForProduct(productId);
        
        stores.stream().forEach(s -> {ls.setProductStockInStore(stock, productId, s.getId());});
        
    }
    
    /**
     * Add the given stock number to the actual stock for a product in a concrete store
     * 
     * @param stock
     *            stock amount to add
     *            
     * @param productId
     *            Product Id.
     *            
     * @param storeId
     *            Store Id.
     *            
     * @return
     */
    @Transactional
    public void addProductStockInStore(Integer stock, Integer productId, Integer storeId) {
        final Integer actualStock = ls.getProductStockInStore(productId, storeId);
        
        LOGGER.debug("Actual stock: " + actualStock);
        
        ls.setProductStockInStore(stock + actualStock, productId, storeId);
    }
}
