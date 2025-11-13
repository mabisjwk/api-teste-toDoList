package com.example.api_teste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.api_teste.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

    @Query("""
        SELECT t FROM Tarefa t
        WHERE :termoBusca IS NULL
        OR t.nome LIKE %:termoBusca%
    """)
    
    List<Tarefa> consultar(String termoBusca);
    
} 
