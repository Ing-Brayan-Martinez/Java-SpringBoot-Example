package com.example.security;

import com.example.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class JwtTokenProvider {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private static final String HEADER = "Authorization";
	private static final String PREFIX = "Bearer ";

	@Value("${west.jwt.secrettoken:SecretTokenWest}")
	private String secret_token = "";

	@Value("${west.jwt.secrettoken:600000}")
	private Long expiration_time = 600_000L;

	public String getToken(User user) {

		final String token = Jwts.builder()
				.setId("jdi" + user.getUserId())
				.setSubject(user.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiration_time))
				.signWith(SignatureAlgorithm.HS512, secret_token.getBytes())
				.compact();

		log.debug(String.format("Creating token: %s { %s }", user.getUsername(), token));

		return PREFIX + token;
	}

	public Claims validateToken(HttpServletRequest request) {
		final String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
		return Jwts.parser().setSigningKey(secret_token.getBytes()).parseClaimsJws(jwtToken).getBody();
	}

	public boolean existsToken(HttpServletRequest request, HttpServletResponse res) {
		final String authenticationHeader = request.getHeader(HEADER);
		return (authenticationHeader != null && authenticationHeader.startsWith(PREFIX));
	}
}
