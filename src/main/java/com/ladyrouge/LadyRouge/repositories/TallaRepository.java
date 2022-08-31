package com.ladyrouge.LadyRouge.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ladyrouge.LadyRouge.models.Talla;

/*
 * This is the same as the above except it is using the CrudRepository interface instead of the interface above.
 */
@Repository
public interface TallaRepository extends CrudRepository<Talla, Long> {
    
    public Iterable<Talla> findByNombre(String nombre);
}
    
