package br.com.fiap.cultura.controller;

import br.com.fiap.cultura.dto.EventoCadastroDTO;
import br.com.fiap.cultura.dto.EventoExibicaoDTO;
import br.com.fiap.cultura.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;



@RestController
@RequestMapping("/api")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping("/evento")
    @ResponseStatus(HttpStatus.CREATED)
    public EventoExibicaoDTO salvar(
            @RequestBody EventoCadastroDTO evento){
        return eventoService.salvarEvento(evento);
    }

    @GetMapping("/evento")
    @ResponseStatus(HttpStatus.OK)
    public Page<EventoExibicaoDTO> ListarTodos(
            @PageableDefault(size = 2, page = 0) Pageable paginacao
    ){
        return eventoService.listarTodos(paginacao);
    }

    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<EventoExibicaoDTO> buscarPorId(
            @PathVariable Long eventoId) {
        try {
            return ResponseEntity
                    .ok(eventoService.buscarPorId(eventoId));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/evento/{eventoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long eventoId){
        eventoService.excluir(eventoId);
    }

    @PutMapping("/evento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> atualizar(
            @RequestBody EventoCadastroDTO eventoDTO){
        try {
           if (eventoDTO.eventoId() == null) {
            return ResponseEntity.badRequest().build();
           }
            eventoService.atualizar(eventoDTO);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/evento", params = "nome")
    public ResponseEntity<EventoExibicaoDTO> buscarPorNome(
            @RequestParam String nome){
        try {
            return ResponseEntity
                    .ok(eventoService.buscarPorNome(nome));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
