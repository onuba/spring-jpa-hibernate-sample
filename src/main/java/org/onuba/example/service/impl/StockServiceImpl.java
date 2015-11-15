package org.onuba.example.service.impl;

import java.util.List;

import org.onuba.example.dao.ProductDao;
import org.onuba.example.dao.StoreDao;
import org.onuba.example.dto.ProductDTO;
import org.onuba.example.dto.StoreDTO;
import org.onuba.example.mappers.ProductEntityToDto;
import org.onuba.example.mappers.StoreEntityToDto;
import org.onuba.example.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

/**
 * Stock service implementation
 * 
 * @author fhernans
 * 
 */
@Service("stockService")
public class StockServiceImpl implements StockService {

    @Autowired
    @Qualifier("storeDao")
    private StoreDao storeDao;
    
    @Autowired
    @Qualifier("productDao")
    private ProductDao productDao;

    /**
     * Get a store with the supplied id
     * 
     * @param storeId Store Id
     *  
     * @return
     */
    public StoreDTO getStoreById(Integer storeId) {
        
        Preconditions.checkArgument(storeId != null, "The store identifier can not be null!");
        
        return StoreEntityToDto.INSTANCE.apply(storeDao.findById(storeId));
    }
    
    /**
     * Get all stores
     * 
     * @return
     */
    public List<StoreDTO> getAllStores() {
        
        return storeDao.getAllStores();
    }

    /**
     * For a concrete product Id, retrieve all the stores that have in stock that product
     * 
     * @param productId
     *            Product Id
     * @return
     */
    public List<StoreDTO> getStoresForProduct(Integer productId) {
        
        Preconditions.checkArgument(productId != null, "The product identifier can not be null!");
        
        return productDao.getStoresForProduct(productId);
    }
    
    /**
     * Get a product with the supplied id.
     * 
     * @param productId Product ID
     * 
     * @return
     */
    public ProductDTO getProductById(Integer productId) {
        
        Preconditions.checkArgument(productId != null, "The product identifier can not be null!");
        
        return ProductEntityToDto.INSTANCE.apply(productDao.findById(productId));
    }
    
    /**
     * Get all products.
     * 
     * @return
     */
    public List<ProductDTO> getProductsInfo() {
        
        return productDao.getProductsInfo();
    }

    /**
     * Get all products for a store
     * 
     * @param storeId
     *            Store Id.
     * @return
     */
    public List<ProductDTO> getProductsForStore(Integer storeId) {
        
        Preconditions.checkArgument(storeId != null, "The store identifier can not be null!");
        
        return storeDao.getProductsForStore(storeId);
    }
    
    /**
     * Get the actual stock for a product in a concrete store
     * 
     *  @param productId
     *            Product Id.
     *            
     * @param soreIdId
     *            Store Id.
     *            
     * @return
     */
    public Integer getProductStockInStore(Integer productId, Integer storeId) {
        
        Preconditions.checkArgument(storeId != null, "The store identifier can not be null!");
        Preconditions.checkArgument(productId != null, "The product identifier can not be null!");
        
        return productDao.getProductStockInStore(productId, storeId);
    }

    @Override
    public void setProductStockInStore(Integer stock, Integer productId, Integer storeId) {
        
        Preconditions.checkArgument(storeId != null, "The store identifier can not be null!");
        Preconditions.checkArgument(productId != null, "The product identifier can not be null!");
        Preconditions.checkArgument(stock != null && stock >= 0, "The stock identifier can not be null or a negative value!");
        
        productDao.setProductStockInStore(stock, productId, storeId);
    }
}
