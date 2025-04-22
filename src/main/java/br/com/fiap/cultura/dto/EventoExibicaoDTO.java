package br.com.fiap.cultura.dto;

import br.com.fiap.cultura.model.Evento;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Date;

public record EventoExibicaoDTO(
        Long eventoId,
        String nome,

        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime data,
        String local,
        String descricao,
        Integer quantidadeLimite) {

    public EventoExibicaoDTO(Evento evento) {
        this(
                evento.getEventoId(),
                evento.getNome(),
                evento.getData(),
                evento.getLocal(),
                evento.getDescricao(),
                evento.getQuantidadeLimite());
    }
}
