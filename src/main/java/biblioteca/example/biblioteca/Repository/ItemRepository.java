package biblioteca.example.biblioteca.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import biblioteca.example.biblioteca.domain.Item.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findAll(Pageable pageable);
    List<Item> findByNome(String item);
    List<Item> findByTipo(String categoria);
    List<Item> findByEditora(String editora);
}
