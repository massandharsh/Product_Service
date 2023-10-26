package dev.harsh.product_service.repositories;

import dev.harsh.product_service.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PriceRepository extends JpaRepository<Price, UUID> {

}
