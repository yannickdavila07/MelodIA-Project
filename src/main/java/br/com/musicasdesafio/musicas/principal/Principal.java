package br.com.musicasdesafio.musicas.principal;

import br.com.musicasdesafio.musicas.model.Artista;
import br.com.musicasdesafio.musicas.model.Categoria;
import br.com.musicasdesafio.musicas.model.Musica;
import br.com.musicasdesafio.musicas.repository.ArtistaRepository;
import br.com.musicasdesafio.musicas.repository.MusicaRepository;
import br.com.musicasdesafio.musicas.service.ConsultaIA;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Principal {

    @Autowired
    private MusicaRepository musicaRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    private Scanner leitura = new Scanner(System.in);


    public void exibirMenu(){


        var num = -1;
        while (num != 0){

            System.out.println("""
                    \n1 - Cadrastrar artista
                    2 - Cadrastar música
                    3 - Listar música
                    4 - Listar artista
                    5 - Buscar música por artistas
                    6 - Pesquisar dados sobre um artista
                    
                    
                    
                    0 - Sair
                    """);
            num = leitura.nextInt();
            leitura.nextLine();

            switch (num){
                case 1:
                    cadrastarArtista();
                    break;
                case 2:
                    cadrastrarMusica();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    listarArtistas();
                    break;
                case 5:
                    buscarMusicaPorArtista();
                    break;
                case 6:
                    pesquisarDados();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Valor digitado inválido!");
                    break;
            }


        }


    }




    @Transactional
    private void cadrastarArtista() {
        var opcao = "s";
        while (opcao.equals("s")) {
            System.out.println("Qual o nome do artista: ");
            var nomeArtista = leitura.nextLine();

            System.out.println("Informe o tipo desse artista: (solo, dupla, banda): ");
            var verCategoria = leitura.nextLine().toLowerCase();

            try {
                var categoria = Categoria.valueOf(verCategoria.toUpperCase());
                Artista artista = new Artista(nomeArtista, categoria);
                artistaRepository.save(artista);
            } catch (IllegalArgumentException e) {
                System.out.println("Categoria invalida!");
            }

            System.out.println("Cadastrar outro artista? (S/N)");
            opcao = leitura.nextLine().toLowerCase();

        }


    }

    @Transactional
    private void cadrastrarMusica() {
        listarArtistas();
        System.out.println("\nEscolha um artista pelo nome: ");
        var nomeArtista = leitura.nextLine();


        Artista artista = artistaRepository.verificarArtista(nomeArtista);
        if (artista == null){
            System.out.println("Artista nao encontrado!");


        }else{
            System.out.println("Escreva o nome da musica: ");
            var nomeMusica = leitura.nextLine();

            System.out.println("Digite o nome do Album: ");
            var nomeAlbum = leitura.nextLine();

            Musica musica = new Musica(artista, nomeMusica, nomeAlbum);

            musicaRepository.save(musica);

        }

    }



    private void listarArtistas(){
        System.out.println("Lista dos artistas:\n");
        List<Artista> listaDeArtista = artistaRepository.listarArtistas();
        listaDeArtista.forEach(artista1 -> System.out.printf("Artista: %s - Categoria: %s - Id: %d\n", artista1.getNome(), artista1.getCategoria(), artista1.getId()));

    }

    private void listarMusicas() {
        System.out.println("Lista de musicas:\n");
        List<Musica> musicas = musicaRepository.listaDeMusicas();
        musicas.forEach(musica -> System.out.printf("Musica: %s - Album: %s - Nome do(a) Artista: %s\n", musica.getNome(), musica.getAlbum(), musica.getArtista()));

    }

    private void buscarMusicaPorArtista(){
        listarArtistas();
        System.out.println("\nQual artista voce quer ver as musicas? ");
        var nomeArtista = leitura.nextLine();

        Artista artista = artistaRepository.verificarArtista(nomeArtista);

        if (artista == null){
            System.out.println("Artista nao encontrado!");
        }else{
            List<Musica> musicasBuscadas = musicaRepository.listaDeMusicaPorArtista(nomeArtista);
            musicasBuscadas.forEach(musica -> System.out.printf("Musica: %s - Album: %s - Artista: %s\n", musica.getNome(), musica.getAlbum(), musica.getArtista()));

        }
    }


    @Transactional
    private void pesquisarDados() {
        System.out.println("\nDigite o nome do artista para a pesquisa:");
        var nomeDoArtista = leitura.nextLine();

        Artista artista = artistaRepository.verificarArtista(nomeDoArtista);

        if (artista != null) {

            if (artista.getBiografia() == null || artista.getBiografia().isEmpty()) {
                System.out.println("Buscando na IA (Gasto de tokens)...");

                var biografiaIA = ConsultaIA.obterInformacao(nomeDoArtista);

                artista.setBiografia(biografiaIA);
                artistaRepository.save(artista);
                System.out.println("\n-=-=-=-=-=-=-=-=-=-=-= BIOGRAFIA DO(A) ARTISTA (via AI) -=-=-=-=-=-=-=-=-=-=-=\n");
                System.out.println(biografiaIA);
                System.out.println("\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            } else {
                System.out.println("\n-=-=-=-=-=-=-=-=-=-=-= BIOGRAFIA DO(A) ARTISTA (via AI) -=-=-=-=-=-=-=-=-=-=-=\n");
                System.out.println(artista.getBiografia());
                System.out.println("\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            }
        } else {
            System.out.println("Artista não encontrado! Cadastre-o na opção 1 primeiro.");
        }
    }

}
