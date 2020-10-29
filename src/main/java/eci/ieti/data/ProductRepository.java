package eci.ieti.data;

import eci.ieti.data.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, Long> {

    Page<Product> findByDescriptionContaining(String description, Pageable pageable);
}
