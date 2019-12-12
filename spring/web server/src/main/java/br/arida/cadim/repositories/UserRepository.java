package br.arida.cadim.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.arida.cadim.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	
	//List<User> findByTitle(String title);
}
