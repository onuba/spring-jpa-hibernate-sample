package org.onuba.example.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.google.common.base.Objects;

@MappedSuperclass
public class SimpleEntity implements Serializable {

    private static final long serialVersionUID = -482667093443976364L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public final int hashCode() {
        return (id == null) ? 0 : id.hashCode();
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof SimpleEntity) {
            final SimpleEntity e = (SimpleEntity) obj;
            return Objects.equal(getId(), e.getId());
        }
        return false;
    }
}