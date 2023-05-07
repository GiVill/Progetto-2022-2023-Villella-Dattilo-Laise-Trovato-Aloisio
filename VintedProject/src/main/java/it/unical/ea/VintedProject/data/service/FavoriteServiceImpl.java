package it.unical.ea.VintedProject.data.service;

import it.unical.ea.VintedProject.data.dao.FavoriteDao;
import it.unical.ea.VintedProject.data.entities.Favorite;
import it.unical.ea.VintedProject.dto.FavoriteDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class FavoriteServiceImpl implements  FavoriteService{

    private final FavoriteDao favoriteDao;
    private final ModelMapper modelMapper;

    @Override
    public FavoriteDto save(FavoriteDto favoriteDto) {
        Favorite student = modelMapper.map(favoriteDto, Favorite.class);
        Favorite s = favoriteDao.save(student);
        return modelMapper.map(s, FavoriteDto.class);
    }
    @Override
    public void add(Favorite favorite) { favoriteDao.save(favorite); }

    @Override
    public void save(Favorite favorite) { favoriteDao.save(favorite); }

    @Override
    public Favorite getFavoriteById(Long id) {
            return favoriteDao.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Don't exist any favorite with id: [%s]", id)));
    }

    public void deleteFavoriteById(Long id) { favoriteDao.deleteById(id);
    }

}
