package biblioteca.example.biblioteca.domain.Item;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="item")
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome, tipo, editora, descricao, imagem, publicacao, numPagina;
    
    public Item(ItemRequestDTO dados) {
        this.nome = dados.nome();
        this.tipo = dados.tipo();
        this.editora = dados.editora();
        this.descricao = dados.descricao();
        this.imagem = dados.imagem();
        this.numPagina = dados.numPagina();
        this.publicacao = dados.publicacao();
    }
}
