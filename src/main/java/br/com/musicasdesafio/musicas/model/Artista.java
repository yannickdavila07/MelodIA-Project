package br.com.musicasdesafio.musicas.model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    @Column(columnDefinition = "TEXT")
    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    private String biografia;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musica> musica;


    //CONSTRUCTORS:

    public Artista(String nome, Categoria categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }

    public Artista(){}


    //GETTERS:

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public List<Musica> getMusica() {
        return musica;
    }

    public String getBiografia() {
        return biografia;
    }

    //SETTERS:


    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    @Override
    public String toString() {
        return nome;
    }
}
