package br.com.musicasdesafio.musicas.model;

import jakarta.persistence.*;


@Entity
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String album;

    @ManyToOne
    private Artista artista;



    //CONSTRUCTORS:


    public Musica(Artista artista, String nome, String album) {
        this.artista = artista;
        this.album = album;
        this.nome = nome;
    }

    public Musica(){}



    //GETTERS:

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getAlbum() {
        return album;
    }

    public Artista getArtista() {
        return artista;
    }




}
