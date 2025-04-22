package br.com.fiap.cultura.dto;

public record UsuarioCadastroDTO(
        Long usuarioId,
        String nome,
        String email,
        String senha,
        String role
) {
}
