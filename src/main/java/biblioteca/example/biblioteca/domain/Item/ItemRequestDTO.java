package biblioteca.example.biblioteca.domain.Item;

public record ItemRequestDTO(Long id,
                             String nome,
                             String tipo,
                             String editora,
                             String descricao,
                             String imagem ,
                             String numPagina, 
                             String publicacao) {}
