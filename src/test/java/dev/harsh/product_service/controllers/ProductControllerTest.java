package dev.harsh.product_service.controllers;

import dev.harsh.product_service.dtos.GenericProductDto;
import dev.harsh.product_service.exceptions.NotFoundException;
import dev.harsh.product_service.models.Product;
import dev.harsh.product_service.services.FakeStoreProductService;
import dev.harsh.product_service.services.ProductService;
import dev.harsh.product_service.thirdpartyclient.fakestore.FakeStoreProductDto;
import dev.harsh.product_service.thirdpartyclient.fakestore.FakeStoreThirdParty;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertNull;


@SpringBootTest

public class ProductControllerTest {
//    @MockBean
//    private final FakeStoreProductService fakeStoreProductService;
//
//    private final ProductController productController;
//
//    @MockBean
//    private final ProductService productService;
//
//    @MockBean
//    private final FakeStoreThirdParty fakeStoreThirdParty;
//
//    @Captor
//    private ArgumentCaptor<String> idCaptor;
//
//    @Captor
//    private ArgumentCaptor<Long> longIdCaptor;
//    @Autowired
//    public ProductControllerTest(FakeStoreProductService fakeStoreProductService, ProductController productController,
//                                 @Qualifier("selfProductService") ProductService productService, FakeStoreThirdParty fakeStoreThirdParty){
//        this.fakeStoreProductService = fakeStoreProductService;
//        this.productController = productController;
//        this.productService = productService;
//        this.fakeStoreThirdParty = fakeStoreThirdParty;
//    }
//
//
//    @Test
//    void returnsNullWhenProductDoesntExist(){
//        String id = "101";
//        //wherever it is called productService it will return null order does not matter
//        when(productService.getProductById(id)).thenReturn(null);
//        when(productService.getProductById(any(String.class))).thenCallRealMethod();
//        System.out.println(productService.getClass());
//        GenericProductDto genericProductDto = productController.getProductById(id);
//        Assertions.assertNull(genericProductDto,"Product should be null");
//    }
//
//    @Test
//    void throwsExceptionWhenProductDoesntExist(){
//        String id = "101";
//        //wherever it is called productService it will return null order does not matter
//        when(productService.getProductById(any(String.class)))
//                .thenReturn(null);
//        assertThrows(NotFoundException.class,()->productController.getProductById(id));
//    }
//
//
//
//    @Test
//    @DisplayName("1 + 1 equals 2")
//    void testOnePlusOneEqualsTrue(){
////        System.out.println("!!This is true!!");
////        assertEquals (11 , 1 + 1,"one plus one is not coming to eleven");
////          Assertions.assertNull(fakeStoreProductService.getProductById("101"));
//
//          //But what if exception is thrown in that case we can test
//        assertThrows(NotFoundException.class,()->fakeStoreProductService.getProductById("101"),"Not found exception should be thrown");
//    }
//
//    //Assertion framework
//    //---> allows you to make assertion
//    //---> allows you to make checks
//
//    @Test
//    void additionShouldBeCorrect(){
//        assertTrue( -1 + -1 == -2, "adding 2 negatives is not correct");
//        assertTrue( 1 + 1 == 2,"one plus one should be two");
//    }
//
//    @Test
//    void productControllerCallProductServiceWithSameProductId() throws NotFoundException{
//        //check the product service is getting called by the exact same param as controller
//        String id = "101";
//        when(productService.getProductById(any()))
//                .thenReturn(new GenericProductDto());
//
//        productController.getProductById(id);
//        verify(productService).getProductById(idCaptor.capture());
//
//        assertEquals(id,idCaptor.getValue());
//    }
//
//    @Test
//    void productControllerCallFakeStoreThirdPartyWithSameProductId() throws NotFoundException{
//        //check the product service is getting called by the exact same param as controller
//        String id = "10";
//        when(fakeStoreProductService.getProductById(any()))
//                .thenCallRealMethod();
//        when(fakeStoreProductService.getFakeStoreThirdParty())
//                .thenReturn(fakeStoreThirdParty);
//
//        fakeStoreProductService.getProductById(id);
//        verify(fakeStoreThirdParty).getProductById(longIdCaptor.capture());
//
//        assertEquals(Integer.parseInt(id),longIdCaptor.getValue());
//    }

}

