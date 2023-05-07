package it.unical.ea.VintedProject.data.dao;

import it.unical.ea.VintedProject.data.entities.Favorite;
import it.unical.ea.VintedProject.data.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteDao extends JpaRepository<Favorite,Long> {
    List<Order> findByFavoriteInsertion(Long favoriteInsertion);


}
