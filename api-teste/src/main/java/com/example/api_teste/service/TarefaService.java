package com.example.api_teste.service;

import java.util.List;

import com.example.api_teste.model.Tarefa;
import com.example.api_teste.repository.TarefaRepository;

public class TarefaService {

    private final TarefaRepository repo;

    public TarefaService(TarefaRepository repo) {
        this.repo = repo;
    }

    public List<Tarefa> consultar(String termoBusca) {
        if(termoBusca != null) {
            termoBusca = termoBusca.trim(); //tira espaços em branco
        }

        return repo.consultar(termoBusca);
    }

    public Tarefa consultar(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Tarefa salvar(Tarefa objeto) {
        return repo.save(objeto); //metodo padrao
    }

    public void excluir(Long id) {  //criar validacao dps
        repo.deleteById(id);
    } 
}
