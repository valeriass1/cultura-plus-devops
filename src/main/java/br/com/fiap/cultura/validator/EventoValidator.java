package br.com.fiap.cultura.validator;

import br.com.fiap.cultura.dto.EventoCadastroDTO;
import br.com.fiap.cultura.exception.ValidacaoException;

public class EventoValidator {
    public static void valid(EventoCadastroDTO eventoCadastroDTO){
        if(eventoCadastroDTO.nome() == null || eventoCadastroDTO.nome().isEmpty())
            throw new ValidacaoException("Nome do evento vázio");

        if(eventoCadastroDTO.data() == null)
            throw new ValidacaoException("Data do evento vázio");

        if(eventoCadastroDTO.descricao() == null || eventoCadastroDTO.descricao().isEmpty())
            throw new ValidacaoException("Descrição do evento vázio");

        if(eventoCadastroDTO.local() == null || eventoCadastroDTO.local().isEmpty())
            throw new ValidacaoException("Local do evento vázio");

        if(eventoCadastroDTO.quantidadeLimite() == null || eventoCadastroDTO.quantidadeLimite() <= 0)
            throw new ValidacaoException("Quantidade de limite vazia ou inválida");

    }
}
