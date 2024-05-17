package com.example.livraria.repository;

import com.example.livraria.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Integer> {
    Optional<Livro> findByTitulo(String titulo);
    Optional<Livro> findByDataPublicacao(LocalDate dataPublicacao);
}
