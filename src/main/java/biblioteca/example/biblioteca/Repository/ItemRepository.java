package biblioteca.example.biblioteca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import biblioteca.example.biblioteca.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {}
