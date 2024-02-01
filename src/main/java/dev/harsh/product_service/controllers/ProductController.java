package dev.harsh.product_service.controllers;


import dev.harsh.product_service.dtos.GenericProductDto;
import dev.harsh.product_service.exceptions.NotFoundException;
import dev.harsh.product_service.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * Adds prefix /api/v1/products to the request
 * */

@RestController
@RequestMapping("/products/")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<GenericProductDto>> getAllProducts(){
        List<GenericProductDto> genericProductDtos =  productService.getAllProducts();
        if(genericProductDtos.isEmpty()){
            return new ResponseEntity<>(
                    genericProductDtos,
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(
                genericProductDtos,
                HttpStatus.OK
        );
    }

    // localhost:8080/products/123
    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") String id){
        GenericProductDto productById = productService.getProductById(id);
        if(productById == null){
            throw new NotFoundException("Product not found");
        }
        return productById;
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto){
        return productService.createProduct(genericProductDto);
    }

    @PutMapping("{id}")
    public GenericProductDto updateProductById(@RequestBody GenericProductDto genericProductDto,@PathVariable("id") String id){
        return productService.updateProductById(genericProductDto,id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") String id){
        return new ResponseEntity<>(this.productService.deleteProductById(id), HttpStatus.OK);
    }

}
