package br.com.gestaoproducaomalharia.controller.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.gestaoproducaomalharia.dto.RelatorioDeEscalas;
import br.com.gestaoproducaomalharia.entity.Escala;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Component
public class EscalaConverter {
	
	@Autowired
	private MapConverter mapConverter;
	
	public Map<String, Object> toMap(RelatorioDeEscalas relatorio){			

		Map<String, Object> relatorioMap = new HashMap<>();

		final int PRIMEIRO_COLABORADOR = 0;
		relatorioMap.put("colaborador", relatorio.getEscalas()
				.get(PRIMEIRO_COLABORADOR).getColaborador());

		relatorioMap.put("ano", relatorio.getAno());

		relatorioMap.put("mes", relatorio.getMes());

		List<Map<String, Object>> escalasConvertidasMap = toListMap(relatorio.getEscalas());

		relatorioMap.put("escalas", escalasConvertidasMap);

		relatorioMap.put("resumo", relatorio.getResumo());

		return relatorioMap;

	}

	public Map<String, Object> toMap(
			@NotNull(message = "A listagem de escalas é obrigatória")
			@NotEmpty(message = "É preciso existir ao menos uma escala na lista")
			List<Escala> escalasGeradas){

		Map<String, Object> escalasDoColaboradorMap = new HashMap<>();

		if (!escalasGeradas.isEmpty()) {

			//Adiciona no map o primeiro colaborador da lista, nesse caso, 
			//as demais escalas serão do mesmo colaborador
			final int PRIMEIRO_COLABORADOR = 0;
			escalasDoColaboradorMap.put("colaborador", escalasGeradas
					.get(PRIMEIRO_COLABORADOR).getColaborador());

			List<Map<String, Object>> escalasConvertidasMap = toListMap(escalasGeradas);

			escalasDoColaboradorMap.put("escalas", escalasConvertidasMap);

		}

		return escalasDoColaboradorMap;

	}
	
	private List<Map<String, Object>> toListMap(List<Escala> escalasGeradas){

		List<Map<String, Object>> escalasConvertidasMap = new ArrayList<>();

		escalasGeradas.forEach(e -> {

			Map<String, Object> escalaMap = new HashMap<>();
			escalaMap.put("id", e.getId());
			escalaMap.put("data", e.getData());
			escalaMap.put("entrada", e.getEntrada());
			escalaMap.put("saida", e.getSaida());
			escalaMap.put("realizada", e.getRealizada());
			escalaMap.put("justificada", e.getJustificada());

			if (e.isJaJustificada()) {
				escalaMap.put("justicativa", e.getJustificativa());
			}
			
			if (e.isAcertoRealizado()) {
				//Desvincula o colaborador do acerto da escala
				//e.getAcerto().setColaborador(null);
				escalaMap.put("acerto", mapConverter.toJsonMap(e.getAcerto(), "colaborador"));
			}

			escalasConvertidasMap.add(escalaMap);

		});

		return escalasConvertidasMap;

	}
	
}
