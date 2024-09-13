package biblioteca.example.biblioteca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import biblioteca.example.biblioteca.domain.MinhaLista.MinhaLista;
import biblioteca.example.biblioteca.domain.MinhaLista.MinhaListaID;

public interface MinhaListaRepository extends JpaRepository<MinhaLista, MinhaListaID> {}
