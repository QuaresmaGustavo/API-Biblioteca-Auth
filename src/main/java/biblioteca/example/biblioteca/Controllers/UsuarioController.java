package biblioteca.example.biblioteca.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import biblioteca.example.biblioteca.Enum.EUsuario;
import biblioteca.example.biblioteca.Repository.UsuarioRepository;
import biblioteca.example.biblioteca.Service.TokenService;
import biblioteca.example.biblioteca.Service.UsuarioService;
import biblioteca.example.biblioteca.domain.AutenticacaoRequest;
import biblioteca.example.biblioteca.domain.LoginRequestDTO;
import biblioteca.example.biblioteca.domain.Usuario;
import biblioteca.example.biblioteca.domain.UsuarioRequestDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:8080")
public class UsuarioController {
    @Autowired 
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService service;

    @Autowired
    private AuthenticationManager autenticacao;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/todos")
    public ResponseEntity<List<Usuario>> buscarTodosUsuarios() {
        return ResponseEntity.ok().body(service.buscarTodosUsuarios());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> buscarPorId(@PathVariable @Validated Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/{nome}")
    public ResponseEntity<List<Usuario>> buscarPorNome(@PathVariable @Validated String nome) {
        return ResponseEntity.ok().body(service.buscarPorNome(nome));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Validated AutenticacaoRequest dados) {
        var login = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var loginAutenticado = this.autenticacao.authenticate(login);
        var token = tokenService.generateToken((Usuario)loginAutenticado.getPrincipal());
        Optional<Usuario> usuario = usuarioRepository.buscarPorLogin(dados.login());
        String roleUsuario = usuario.get().getStatus().toString();
        return ResponseEntity.ok(new LoginRequestDTO(token, roleUsuario));
    }
    
    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody @Validated UsuarioRequestDTO dados) {
        return ResponseEntity.ok().body(service.cadastrarUsuario(dados));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable @Validated Long id, @RequestBody @Validated UsuarioRequestDTO dados) {    
        return ResponseEntity.ok(service.atualizarUsuario(id, dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> delete(@PathVariable @Validated Long id){
        usuarioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
