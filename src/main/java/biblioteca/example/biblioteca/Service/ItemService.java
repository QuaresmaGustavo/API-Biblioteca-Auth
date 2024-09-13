package biblioteca.example.biblioteca.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import biblioteca.example.biblioteca.Repository.ItemRepository;
import biblioteca.example.biblioteca.domain.Item.Item;
import biblioteca.example.biblioteca.domain.Item.ItemRequestDTO;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    public Page<Item> buscarTodositens(Integer pagina, Integer quantidade) {
        return repository.findAll(PageRequest.of(pagina, quantidade));
    }

    public Optional<Item> buscarPorID(Long id) {
        return repository.findById(id);
    }

    public List<Item> buscarPorNome(String item) {
        return repository.findByNome(item);
    }

    public List<Item> buscarPorCategoria(String categoria) {
        return repository.findByTipo(categoria);
    }

    public List<Item> buscarPorEditora(String editora) {
        return repository.findByEditora(editora);
    }

    public Item cadastrarItem(ItemRequestDTO dados) {
        Item novoItem = new Item(dados);
        repository.save(novoItem);
        return novoItem;        
    }

    public Item atualizarItem(Long id, ItemRequestDTO dados) {
        if (buscarPorID(id).isPresent()) {
            Item itemAtualizado = buscarPorID(id).get();

            itemAtualizado.setNome(dados.nome());
            itemAtualizado.setTipo(dados.tipo());
            itemAtualizado.setEditora(dados.editora());
            itemAtualizado.setDescricao(dados.descricao());
            itemAtualizado.setImagem(dados.imagem());
            itemAtualizado.setNumPagina(dados.numPagina());
            itemAtualizado.setPublicacao(dados.publicacao());
            return repository.save(itemAtualizado);
        }
        else {
            throw new ResolutionException("Erro ao atualizar dados do usuario!");
        } 
    }
}
