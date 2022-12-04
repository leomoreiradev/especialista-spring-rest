package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncotradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.salvar(cozinha);
    }

    public void excluir(Long cozinhaId) {
        try {
            cozinhaRepository.remover(cozinhaId);

        }
        catch(EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncotradaException(
                    String.format("Não existe um cadastro de cozinha com codigo %d", cozinhaId)
            );
        }
        catch (DataIntegrityViolationException e) {
            //Aqui não pode excluir porq a cozinha esta associada a um restaurante
           throw new EntidadeEmUsoException(
                   String.format("Cozinha de codigo %d não pode ser removida pois está em uso", cozinhaId)
           );
        }

    }
}
