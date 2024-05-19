package com.example.livraria.repository;

import com.example.livraria.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Integer> {
    Optional<Livro> findByTitulo(String titulo);
    Optional<Livro> findByDataPublicacao(LocalDate dataPublicacao);
    List<Livro> findByDataPublicacaoBetweenAndTituloContainingIgnoreCase(LocalDate startDate, LocalDate endDate, String titulo);
    @Query("SELECT SUM(l.preco) FROM Livro l")
    Double sumPrecos();
}
