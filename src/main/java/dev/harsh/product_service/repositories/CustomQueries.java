package dev.harsh.product_service.repositories;

import dev.harsh.product_service.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface CustomQueries {
    String FIND_ALL_BY_TITLE = "select * from Product where title = :title";
    String FIND_ALL_BY_TITLE_HIBERNATE = "select p from Product p where p.title = :title";

    @Repository("productElasticRepository")
    interface ProductElasticRepository extends ElasticsearchRepository<Product, UUID> {
        Page<Product> searchAllByTitle(String title, Pageable pageable);
    }
}
