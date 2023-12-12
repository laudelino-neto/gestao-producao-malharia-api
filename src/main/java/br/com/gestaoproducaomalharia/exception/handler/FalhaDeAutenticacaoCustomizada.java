package br.com.gestaoproducaomalharia.exception.handler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import br.com.gestaoproducaomalharia.exception.ErroDaApi;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FalhaDeAutenticacaoCustomizada implements AuthenticationEntryPoint{

	@Autowired
	private ErrorConverter errorConverter;
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		JSONObject body = errorConverter.criarJsonDeErro(ErroDaApi.TOKEN_INVALIDO, 
				"O token não está presente na requisição ou está fora da validade");

		response.setStatus(HttpStatus.FORBIDDEN.value());
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.getOutputStream().write(body.toString().getBytes(StandardCharsets.UTF_8));
		
	}

}
