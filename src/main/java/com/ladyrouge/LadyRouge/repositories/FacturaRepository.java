package com.ladyrouge.LadyRouge.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ladyrouge.LadyRouge.models.Factura;

/*
 * This is the same as the above except it is using the CrudRepository interface instead of the interface above.
 */
@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
}
    
