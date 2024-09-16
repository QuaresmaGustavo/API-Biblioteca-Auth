package biblioteca.example.biblioteca.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import biblioteca.example.biblioteca.Repository.MinhaListaRepository;
import biblioteca.example.biblioteca.domain.Item.Item;
import biblioteca.example.biblioteca.domain.MinhaLista.MinhaLista;
import biblioteca.example.biblioteca.domain.MinhaLista.MinhaListaRequestDTO;
import jakarta.persistence.EntityNotFoundException;

@Service
public class MinhaListaService {
    @Autowired
    private MinhaListaRepository repository;

    @Autowired
    private ItemService serviceitem;

    public List<MinhaLista> buscarItensPorUsuario(Long usuarioId) {
        return repository.findByIdUsuarioID(usuarioId);
    }

    public MinhaLista salvarItem(MinhaListaRequestDTO dados){
        Optional<Item> buscarItem = serviceitem.buscarPorID(dados.itemId());
        if (buscarItem.isEmpty()) {
            throw new EntityNotFoundException("Item não encontrado");
        }

        Item item = buscarItem.get();

        MinhaLista novoItem = new MinhaLista(dados, item);
        repository.save(novoItem);
        return novoItem;
    }
}
