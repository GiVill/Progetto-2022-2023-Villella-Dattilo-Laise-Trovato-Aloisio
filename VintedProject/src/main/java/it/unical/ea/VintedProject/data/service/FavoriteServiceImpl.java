package it.unical.ea.VintedProject.data.service;

import it.unical.ea.VintedProject.data.dao.FavoriteDao;
import it.unical.ea.VintedProject.data.entities.Favorite;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class FavoriteServiceImpl implements  FavoriteService{
    private final FavoriteDao favoriteDao;

    @Override
    public void add(Favorite favorite) { favoriteDao.save(favorite); }

    @Override
    public Favorite save(Favorite favorite) { return favoriteDao.save(favorite); }

    @Override
    public Favorite getFavoriteById(Long id) {
            return favoriteDao.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Don't exist any favorite with id: [%s]", id)));
    }

    @Override
    public Long getFavoriteInsertionId(FavoriteDao favoriteDto) {
        return favoriteDto.favoriteInsertionId();
    }
    @Override
    public Long getUserFavoriteId(FavoriteDao favoriteDto) {
        return favoriteDto.userFavoriteId();
    }

    public void deleteFavoriteById(Long id) { favoriteDao.deleteById(id);
    }

}
