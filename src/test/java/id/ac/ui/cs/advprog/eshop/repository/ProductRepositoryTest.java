package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp(){
    }
    @Test
    void testCreateAndFind(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(),savedProduct.getProductId());
        assertEquals(product.getProductName(),savedProduct.getProductName());
        assertEquals(product.getProductQuantity(),savedProduct.getProductQuantity());
    }

    @Test
    void testCreateDeleteAndFind(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(),savedProduct.getProductId());
        assertEquals(product.getProductName(),savedProduct.getProductName());
        assertEquals(product.getProductQuantity(),savedProduct.getProductQuantity());

        productRepository.delete(product.getProductId());
        productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void CreateEditAndFind(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        String oldName = savedProduct.getProductName();
        int oldQuantity = savedProduct.getProductQuantity();
        assertEquals(product.getProductId(),savedProduct.getProductId());
        assertEquals(product.getProductName(),oldName);
        assertEquals(product.getProductQuantity(),oldQuantity);

        Product newProduct = new Product();
        newProduct.setProductName("Kecap Cap Bombong");
        newProduct.setProductQuantity(120);
        productRepository.update(product.getProductId(),newProduct);

        Product productToCheck = productRepository.findById(product.getProductId());
        assertNotEquals(product.getProductName(),oldName);
        assertNotEquals(product.getProductQuantity(),oldQuantity);

        productIterator = productRepository.findAll();
        savedProduct = productIterator.next();
        assertEquals(product.getProductId(),savedProduct.getProductId());
        assertEquals(product.getProductName(),savedProduct.getProductName());
        assertEquals(product.getProductQuantity(),savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty(){
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct(){
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9d346-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(),savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(),savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void negativeDeleteTest(){
        Product newProduct = new Product();
        newProduct.setProductName("ada");
        newProduct.setProductQuantity(10);
        productRepository.create(newProduct);

        Product testProduct = new Product();
        testProduct.setProductName("tidak ada");
        testProduct.setProductQuantity(1);
        productRepository.delete(testProduct.getProductId());

        Iterator<Product> productIterator = productRepository.findAll();
        productIterator.next();
        assertFalse(productIterator.hasNext());
    }
}
