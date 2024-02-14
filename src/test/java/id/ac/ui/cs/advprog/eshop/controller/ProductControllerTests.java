package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTests {
    @Mock
    ProductService productService;
    @Mock
    private Model model;
    @InjectMocks
    ProductController productController;

    @Test
    void testCreateProductPage(){
        String result = productController.createProductPage(model);
        assertEquals("createProduct",result);
    }

    @Test
    void testCreateProductPost(){
        Product product = new Product();
        when(productService.create(product)).thenReturn(product);
        String result = productController.createProductPost(product,model);
        assertEquals("redirect:list",result);
    }

    @Test
    void testDeleteProductPost(){
        Product product = new Product();
        String productId = product.getProductId();
        when(productService.delete(productId)).thenReturn(productId);
        String result = productController.deleteProductPost(productId);
        assertEquals("redirect:/product/list",result);
    }

    @Test
    void testProductListPage(){
        List<Product> products = productService.findAll();
        when(productService.findAll()).thenReturn(products);
        String result = productController.productListPage(model);
        assertEquals("productList",result);
    }

    @Test
    void testEditProductPage(){
        Product product = new Product();
        String result = productController.editProductPage(product.getProductId(),model);
        assertEquals("editProduct",result);
    }

    @Test
    void testEditProductPost(){
        Product product = new Product();
        productService.create(product);
        when(productService.findById(product.getProductId())).thenReturn(product);
        String result = productController.editProductPost(product.getProductId(), "Test",1);
        assertEquals("redirect:/product/list",result);
    }
}
