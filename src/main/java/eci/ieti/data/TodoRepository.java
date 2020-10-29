package eci.ieti.data;

import eci.ieti.data.model.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TodoRepository extends MongoRepository<Todo, String> {
    Page<Todo> findByResponsible(String responsible, Pageable pageable);

    @Query("{'priority': {$gt:4}}")
    List<Todo> existsByResponsible();

}
