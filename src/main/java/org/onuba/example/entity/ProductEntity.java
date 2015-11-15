package org.onuba.example.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Products entity, maps to the PRODUCTS Table
 * 
 * @author fhernandez
 * 
 */
@Entity
@Table(name = "PRODUCT")
@NamedQueries({ @NamedQuery(name = ProductEntity.ALL, query = "select e from ProductEntity e") })
public class ProductEntity extends SimpleEntity {

    private static final long serialVersionUID = 1937606674801403192L;

    /** Por el identificador. */
    public static final String ALL = "product.All";

    @Column(name = "NAME", length = 50, nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", length = 200, nullable = false)
    private String description;

    @Column(name = "PRICE", nullable = false)
    private Double price;

    // Hibernate maneja las colecciones como Bags, un bag es una coleccion
    // desordenada que permite duplicados. Pero cuando tenemos un fecth EAGER
    // no podemos tener bags de bags, salta la excepcion:
    // Caused by: org.hibernate.HibernateException: cannot simultaneously fetch multiple bags
    // Una de las soluciones es poner como fecth LAZY, pero podemos tener el probelma
    // de que si necesitamos una de las colecciones fuera de una sesion, saltara la excepcion
    // LazyInitializationException.
    // Podemos forzar que se cargue con Hibernate.initialize.
    // Otra alternativa es usar Set en vez de colecciones si no manejamos duplicados
    // ya que hibernate no lo tratara como bag.
    // Otra opcion es usar la anotacion @IndexColumn(name="columna",base=1) de forma que
    // tratara a la coleccion como lista (Hay que definirla lista), no cmo bags.
    // Teniendo esto en cuenta, debemos pensar cuales son las necesidades de cargar algo como EAGER,
    // que debe ser LAZY, los tipos de datos, etc...
    //
    // Libro tiene una relacion de una a muchos con LibrosPremios
    // este campo refleja dicha relacion. El campo mappedBy indica el campo
    // del lado 'owner' que refleja la FK en la entidad
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.product")
    private Set<ProductStoreEntity> storesForProduct;
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the storesForProduct
     */
    public Set<ProductStoreEntity> getStoresForProduct() {
        return storesForProduct;
    }

    /**
     * @param storesForProduct the storesForProduct to set
     */
    public void setStoresForProduct(Set<ProductStoreEntity> storesForProduct) {
        this.storesForProduct = storesForProduct;
    }
}
