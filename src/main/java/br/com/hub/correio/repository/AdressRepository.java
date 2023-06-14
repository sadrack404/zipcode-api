package br.com.hub.correio.repository;

import br.com.hub.correio.model.Adress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdressRepository extends JpaRepository<Adress, String> {

}