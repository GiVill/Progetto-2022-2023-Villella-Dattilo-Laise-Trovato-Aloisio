package it.unical.ea.VintedProject.data.controller;
import it.unical.ea.VintedProject.data.entities.Favorite;
import it.unical.ea.VintedProject.data.service.interfaces.FavoriteService;
import it.unical.ea.VintedProject.dto.FavoriteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;
    @PostMapping
    public ResponseEntity<FavoriteDto> addFavorite(@RequestBody FavoriteDto favoriteDto) {
        return ResponseEntity.ok(favoriteService.save(favoriteDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Favorite> getFavoriteById(@PathVariable Long id) {
        return ResponseEntity.ok(favoriteService.getFavoriteById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavoriteById(@PathVariable Long id) {
        favoriteService.deleteFavoriteById(id);
        return ResponseEntity.noContent().build();
    }


}


