package org.onuba.example.dto;

import java.io.Serializable;

/**
 * Clase DTO que modela el concepto de Buffer
 * 
 * @author fhernandez
 * 
 */
public class ProductDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -188325954512608769L;

    private Integer id;
    
    private String name;

    private String description;

    private Double price;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

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
}
