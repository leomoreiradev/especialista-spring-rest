package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	
	@GetMapping
	public List<Restaurante> listar() {
		return restauranteRepository.listar();
	}
	
	@GetMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {
		Restaurante restaurante = restauranteRepository.buscar(restauranteId);
		
		if (restaurante != null) {
			return ResponseEntity.ok(restaurante);
		}
		
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
		try {
			restaurante = cadastroRestauranteService.salvar(restaurante);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping(value = "/{restauranteId}")
	public ResponseEntity<?> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {
		try{
			Restaurante restauranteAtual = restauranteRepository.buscar(restauranteId);
			if(restauranteAtual != null) {
				restauranteAtual.setNome(restaurante.getNome());
				restauranteAtual.setTaxaFrete(restaurante.getTaxaFrete());
				restauranteAtual.getCozinha().setId(restaurante.getCozinha().getId());
				restauranteAtual  = cadastroRestauranteService.salvar(restauranteAtual);
				return ResponseEntity.ok(restauranteAtual);
			}

			return ResponseEntity.notFound().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PatchMapping(value = "/{restauranteId}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
											  @RequestBody Map<String, Object> campos) {
		Restaurante restauranteAtual = restauranteRepository.buscar(restauranteId);
		if (restauranteAtual == null) {
			return ResponseEntity.notFound().build();
		}

			merge(campos, restauranteAtual);

		return atualizar(restauranteId, restauranteAtual);
	}

	private static void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

		System.out.println(restauranteOrigem);

		//para cada par key:value faça:
		// 1 - pegue o campo key(nome da propriedade correspondente) dentro do Restaurante.class
		// 2 - pega o valor da prorpriedade representado pelo field dentro da instancia restauranteOrigem
		// 3 - set o novoValor em cada field(chave de cada propriedade) de restauranteDestino
		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			//1
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			field.setAccessible(true); // permite que a variavel que esta private se torne acessivel

			//3
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

			System.out.println(nomePropriedade + "=" + valorPropriedade + "=" + novoValor);

			//2
			ReflectionUtils.setField(field, restauranteDestino, novoValor);

		});

	}
}
