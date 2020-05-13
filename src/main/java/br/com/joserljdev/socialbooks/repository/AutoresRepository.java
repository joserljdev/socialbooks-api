package br.com.joserljdev.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joserljdev.socialbooks.domain.Autor;

public interface AutoresRepository extends JpaRepository<Autor, Long> {

}
