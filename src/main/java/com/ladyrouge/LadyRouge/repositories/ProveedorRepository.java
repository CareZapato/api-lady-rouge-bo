package com.ladyrouge.LadyRouge.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ladyrouge.LadyRouge.models.Proveedor;

/*
 * This is the same as the above except it is using the CrudRepository interface instead of the interface above.
 */
@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    
    public Iterable<Proveedor> findByNombre(String nombre);
}
    
