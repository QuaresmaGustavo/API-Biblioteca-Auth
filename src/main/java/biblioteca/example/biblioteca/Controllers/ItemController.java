package biblioteca.example.biblioteca.Controllers;

import org.springframework.web.bind.annotation.RestController;

import biblioteca.example.biblioteca.Repository.ItemRepository;
import biblioteca.example.biblioteca.Repository.ItemRequest;
import biblioteca.example.biblioteca.Service.ItemService;
import biblioteca.example.biblioteca.domain.Item;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/item")

public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemService service;

    @GetMapping("/all")
    public ResponseEntity<List<Item>> buscarTodosItens() {
        return ResponseEntity.ok().body(service.buscarTodositens());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Item>> buscarPorID(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.buscarPorID(id));
    }
    
    @PostMapping("/cadastrar")
    public ResponseEntity<Item> cadastrarItem(@RequestBody ItemRequest dados) {
        return ResponseEntity.ok().body(service.cadastrarItem(dados));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Item> atualizarItem(@PathVariable Long id, @RequestBody ItemRequest dados) {
        return ResponseEntity.ok().body(service.atualizarItem(id,dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Item> delete(@PathVariable Long id){
        itemRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
