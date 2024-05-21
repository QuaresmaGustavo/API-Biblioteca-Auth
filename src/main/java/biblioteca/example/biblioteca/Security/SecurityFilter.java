package biblioteca.example.biblioteca.Security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import biblioteca.example.biblioteca.Repository.UsuarioRepository;
import biblioteca.example.biblioteca.Service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{

    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                String token = this.recoverToken(request);
                if (token != null) {
                    var login = tokenService.validacaoToken(token);
                    UserDetails loginUsuario = repository.findByLogin(login);
                    var autenticacao = new UsernamePasswordAuthenticationToken(loginUsuario, null, loginUsuario.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(autenticacao);
                }
                filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var autenticarCabecalho = request.getHeader("Authorization");
        if(autenticarCabecalho == null) return null;
        return autenticarCabecalho.replace("Bearer ", "");
    }
}
