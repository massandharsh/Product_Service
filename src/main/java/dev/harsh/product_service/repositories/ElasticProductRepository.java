package dev.harsh.product_service.repositories;

import dev.harsh.product_service.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ElasticProductRepository extends ElasticsearchRepository<Product, UUID> {
    Page<Product> searchAllByTitle(String title, Pageable pageable);
}
