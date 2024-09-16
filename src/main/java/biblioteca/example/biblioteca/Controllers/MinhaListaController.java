package biblioteca.example.biblioteca.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import biblioteca.example.biblioteca.Repository.MinhaListaRepository;
import biblioteca.example.biblioteca.Service.MinhaListaService;
import biblioteca.example.biblioteca.domain.MinhaLista.MinhaLista;
import biblioteca.example.biblioteca.domain.MinhaLista.MinhaListaID;
import biblioteca.example.biblioteca.domain.MinhaLista.MinhaListaRequestDTO;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/minhaLista")
public class MinhaListaController {
    @Autowired
    private MinhaListaService service;

    @Autowired
    private MinhaListaRepository repository;

    @GetMapping("/buscarLista/{id}")
    public ResponseEntity<List<MinhaLista>> buscarItensPorUsuario(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(service.buscarItensPorUsuario(id));
    }
    
    @PostMapping("/salvar")
    public ResponseEntity<MinhaLista> salvarItem(@RequestBody MinhaListaRequestDTO dados) {
        return ResponseEntity.ok().body(service.salvarItem(dados));
    }
    
    @DeleteMapping("/{usuarioId}/{itemId}")
    public ResponseEntity<MinhaLista> delete(@PathVariable("usuarioId") Long usuarioId, @PathVariable("itemId") Long itemId){
        MinhaListaID id = new MinhaListaID(usuarioId, itemId);
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
