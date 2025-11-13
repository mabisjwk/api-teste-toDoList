package com.example.api_teste.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record TarefaDto(
    Long id,
    @NotBlank
    String nome,
    @NotBlank
    String descricao,
    @NotBlank
    @Pattern(
        regexp = "^\\d{2}/\\d{2}/\\d{4}$",
        message = "deve seguir o formato dd/mm/aaaa"
    )
    @NotBlank
    String diaConclusao
) {
    
}
