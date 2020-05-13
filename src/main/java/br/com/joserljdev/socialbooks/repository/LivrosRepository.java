package br.com.joserljdev.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joserljdev.socialbooks.domain.Livro;

public interface LivrosRepository extends JpaRepository<Livro, Long>{

}
