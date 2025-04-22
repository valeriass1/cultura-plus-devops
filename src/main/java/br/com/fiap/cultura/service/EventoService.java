package br.com.fiap.cultura.service;

import br.com.fiap.cultura.validator.EventoValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.fiap.cultura.dto.EventoCadastroDTO;
import br.com.fiap.cultura.dto.EventoExibicaoDTO;
import br.com.fiap.cultura.model.Evento;
import br.com.fiap.cultura.repository.EventoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public EventoExibicaoDTO salvarEvento(EventoCadastroDTO eventoDTO){

        EventoValidator.valid(eventoDTO);

        Evento evento = new Evento();
        BeanUtils.copyProperties(eventoDTO, evento);

        Evento eventoSalvo = eventoRepository.save(evento);
        return new EventoExibicaoDTO(eventoSalvo);
    }

    public EventoExibicaoDTO buscarPorId(Long id) {
        Optional<Evento> eventoOptional = eventoRepository.findById(id);

        if (eventoOptional.isPresent()) {
            return new EventoExibicaoDTO(eventoOptional.get());
        } else {
            throw new RuntimeException("Evento não existe!");
        }
    }

    public Page<EventoExibicaoDTO> listarTodos(Pageable paginacao){
        return eventoRepository
                .findAll(paginacao)
                .map(EventoExibicaoDTO::new);
    }

    public void excluir(Long id) {
        Optional<Evento> eventoOptional = eventoRepository.findById(id);

        if (eventoOptional.isPresent()) {
            eventoRepository.delete(eventoOptional.get());
        } else {
            throw new RuntimeException("Evento não encontrado!");
        }
    }

    public EventoExibicaoDTO atualizar(EventoCadastroDTO eventoDTO) {
        EventoValidator.valid(eventoDTO);

        Optional<Evento> eventoOptional = eventoRepository.findById(eventoDTO.eventoId());

        if (eventoOptional.isPresent()) {
            var evento = eventoOptional.get();
            evento.setNome(eventoDTO.nome());
            evento.setData(eventoDTO.data());
            evento.setLocal(eventoDTO.local());
            evento.setDescricao(eventoDTO.descricao());
            evento.setQuantidadeLimite(eventoDTO.quantidadeLimite());

            eventoRepository.save(evento);
            return new EventoExibicaoDTO(evento);
        } else {
            throw new RuntimeException("Evento não encontrado!");
        }
    }

    public EventoExibicaoDTO buscarPorNome(String nome){
        Optional<Evento> eventoOptional =
                eventoRepository.buscarPorNome(nome);

        if (eventoOptional.isPresent()){
            return new EventoExibicaoDTO(eventoOptional.get());
        } else {
            throw new RuntimeException("Evento não encontrado!");
        }
    }
}
