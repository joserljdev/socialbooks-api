package br.com.joserljdev.socialbooks.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.joserljdev.socialbooks.domain.User;
import br.com.joserljdev.socialbooks.domain.dto.UserDTO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	public static final String AUTH_URL = "/login";

	private final AuthenticationManager authenticationManager;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;

		setFilterProcessesUrl(AUTH_URL);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

		try {
			JwtLoginInput login = new ObjectMapper().readValue(request.getInputStream(), JwtLoginInput.class);
			String username = login.getUsername();
			String password = login.getPassword();

			if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
				throw new BadCredentialsException("Usuário/Senha inválidos.");
			}

			Authentication auth = new UsernamePasswordAuthenticationToken(username, password);

			return authenticationManager.authenticate(auth);
		} catch (IOException e) {
			throw new BadCredentialsException(e.getMessage());
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain, Authentication authentication) throws IOException {
		User user = (User) authentication.getPrincipal();

		String jwtToken = JwtUtil.createToken(user);

		String json = UserDTO.create(user, jwtToken).toJson();
		ServletUtil.write(response, HttpStatus.OK, json);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException error) throws IOException, ServletException {

		String json = ServletUtil.getJson("error", "Login incorreto.");
		ServletUtil.write(response, HttpStatus.UNAUTHORIZED, json);
	}
}