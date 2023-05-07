package it.unical.ea.VintedProject.data.service.interfaces;
import it.unical.ea.VintedProject.data.dao.FavoriteDao;
import it.unical.ea.VintedProject.data.entities.Favorite;
import it.unical.ea.VintedProject.dto.FavoriteDto;



public interface FavoriteService {
    FavoriteDto save(FavoriteDto favoriteDto);

    void add(Favorite favorite);

    void save(Favorite favorite);

    Favorite getFavoriteById(Long id);

    void deleteFavoriteById(Long id);

}
