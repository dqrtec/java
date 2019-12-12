package br.arida.cadim.repositories;

import org.springframework.data.repository.CrudRepository;

import br.arida.cadim.model.Ecg;

public interface EcgRepository extends CrudRepository<Ecg, Integer> {

}
