package it.unical.ea.VintedProject.data.service;
import it.unical.ea.VintedProject.data.entities.Favorite;


public interface FavoriteService {
    void add(Favorite favorite);

    Favorite save(Favorite favorite);

    Favorite getFavoriteById(Long id);

    void deleteFavoriteById(Long id);

}
