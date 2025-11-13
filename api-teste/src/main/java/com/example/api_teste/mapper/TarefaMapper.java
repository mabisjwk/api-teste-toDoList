package com.example.api_teste.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.example.api_teste.dto.TarefaDto;
import com.example.api_teste.model.Tarefa;

@Mapper(componentModel = "spring")
public interface TarefaMapper {
    
    TarefaDto toDto(Tarefa tarefa);

    @InheritInverseConfiguration
    Tarefa toEntity(TarefaDto dto);
}
