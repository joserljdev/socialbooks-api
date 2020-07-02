package br.com.joserljdev.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joserljdev.socialbooks.domain.User;

public interface UsuarioRepository extends JpaRepository<User, Long> {
	User findByLogin(String login);
}
