package br.com.fiap.cultura.repository;

import br.com.fiap.cultura.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    @Query("SELECT a FROM Evento a WHERE a.nome = :nome")
    Optional<Evento> buscarPorNome(@Param("nome") String nome);
}
