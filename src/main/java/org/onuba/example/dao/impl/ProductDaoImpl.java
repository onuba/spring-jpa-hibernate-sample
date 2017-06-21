package org.onuba.example.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.Query;

import org.onuba.example.dao.ProductDao;
import org.onuba.example.dao.ProductStoreDao;
import org.onuba.example.dto.ProductDTO;
import org.onuba.example.dto.StoreDTO;
import org.onuba.example.entity.ProductEntity;
import org.onuba.example.entity.ProductStoreEntity;
import org.onuba.example.entity.StoreEntity;
import org.onuba.example.mappers.ProductEntityToDto;
import org.onuba.example.mappers.StoreEntityToDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

@Repository("productDao")
public class ProductDaoImpl extends DAOImpl<ProductEntity, Integer> implements ProductDao {

    @Autowired
    @Qualifier("productStoreDao")
    private ProductStoreDao psDao;
    
    /**
     * Get all products.
     * 
     * @return
     */
    public List<ProductDTO> getProductsInfo() {
        
        final Query q = createNamedQuery(ProductEntity.ALL);

        return Lists.transform(list(q), ProductEntityToDto.INSTANCE) ;
    }
    

    /**
     * Get all stores where a product is
     * 
     * @param productId
     *            Product Id.
     * @return
     */
    public List<StoreDTO> getStoresForProduct(Integer productId) {
        
        final ProductEntity product = findById(productId);
        // Map to List<ProductEntity>
        final List<StoreEntity> storeList = product.getStoresForProduct().stream().map(ps -> ps.getStore()).collect(Collectors.toList()); 
        
        return Lists.transform(storeList, StoreEntityToDto.INSTANCE);
    }
    
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
    public Integer getProductStockInStore(Integer productId, Integer storeId) {
        
        final ProductEntity product = findById(productId);
        
        final Optional<ProductStoreEntity> ps = product.getStoresForProduct().stream().filter(sp -> sp.getStore().getId().equals(storeId)).findFirst();
        
        if (ps.isPresent()) {
            
            return ps.get().getStock();
        }
            
        return -1;
    }
    
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
    public void setProductStockInStore(Integer stock, Integer productId, Integer storeId) {
        
        final ProductEntity product = findById(productId);
        
        final Optional<ProductStoreEntity> ps = product.getStoresForProduct().stream().filter(sp -> sp.getStore().getId().equals(storeId)).findFirst();
        
        if (ps.isPresent()) {
            
            final ProductStoreEntity psEntity = ps.get();
            
            psEntity.setStock(stock);
            psDao.update(psEntity);
        }
    }
}
