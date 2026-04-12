package br.com.musicasdesafio.musicas.repository;

import br.com.musicasdesafio.musicas.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    @Query(value = "SELECT a FROM Artista a")
    List<Artista> listarArtistas();

    @Query(value = "SELECT a FROM Artista a WHERE a.nome ILIKE %:nomeArtista%")
    Artista verificarArtista(String nomeArtista);



}
