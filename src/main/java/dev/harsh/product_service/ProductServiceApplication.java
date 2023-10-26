package dev.harsh.product_service;

import dev.harsh.product_service.models.Category;
import dev.harsh.product_service.models.Product;
import dev.harsh.product_service.repositories.CategoryRepository;
import dev.harsh.product_service.repositories.ProductRepository;
import jakarta.persistence.Column;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class ProductServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
}
