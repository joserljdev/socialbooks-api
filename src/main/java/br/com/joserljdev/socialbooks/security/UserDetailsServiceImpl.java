package br.com.joserljdev.socialbooks.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.joserljdev.socialbooks.domain.User;
import br.com.joserljdev.socialbooks.repository.UsuarioRepository;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = usuarioRepository.findByLogin(username);

		if (user == null) {
			throw new UsernameNotFoundException("Usuário não encontrado!");
		}

		return user;
	}
}
