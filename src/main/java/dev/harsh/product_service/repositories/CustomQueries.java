package dev.harsh.product_service.repositories;

public interface CustomQueries {
    String FIND_ALL_BY_TITLE = "select * from Product where title = :title";
    String FIND_ALL_BY_TITLE_HIBERNATE = "select p from Product p where p.title = :title";
}
