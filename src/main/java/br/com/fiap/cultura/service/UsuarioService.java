package br.com.fiap.cultura.service;

import br.com.fiap.cultura.dto.UsuarioCadastroDTO;
import br.com.fiap.cultura.dto.UsuarioExibicaoDTO;
import br.com.fiap.cultura.model.Usuario;
import br.com.fiap.cultura.model.UsuarioRole;
import br.com.fiap.cultura.repository.UsuarioRepository;
import br.com.fiap.cultura.validator.UsuarioValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioExibicaoDTO salvarUsuario(UsuarioCadastroDTO usuarioDTO){
        UsuarioValidator.valid(usuarioDTO);

        String senhaCriptografada = new
                BCryptPasswordEncoder().encode(usuarioDTO.senha());

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        usuario.setSenha(senhaCriptografada);

        usuario.setRole(UsuarioRole.valueOf(usuarioDTO.role()));

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioExibicaoDTO(usuarioSalvo);
    }

    public UsuarioExibicaoDTO listarPorId(Long id){
        Optional<Usuario> usuarioOptional =
                usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()){
            return new UsuarioExibicaoDTO(usuarioOptional.get());
        } else {
            throw new RuntimeException("Usuário não existe!");
        }
    }

    public Page<UsuarioExibicaoDTO> listarTodos(Pageable paginacao){
        return usuarioRepository
                .findAll(paginacao)
                .map(UsuarioExibicaoDTO::new);
    }

    public void excluir(Long id){
        Optional<Usuario> usuarioOptional =
                usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()){
            usuarioRepository.delete(usuarioOptional.get());
        } else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }

    public UsuarioExibicaoDTO atualizar(UsuarioCadastroDTO usuarioDTO){
        UsuarioValidator.valid(usuarioDTO);

        Optional<Usuario> usuarioOptional =
                usuarioRepository.findById(usuarioDTO.usuarioId());

        if (usuarioOptional.isPresent()){
            var usuario = usuarioOptional.get();
            usuario.setEmail(usuarioDTO.email());
            usuario.setNome(usuarioDTO.nome());
            usuario.setSenha(usuarioDTO.senha());

            usuarioRepository.save(usuario);
            return new UsuarioExibicaoDTO(usuario);
        } else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }
}

