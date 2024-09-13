package biblioteca.example.biblioteca.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import biblioteca.example.biblioteca.Repository.UsuarioRepository;
import biblioteca.example.biblioteca.domain.ResponseModel;
import biblioteca.example.biblioteca.domain.Usuario.Usuario;
import biblioteca.example.biblioteca.domain.Usuario.UsuarioRequestDTO;

@Service
public class UsuarioService implements UserDetailsService{

    @Autowired
    private UsuarioRepository repository;

    public ResponseModel<Usuario> buscarTodosUsuarios(Integer pagina, Integer quantidade) {
        Page<Usuario> usuarioPorPagina = repository.findAll(PageRequest.of(pagina, quantidade));
        Long totalUsuario = repository.count();
        return new ResponseModel<>(usuarioPorPagina.getContent(), totalUsuario);
    }

    public Optional<Usuario> buscarPorId(Long id) {
        Optional<Usuario> usuarioId = repository.findById(id);
        return usuarioId;
    }

    public List<Usuario> buscarPorNome(String nome) {
        List<Usuario> listaUsuarios = repository.findByNome(nome);
        return listaUsuarios;
    }

    public Usuario cadastrarUsuario(UsuarioRequestDTO dados) {
        String encryptarSenha = new BCryptPasswordEncoder().encode(dados.senha());
        Usuario novoUsuario = new Usuario(dados, encryptarSenha);
        repository.save(novoUsuario);
        return novoUsuario;
    }

    public Usuario atualizarUsuario(Long id, UsuarioRequestDTO dados) {
        Optional<Usuario> idUsuario = buscarPorId(id);
        if (idUsuario.isPresent()) {
            Usuario usuarioAtualizado = idUsuario.get();

            String encryptarSenha = new BCryptPasswordEncoder().encode(dados.senha());

            usuarioAtualizado.setNome(dados.nome());
            usuarioAtualizado.setLogin(dados.login());
            usuarioAtualizado.setSenha(encryptarSenha);
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
