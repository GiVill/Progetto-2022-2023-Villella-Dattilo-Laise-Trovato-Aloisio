package it.unical.ea.VintedProject.data.controller;

import it.unical.ea.VintedProject.data.entities.BasicInsertion;
import it.unical.ea.VintedProject.data.entities.Favorite;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.data.service.FavoriteService;
import it.unical.ea.VintedProject.dto.FavoriteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/favorites")
public class FavoriteController {
    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping
    public ResponseEntity<FavoriteDto> addFavorite(@RequestBody FavoriteDto favoriteDto) {
        Favorite favorite = new Favorite();
        favorite.setFavoriteInsertion(new BasicInsertion(favoriteDto.getFavoriteInsertionId()));
        favorite.setUserFavorite(new User(favoriteDto.getUserFavoriteId()));
        favoriteService.add(favorite);

        favoriteDto.setId(favorite.getId());
        return ResponseEntity.ok(favoriteDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FavoriteDto> getFavoriteById(@PathVariable Long id) {
        Favorite favorite = favoriteService.getFavoriteById(id);
        FavoriteDto favoriteDto = new FavoriteDto();
        favoriteDto.setId(favorite.getId());
        favoriteDto.setFavoriteInsertionId(favorite.getFavoriteInsertion().getId());
        favoriteDto.setUserFavoriteId(favorite.getUserFavorite().getId());

        return ResponseEntity.ok(favoriteDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavoriteById(@PathVariable Long id) {
        favoriteService.deleteFavoriteById(id);
        return ResponseEntity.noContent().build();
    }

}
