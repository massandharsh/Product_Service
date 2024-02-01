package dev.harsh.product_service.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.harsh.product_service.dtos.GenericProductDto;
import dev.harsh.product_service.models.Category;
import dev.harsh.product_service.services.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerWebMvcTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    @Qualifier("selfProductService")
//    private ProductService productService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Value("{security.oauth2.resourceserver.jwt.issuer-uri}")
//    private String authServerUri;
//    @Test
//    void getAllProductsReturnsEmptyListWhenNoProduct() throws Exception {
//       when(productService.getAllProducts())
//               .thenReturn(new ArrayList<>());
//
//        mockMvc.perform(get("/products/"))
//                .andExpect(status().is(404))
//                .andExpect(content().string("[]"));
//
//    }
//
//    @Test
//    void getAllProductReturnsProductListWhenPresent() throws Exception {
//
//        List<GenericProductDto> genericProductDtos = List.of(GenericProductDto.builder().id("1").title("harsh").build(),GenericProductDto.builder().id("2").title("phone").build());
//        when(productService.getAllProducts())
//                .thenReturn(genericProductDtos);
//        mockMvc.perform(get("/products/"))
//                .andExpect(status().is(200))
//                .andExpect(content().string(objectMapper.writeValueAsString(genericProductDtos)));
//    }
//
//    @Test
//    void createProductShouldCreateANewProduct() throws Exception{
//        GenericProductDto genericProductDto = GenericProductDto.builder()
//                .title("Iphone 15 pro")
//                .category(Category.builder().name("mobile phones").build())
//                .description("best iphone")
//                .build();
//
//        GenericProductDto expectedProduct = GenericProductDto.builder()
//                .id("550e8400-e29b-41d4-a716-446655440000")
//                .title("Iphone 15 pro")
//                .category(Category.builder().name("mobile phones").build())
//                .description("best iphone")
//                .build();
//
//        when(productService.createProduct(any(GenericProductDto.class)))
//                .thenReturn(expectedProduct);
//        mockMvc.perform(post("/products").content(objectMapper.writeValueAsString(genericProductDto)).contentType("application/json"))
//
//                .andExpect(
//                        content().string(objectMapper.writeValueAsString(expectedProduct))
//                ).andExpect(status().is(200))
//                .andExpect(jsonPath("$.student.name",is("harsh")))
//                .andExpect(jsonPath("$.length()",is(2)));
//    }
//
//    @Test
//    public void getProductWhenNoProductIsPresent() throws Exception{
//        //Mocking
//        when(productService.getAllProducts())
//                .thenReturn(new ArrayList<>());
//
//
//        mockMvc.perform(get("/products/"))
//                .andExpect(content().string(objectMapper.writeValueAsString(new ArrayList<>())))
//                .andExpect(status().is(404));
//
//
//    }


}
