package biblioteca.example.biblioteca.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import biblioteca.example.biblioteca.Repository.UsuarioRepository;
import biblioteca.example.biblioteca.Service.TokenService;
import biblioteca.example.biblioteca.Service.UsuarioService;
import biblioteca.example.biblioteca.domain.AutenticacaoRequest;
import biblioteca.example.biblioteca.domain.LoginRequest;
import biblioteca.example.biblioteca.domain.Usuario;
import biblioteca.example.biblioteca.domain.UsuarioRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<Optional<Usuario>> buscarUsuario(@PathVariable @Validated Long id) {
        return ResponseEntity.ok(service.buscarUsuario(id));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Validated AutenticacaoRequest dados) {
        var login = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var loginAutenticado = this.autenticacao.authenticate(login);
        var token = tokenService.generateToken((Usuario)loginAutenticado.getPrincipal());
        return ResponseEntity.ok(new LoginRequest(token));
    }
    
    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody @Validated UsuarioRequest dados) {
        return ResponseEntity.ok().body(service.cadastrarUsuario(dados));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable @Validated Long id, @RequestBody @Validated UsuarioRequest dados) {    
        return ResponseEntity.ok(service.atualizarUsuario(id, dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> delete(@PathVariable @Validated Long id){
        usuarioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
