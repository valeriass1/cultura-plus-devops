package br.com.fiap.cultura.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EventoCadastroDTO(
        Long eventoId,
        String nome,

        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime data,
        String local,
        String descricao,
        Integer quantidadeLimite
) {
}
