package biblioteca.example.biblioteca.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import biblioteca.example.biblioteca.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public UserDetails findByLogin(String login);
    public List<Usuario> findByNome(String nome);
}
