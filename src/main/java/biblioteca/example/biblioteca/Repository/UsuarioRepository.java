package biblioteca.example.biblioteca.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import biblioteca.example.biblioteca.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Page<Usuario> findAll(Pageable pageable);
    UserDetails findByLogin(String login);
    List<Usuario> findByNome(String nome);

    @Query("SELECT usuario FROM Usuario usuario WHERE usuario.login = :login")
    Optional<Usuario> buscarPorLogin(String login);
}
