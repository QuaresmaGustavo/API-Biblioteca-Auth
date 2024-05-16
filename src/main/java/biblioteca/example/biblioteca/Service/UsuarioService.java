package biblioteca.example.biblioteca.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblioteca.example.biblioteca.Repository.UsuarioRepository;
import biblioteca.example.biblioteca.Repository.UsuarioRequest;
import biblioteca.example.biblioteca.domain.Usuario;

@Service
public class UsuarioService {

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
        Usuario novoUsuario = new Usuario(dados);
        repository.save(novoUsuario);
        return novoUsuario;
    }

    public Usuario atualizarUsuario(Long id, UsuarioRequest dados) {
        Optional<Usuario> idUsuario = buscarUsuario(id);
        if (idUsuario.isPresent()) {
            Usuario usuarioAtualizado = idUsuario.get();
            usuarioAtualizado.setNome(dados.nome());
            usuarioAtualizado.setEmail(dados.email());
            usuarioAtualizado.setSenha(dados.senha());
            usuarioAtualizado.setIdade(dados.idade());
            return repository.save(usuarioAtualizado);
            }
            else {
                throw new ResolutionException("Erro ao atualizar dados do usuario!");
            } 
    }
    
}
