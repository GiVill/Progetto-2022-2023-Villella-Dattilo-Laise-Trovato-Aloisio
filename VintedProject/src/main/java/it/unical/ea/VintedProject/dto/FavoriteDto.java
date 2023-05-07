package it.unical.ea.VintedProject.dto;
import it.unical.ea.VintedProject.data.entities.Favorite;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class FavoriteDto {

    @Generated
    private Long id;

    private Long favoriteInsertionId;

    private Long userFavoriteId;

}
