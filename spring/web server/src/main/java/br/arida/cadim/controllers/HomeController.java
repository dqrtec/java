package br.arida.cadim.controllers;

import java.sql.Date;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.arida.cadim.model.User;
import br.arida.cadim.repositories.UserRepository;

@Controller
public class HomeController {

	@GetMapping("/")
	public String index() {
		return "home/index";
	}

	@Autowired
	UserRepository userRepository;

	@PostMapping("/add/user")
	public String adicionarPaciente(
			@RequestParam(value="cpf") Integer cpf,
			@RequestParam(value="nome") String nome,
			@RequestParam(value="email") String email,
			@RequestParam(value="senha") String senha,
			@RequestParam(value="genero") String genero,
			@RequestParam(value="altura") Integer altura,
			@RequestParam(value="peso") Integer peso,
			@RequestParam(value="telefone") String telefone) {

		User u = new User();
		u.setCpf(cpf);
		u.setNome(nome);
		u.setEmail(email);
		u.setSenha(senha);
		u.setGenero(genero);
		u.setAltura(altura);
		u.setPeso(peso);
		u.setTelefone(telefone);
		userRepository.save(u);
		
		
		System.out.println("CHAMOU!");
		return "home/index";
	}
	
	@PostMapping("/envio")
	public String salvar(@RequestParam(value="title", defaultValue="World") String titulo) {
		return "(!!--!!)";
	}

}
