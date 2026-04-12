package br.com.musicasdesafio.musicas.repository;

import br.com.musicasdesafio.musicas.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MusicaRepository extends JpaRepository<Musica, Long> {


    @Query(value = "SELECT m FROM Musica m JOIN FETCH m.artista")
    List<Musica> listaDeMusicas();

    @Query(value = "SELECT m FROM Musica m WHERE m.artista.nome ILIKE %:nomeDoArtista%")
    List<Musica> listaDeMusicaPorArtista(String nomeDoArtista);

}
