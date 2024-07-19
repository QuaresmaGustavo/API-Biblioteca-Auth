package biblioteca.example.biblioteca.domain;

import biblioteca.example.biblioteca.Enum.EUsuario;

public record UsuarioRequestDTO(Long id, String nome, String login, String senha, Long idade, EUsuario status){}
