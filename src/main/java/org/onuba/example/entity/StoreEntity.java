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
 * 
 * 
 * @author fhernandez
 * 
 */
@Entity
@Table(name = "STORE")
@NamedQueries({@NamedQuery(name = StoreEntity.ALL, query = "select e from StoreEntity e")})
public class StoreEntity extends SimpleEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 1825102655282436686L;

    /** Por el identificador. */
    public static final String ALL = "store.All";
    
    @Column(name = "CITY", length = 50, nullable = false)
    private String city;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.store")
    private Set<ProductStoreEntity> productsInStore;

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the productsInStore
     */
    public Set<ProductStoreEntity> getProductsInStore() {
        return productsInStore;
    }

    /**
     * @param productsInStore the productsInStore to set
     */
    public void setProductsInStore(Set<ProductStoreEntity> productsInStore) {
        this.productsInStore = productsInStore;
    }
}
