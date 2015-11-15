package org.onuba.example.dao;

import javax.persistence.Query;

public interface DAOView {

    Query createNativeQuery(String query);


}
