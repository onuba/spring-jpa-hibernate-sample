package org.onuba.example.facade.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.onuba.example.dto.ProductDTO;
import org.onuba.example.dto.StoreDTO;
import org.onuba.example.facade.StockFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:example-context-test.xml")
public class StockFacadeImplTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

	@Autowired
	@Qualifier("stockFacade")
	private StockFacade facade;
	
	@Test
    public void testStoresById() {
        
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("The store identifier can not be null!");
        StoreDTO store = facade.getStoreById(null);
        
        Assert.assertNull(store);
        
        // Not exists
        store = facade.getStoreById(10);
        
        Assert.assertNull(store);
        
        store = facade.getStoreById(1);
        
        Assert.assertNotNull(store);
        Assert.assertEquals("Sevilla", store.getCity());
    }
	
	@Test
    public void testGetAllStores() {
        final List<StoreDTO> storeList = facade.getAllStores();
        
        // Hay 4 almacenes
        Assert.assertEquals(4, storeList.size());
    }
	
	@Test
    public void testGetStoresForProduct() {
        
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("The product identifier can not be null!");
        List<StoreDTO> storeList = facade.getStoresForProduct(null);
        
        Assert.assertNull(storeList);
        
        // Not exists
        storeList = facade.getStoresForProduct(10);
        
        Assert.assertNull(storeList);
        
        storeList = facade.getStoresForProduct(1);
        
        Assert.assertNotNull(storeList);
        Assert.assertEquals(3, storeList.size());
    }
	
	@Test
    public void testProductById() {
        
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("The product identifier can not be null!");
        ProductDTO product = facade.getProductById(null);
        
        Assert.assertNull(product);
        
        // Not exists
        product = facade.getProductById(10);
        
        Assert.assertNull(product);
        
        product = facade.getProductById(8);
        
        Assert.assertNotNull(product);
        Assert.assertEquals("iphone6s", product.getName());
    }
	
	@Test
	public void testGetProductInfo() {
		final List<ProductDTO> productList = facade.getProductsInfo();
		
		// Hay 8 productos
		Assert.assertEquals(8, productList.size());
	}
	
	@Test
    public void testProductByStore() {
        
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("The store identifier can not be null!");
        List<ProductDTO> productList = facade.getProductsForStore(null);
        
        Assert.assertNull(productList);
        
        // Not exists
        productList = facade.getProductsForStore(10);
        
        Assert.assertNull(productList);
        
        productList = facade.getProductsForStore(4);
        
        Assert.assertNotNull(productList);
        Assert.assertEquals(5, productList.size());
    }
	
	@Test
    public void testGetProductStockInStore() {
        
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("The store identifier can not be null!");
        Integer stock = facade.getProductStockInStore(null, null);
        
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("The product identifier can not be null!");
        stock = facade.getProductStockInStore(100, null);
        
        // Not exists
        stock = facade.getProductStockInStore(100, 1);
        
        Assert.assertEquals(-1, stock.intValue());
        
        // Not exists
        stock = facade.getProductStockInStore(8, 10);
        
        Assert.assertEquals(-1, stock.intValue());
        
        stock = facade.getProductStockInStore(4, 2);
        
        Assert.assertEquals(9, stock.intValue());
        
        
    }
	
	@Test
    public void testSetProductStockInAllStore() {
        
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("The product identifier can not be null!");
        facade.setProductStockInAllStore(null, null);
        
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("The stock identifier can not be null or a negative value!");
        facade.setProductStockInAllStore(100, null);
        
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("The stock identifier can not be null or a negative value!");
        facade.setProductStockInAllStore(100, -1);
        
        // Not exists
        facade.setProductStockInAllStore(100, 100);
        
        Integer stock = facade.getProductStockInStore(1, 1);
        
        Assert.assertEquals(10, stock.intValue());
        
        facade.setProductStockInAllStore(1, 100);
        
        List<StoreDTO> stores = facade.getStoresForProduct(1);
        
        Assert.assertEquals(3, stores.size());
        stores.stream().forEach(s -> {
            Integer storeStock = facade.getProductStockInStore(1, s.getId());
            Assert.assertEquals(100, storeStock.intValue());
        });
    }
	
	@Test
    public void testSetProductStockInStore() {
        
	    // state restaured
	    facade.setProductStockInStore(1, 1, 10);
	    
	    exception.expect(IllegalArgumentException.class);
        exception.expectMessage("The store identifier can not be null!");
        facade.setProductStockInStore(null, null, null);
        
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("The product identifier can not be null!");
        facade.setProductStockInStore(100, null, null);
        
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("The stock identifier can not be null or a negative value!");
        facade.setProductStockInStore(100, 2, null);
        
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("The stock identifier can not be null or a negative value!");
        facade.setProductStockInStore(100, 2, -1);
        
        // Not exists
        facade.setProductStockInStore(100, 1, 0);
        
        Integer stock = facade.getProductStockInStore(1, 1);
        
        Assert.assertEquals(10, stock.intValue());
        
        facade.setProductStockInStore(1, 1, 100);
        
        stock = facade.getProductStockInStore(1, 1);
        
        Assert.assertEquals(100, stock.intValue());
    }
}
