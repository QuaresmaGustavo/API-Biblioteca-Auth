package biblioteca.example.biblioteca.Controllers;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.parser.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import biblioteca.example.biblioteca.Repository.UsuarioRepository;
import biblioteca.example.biblioteca.Repository.UsuarioRequest;
import biblioteca.example.biblioteca.Service.UsuarioService;
import biblioteca.example.biblioteca.domain.Usuario;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired 
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioRequest usuarioRequest;

    @Autowired
    private UsuarioService service;

    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> buscarTodosUsuarios() {
        List<Usuario> usuarios = service.buscarTodosUsuarios();
        return ResponseEntity.ok().body(usuarios);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> buscarUsuario(@RequestParam Long id) {
        Optional<Usuario> usuario = service.buscarUsuario(id);
        return ResponseEntity.ok(usuario);
    }
    
    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody UsuarioRequest dados) {
        Usuario cadastro = service.cadastrarUsuario(dados);
        return ResponseEntity.ok().body(cadastro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequest dados) {
        Usuario usuarioAtualizado = service.atualizarUsuario(id, dados);        
        return ResponseEntity.ok(usuarioAtualizado);
    }
}
