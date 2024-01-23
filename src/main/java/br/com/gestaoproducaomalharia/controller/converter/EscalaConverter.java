package br.com.gestaoproducaomalharia.controller.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.gestaoproducaomalharia.entity.Escala;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Component
public class EscalaConverter {

	public Map<String, Object> toMap(
			@NotNull(message = "A listagem de escalas é obrigatória")
			@NotEmpty(message = "É preciso existir ao menos uma escala na lista")
			List<Escala> escalasGeradas){
		
		Map<String, Object> escalasDoColaboradorMap = new HashMap<>();

		//Adiciona no map o primeiro colaborador da lista, nesse caso, 
		//as demais escalas serão do mesmo colaborador
		final int PRIMEIRO_COLABORADOR = 0;
		escalasDoColaboradorMap.put("colaborador", escalasGeradas
				.get(PRIMEIRO_COLABORADOR).getColaborador());
		
		List<Map<String, Object>> escalasConvertidasMap = new ArrayList<>();
		escalasGeradas.forEach(e -> {
			Map<String, Object> escalaMap = new HashMap<>();
			escalaMap.put("data", e.getData());
			escalaMap.put("entrada", e.getEntrada());
			escalaMap.put("saida", e.getSaida());
			escalaMap.put("realizada", e.getRealizada());
			escalasConvertidasMap.add(escalaMap);
		});
		
		escalasDoColaboradorMap.put("escalas", escalasConvertidasMap);
		
		return escalasDoColaboradorMap;

	}
	
}
