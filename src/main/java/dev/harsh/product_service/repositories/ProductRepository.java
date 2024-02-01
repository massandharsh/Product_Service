package dev.harsh.product_service.repositories;

import dev.harsh.product_service.models.Category;
import dev.harsh.product_service.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByTitleEquals(String title);
    Optional<Product> findByTitleEqualsAndPrice_PriceOrderByPrice_price(String title,double price);
    List<Product> findAllByPrice_CurrencyEquals(String currency);

    long count();
    long countAllByPrice_Currency(String currency);


    @Query(value = CustomQueries.FIND_ALL_BY_TITLE,nativeQuery = true)
    List<Product> findByCustomTitle(String title);

    //Hibernate query language
    @Query(value = CustomQueries.FIND_ALL_BY_TITLE_HIBERNATE,nativeQuery = false)
    List<Product> findByCustomTitleNonNative(String title);

    List<Product> findAllByCategoryIn(List<Category> category);

    Page<Product> findAllByTitleContaining(String title, Pageable pageable);



}
