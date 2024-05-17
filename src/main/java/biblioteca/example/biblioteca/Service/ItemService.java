package biblioteca.example.biblioteca.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import biblioteca.example.biblioteca.Repository.ItemRepository;
import biblioteca.example.biblioteca.Repository.ItemRequest;
import biblioteca.example.biblioteca.domain.Item;

public class ItemService {

    @Autowired
    private ItemRepository repository;

    public List<Item> buscarTodositens() {
        return repository.findAll();
    }

    public Optional<Item> buscarPorID(Long id) {
        return repository.findById(id);
    }

    public Item cadastrarItem(ItemRequest dados) {
        Item novoItem = new Item(dados);
        repository.save(novoItem);
        return novoItem;        
    }

    public Item atualizarItem(Long id, ItemRequest dados) {
        if (buscarPorID(id).isPresent()) {
            Item itemAtualizado = buscarPorID(id).get();

            itemAtualizado.setNome(dados.nome());
            itemAtualizado.setTipo(dados.tipo());
            itemAtualizado.setEditora(dados.editora());
            itemAtualizado.setNumPagina(dados.numPagina());
            itemAtualizado.setAno(dados.ano());
            return repository.save(itemAtualizado);
        }
        else {
            throw new ResolutionException("Erro ao atualizar dados do usuario!");
        } 
    }
}
