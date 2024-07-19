package biblioteca.example.biblioteca.domain;

public record ItemRequestDTO(Long id, String nome, String tipo, String editora,String descricao,String imagem ,Long numPagina, Long ano) {}
