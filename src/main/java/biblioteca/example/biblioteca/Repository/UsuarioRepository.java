package biblioteca.example.biblioteca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import biblioteca.example.biblioteca.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}
