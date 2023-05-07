package it.unical.ea.VintedProject.data.service;
import it.unical.ea.VintedProject.data.dao.FavoriteDao;
import it.unical.ea.VintedProject.data.entities.Favorite;


public interface FavoriteService {
    void add(Favorite favorite);

    Favorite save(Favorite favorite);

    Favorite getFavoriteById(Long id);


    Long getFavoriteInsertionId(FavoriteDao favoriteDto);

    Long getUserFavoriteId(FavoriteDao favoriteDto);

    void deleteFavoriteById(Long id);

}
