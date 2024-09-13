package biblioteca.example.biblioteca.domain.MinhaLista;

import biblioteca.example.biblioteca.domain.Item.Item;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "minha_lista")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MinhaLista {

    @EmbeddedId
    private MinhaListaID id;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    public MinhaLista(MinhaListaRequestDTO dados, Item item){

        this.id = new MinhaListaID(dados.usuarioId(), dados.itemId());
        this.item = item;
    }
}
