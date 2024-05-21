package biblioteca.example.biblioteca.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import biblioteca.example.biblioteca.Repository.UsuarioRepository;
import biblioteca.example.biblioteca.domain.Usuario;
import biblioteca.example.biblioteca.domain.UsuarioRequest;

@Service
public class UsuarioService implements UserDetailsService{

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> buscarTodosUsuarios() {
        return repository.findAll();
    }

    public Optional<Usuario> buscarUsuario(Long id) {
        Optional<Usuario> usuarioId = repository.findById(id);
        return usuarioId;
    }

    public Usuario cadastrarUsuario(UsuarioRequest dados) {
        String encryptarSenha = new BCryptPasswordEncoder().encode(dados.senha());
        Usuario novoUsuario = new Usuario(dados, encryptarSenha);
        repository.save(novoUsuario);
        return novoUsuario;
    }

    public Usuario atualizarUsuario(Long id, UsuarioRequest dados) {
        Optional<Usuario> idUsuario = buscarUsuario(id);
        if (idUsuario.isPresent()) {
            Usuario usuarioAtualizado = idUsuario.get();

            String encryptarSenha = new BCryptPasswordEncoder().encode(dados.senha());

            usuarioAtualizado.setNome(dados.nome());
            usuarioAtualizado.setLogin(dados.login());
            usuarioAtualizado.setSenha(encryptarSenha);
            usuarioAtualizado.setIdade(dados.idade());
            return repository.save(usuarioAtualizado);
            }
            else {
                throw new ResolutionException("Erro ao atualizar dados do usuario!");
            } 
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return repository.findByLogin(login);
    }
    
}
