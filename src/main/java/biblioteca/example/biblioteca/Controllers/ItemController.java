package biblioteca.example.biblioteca.Controllers;

import org.springframework.web.bind.annotation.RestController;

import biblioteca.example.biblioteca.Repository.ItemRepository;
import biblioteca.example.biblioteca.Service.ItemService;
import biblioteca.example.biblioteca.domain.ResponseModel;
import biblioteca.example.biblioteca.domain.Item.Item;
import biblioteca.example.biblioteca.domain.Item.ItemRequestDTO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/item")
@CrossOrigin(origins = "http://localhost:8080")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemService service;

    @GetMapping("/todos")
    public ResponseEntity<ResponseModel<Item>> buscarTodosItens(@RequestParam Integer pagina, @RequestParam Integer quantidade) {
        return ResponseEntity.ok().body(service.buscarTodositens(pagina, quantidade));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Item>> buscarPorID(@PathVariable @Validated Long id) {
        return ResponseEntity.ok().body(service.buscarPorID(id));
    }

    @GetMapping("nome/{nome}")
    public ResponseEntity<List<Item>> buscarPorNome(@PathVariable @Validated String nome) {
        return ResponseEntity.ok().body(service.buscarPorNome(nome));
    }

    @GetMapping("categoria/{categoria}")
    public ResponseEntity<List<Item>> buscarPorCategoria(@PathVariable @Validated String categoria) {
        return ResponseEntity.ok().body(service.buscarPorCategoria(categoria));
    }

    @GetMapping("editora/{editora}")
    public ResponseEntity<List<Item>> buscarPorEditora(@PathVariable @Validated String editora) {
        return ResponseEntity.ok().body(service.buscarPorEditora(editora));
    }
    
    @PostMapping("/cadastrar")
    public ResponseEntity<Item> cadastrarItem(@RequestBody @Validated ItemRequestDTO dados) {
        return ResponseEntity.ok().body(service.cadastrarItem(dados));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Item> atualizarItem(@PathVariable @Validated Long id, @RequestBody @Validated ItemRequestDTO dados) {
        return ResponseEntity.ok().body(service.atualizarItem(id,dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Item> delete(@PathVariable @Validated Long id){
        itemRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
