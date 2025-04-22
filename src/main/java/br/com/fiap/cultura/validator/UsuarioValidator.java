package br.com.fiap.cultura.validator;

import br.com.fiap.cultura.dto.UsuarioCadastroDTO;
import br.com.fiap.cultura.exception.ValidacaoException;

public class UsuarioValidator {
    public static void valid(UsuarioCadastroDTO usuarioDTO){
        if(usuarioDTO.nome() == null || usuarioDTO.nome().isEmpty())
            throw new ValidacaoException("Nome do usuario vázio");

        if(usuarioDTO.role() == null || usuarioDTO.role().isEmpty())
            throw new ValidacaoException("Role do usuario está vázio");

        if(!usuarioDTO.role().equals("ADMIN") && !usuarioDTO.role().equals("USER"))
            throw new ValidacaoException("Role do usuário inválida");

        if(usuarioDTO.senha() == null || usuarioDTO.senha().isEmpty())
            throw new ValidacaoException("Senha do usuário está vazia");

        if(usuarioDTO.email() == null || usuarioDTO.email().isEmpty())
            throw new ValidacaoException("Email do usuário vázio");

    }
}
