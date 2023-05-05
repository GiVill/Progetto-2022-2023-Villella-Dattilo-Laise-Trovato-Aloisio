package it.unical.ea.VintedProject.data.dao;

import it.unical.ea.VintedProject.data.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<Product,Long> {
}
