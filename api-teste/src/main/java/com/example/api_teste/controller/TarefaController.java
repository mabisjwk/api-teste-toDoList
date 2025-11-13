package com.example.api_teste.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_teste.dto.TarefaDto;
import com.example.api_teste.mapper.TarefaMapper;
import com.example.api_teste.service.TarefaService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    private final TarefaService servico;
    private final TarefaMapper mapper;

    public TarefaController(TarefaService servico, TarefaMapper mapper) {
        this.servico = servico;
        this.mapper = mapper;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Long> cadastrar(@RequestBody @Valid TarefaDto objeto) {
        var objetoConvertido = mapper.toEntity(objeto);
        var registro = servico.salvar(objetoConvertido);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(registro.getId());
    }

    @GetMapping("/consultar")
    public ResponseEntity<List<TarefaDto>> consultar(@RequestParam(required = false) String termoBusca) { //required = false nesse contexto é retornar td se termoBusca for null ou retornar um objeto especifico se algum parametro for passado, tipo, busca por nome
        var registros = servico.consultar(termoBusca);
        var dtos = registros.stream().map(mapper::toDto).toList();

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<TarefaDto> consultarPorId(@PathVariable Long id) {
        var registro = servico.consultar(id);
        if(registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        var dto = mapper.toDto(registro);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PutMapping("/editar")
    public ResponseEntity<TarefaDto> editar(@RequestBody @Valid TarefaDto objeto) {
        var objetoConvertido = mapper.toEntity(objeto);
        servico.salvar(objetoConvertido);
        var dto = mapper.toDto(objetoConvertido);
        
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        servico.excluir(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    
}
