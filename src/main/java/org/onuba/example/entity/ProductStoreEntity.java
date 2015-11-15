package org.onuba.example.entity;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Tabla de RELACIóN DE PáGINAS CON FICHEROS MEDIA
 * 
 * @author fhernandez
 * 
 */
@Entity
@Table(name = "PRODUCT_STORE")
@AssociationOverrides({ @AssociationOverride(name = "pk.store", joinColumns = @JoinColumn(name = "ID_STORE")),
        @AssociationOverride(name = "pk.product", joinColumns = @JoinColumn(name = "ID_PRODUCT")) })
public class ProductStoreEntity {

    @EmbeddedId
    private ProductStoreEntityPK pk = new ProductStoreEntityPK();

    @Transient
    public StoreEntity getStore() {
        return pk.getStore();
    }
    
    @Column(name = "STOCK", nullable = false)
    private Integer stock;

    public void setStore(StoreEntity store) {
        pk.setStore(store);
    }

    @Transient
    public ProductEntity getProduct() {
        return pk.getProduct();
    }

    public void setProduct(ProductEntity product) {
        pk.setProduct(product);
    }

    /**
     * @return the stock
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public final int hashCode() {
        return pk.hashCode();
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public final boolean equals(Object obj) {
        return pk.equals(obj);
    }
}
