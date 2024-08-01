package biblioteca.example.biblioteca.domain;

import java.time.LocalDate;

import biblioteca.example.biblioteca.Enum.EUsuario;

public record UsuarioRequestDTO(Long id, String nome, String login, String senha, LocalDate data_registro, EUsuario status){}
