package br.com.fiap.cultura.controller;

import br.com.fiap.cultura.dto.UsuarioCadastroDTO;
import br.com.fiap.cultura.dto.UsuarioExibicaoDTO;
import br.com.fiap.cultura.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDTO salvar(@RequestBody UsuarioCadastroDTO usuario){
        return usuarioService.salvarUsuario(usuario);
    }

    @GetMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public Page<UsuarioExibicaoDTO> listarTodos(
            @PageableDefault(size = 2, page = 0)Pageable paginacao
    ){
        return usuarioService.listarTodos(paginacao);
    }

    @GetMapping("/usuarios/{usuarioId}")
    public ResponseEntity<UsuarioExibicaoDTO> buscarPorId(@PathVariable Long usuarioId){
        try {
            return ResponseEntity.ok(usuarioService.listarPorId(usuarioId));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/usuarios/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
        public void excluir(@PathVariable Long usuarioId){
        usuarioService.excluir(usuarioId);
    }

    @PutMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
        public UsuarioExibicaoDTO atualizar(@RequestBody UsuarioCadastroDTO usuarioDTO){
        return usuarioService.atualizar(usuarioDTO);
    }
}
