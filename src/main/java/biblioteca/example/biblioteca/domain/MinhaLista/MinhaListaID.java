package biblioteca.example.biblioteca.domain.MinhaLista;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor

public class MinhaListaID implements Serializable {

    private Long usuarioID;
    private Long itemID;

    public MinhaListaID(Long usuarioId, Long itemId){
        usuarioID = usuarioId;
        itemID = itemId;
    }
}
