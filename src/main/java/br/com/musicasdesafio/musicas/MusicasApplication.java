package br.com.musicasdesafio.musicas;

import br.com.musicasdesafio.musicas.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MusicasApplication implements CommandLineRunner {
	private final Principal main;

	public MusicasApplication(Principal main){
		this.main = main;
	}


	public static void main(String[] args) {
		SpringApplication.run(MusicasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		main.exibirMenu();
	}
}
