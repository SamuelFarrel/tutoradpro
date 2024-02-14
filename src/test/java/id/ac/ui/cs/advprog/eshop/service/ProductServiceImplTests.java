package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        when(productRepository.create(product)).thenReturn(product);

        Product result = productService.create(product);

        assertEquals(product, result);
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindProductById() {
        Product product = new Product();
        String productId = product.getProductId();
        when(productRepository.findById(productId)).thenReturn(product);

        Product result = productService.findById(productId);

        assertEquals(product, result);
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testDeleteProduct() {
        String productId = "1";
        when(productRepository.delete(productId)).thenReturn(productId);

        String result = productService.delete(productId);

        assertEquals(productId, result);
        verify(productRepository, times(1)).delete(productId);
    }

    @Test
    void testFindAllProducts() {
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(new Product());
        mockProducts.add(new Product());

        Iterator<Product> productIterator = mockProducts.iterator();
        when(productRepository.findAll()).thenReturn(productIterator);

        List<Product> result = productService.findAll();

        assertEquals(mockProducts, result);
        verify(productRepository, times(1)).findAll();
    }
}
