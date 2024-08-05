package biblioteca.example.biblioteca.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import biblioteca.example.biblioteca.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByNome(String item);
    List<Item> findByTipo(String categoria);
    List<Item> findByEditora(String editora);
}
