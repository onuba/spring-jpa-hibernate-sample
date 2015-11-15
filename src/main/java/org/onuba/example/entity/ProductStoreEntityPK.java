package org.onuba.example.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class ProductStoreEntityPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    private ProductEntity product;
    @ManyToOne
    private StoreEntity store;

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProductStoreEntityPK that = (ProductStoreEntityPK) o;

        if (product != null ? !product.equals(that.product) : that.product != null) {
            return false;
        }
        if (store != null ? !store.equals(that.store) : that.store != null) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        int result;
        result = (product != null ? product.hashCode() : 0);
        result = 31 * result + (store != null ? store.hashCode() : 0);
        return result;
    }
}
