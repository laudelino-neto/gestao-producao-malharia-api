package br.com.gestaoproducaomalharia.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.gestaoproducaomalharia.service.impl.CredencialDeAcessoServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class GerenciadorDeTokenJwt {

	@Value("${spring.jwt.secret}")
    private String secret;

    @Value("${spring.jwt.ttl-in-millis}")
    private int ttlInMillis;
    
    @Autowired
    private CredencialDeAcessoServiceImpl service;
    
    private Key getChaveDeAssinatura(){
        byte[] keyByte = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyByte);
    }

    public String gerarTokenPor(String login) {
    	
    	UserDetails usuario = service.loadUserByUsername(login);
    	
    	Map<String, Object> claims = new HashMap<String, Object>();
    	claims.put("papel", usuario.getAuthorities().toArray()[0].toString());
    	
    	return Jwts.builder()
                .setClaims(claims)
                .setSubject(login)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ttlInMillis))
                .signWith(getChaveDeAssinatura(), SignatureAlgorithm.HS256).compact();
    }
    
    private Claims extrairDetalhesDo(String tokenGerado) {
    	return Jwts.parserBuilder()
    			.setSigningKey(getChaveDeAssinatura())
    			.build()
    			.parseClaimsJws(tokenGerado)
    			.getBody();
    }
    
    public String extrairLoginDo(String tokenGerado) {
    	Claims detalhes = extrairDetalhesDo(tokenGerado);
    	return detalhes.getSubject();
    }       
    
    public Date extrairValidadeDo(String tokenGerado) {
    	Claims detalhes = extrairDetalhesDo(tokenGerado);
    	return detalhes.getExpiration();
    }
    
    private boolean isVencido(String tokenGerado) {
    	Date validade = extrairValidadeDo(tokenGerado);
    	return validade.before(new Date());
    }
    
    public boolean isValido(String tokenGerado, UserDetails credencial) {
    	String login = extrairLoginDo(tokenGerado);
    	boolean isLoginValido = login.equals(credencial.getUsername());
    	boolean isDentroDaValidade = !isVencido(tokenGerado); 
    	return isLoginValido && isDentroDaValidade;
    }
	
}
