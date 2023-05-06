package it.unical.ea.VintedProject.data.dao;

import it.unical.ea.VintedProject.data.entities.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteDao extends JpaRepository<Favorite,Long> {
}
