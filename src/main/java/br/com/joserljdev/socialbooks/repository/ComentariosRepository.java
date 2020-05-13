package br.com.joserljdev.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joserljdev.socialbooks.domain.Comentario;

public interface ComentariosRepository extends JpaRepository<Comentario, Long> {

}
