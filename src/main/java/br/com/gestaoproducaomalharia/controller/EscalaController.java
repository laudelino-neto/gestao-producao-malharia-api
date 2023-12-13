package br.com.gestaoproducaomalharia.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/escalas")
public class EscalaController {

	@GetMapping("/echo")
	public ResponseEntity<?> echo(){
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("msg", "Funcionado");
		return ResponseEntity.ok(response);
	}
	
}
