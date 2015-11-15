package org.onuba.example.dao.impl;

import org.onuba.example.dao.ProductStoreDao;
import org.onuba.example.entity.ProductStoreEntity;
import org.springframework.stereotype.Repository;

@Repository("productStoreDao")
public class ProductStoreDaoImpl extends DAOImpl<ProductStoreEntity, Integer> implements ProductStoreDao {

}
