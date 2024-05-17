package com.example.livraria.controller;

import com.example.livraria.entity.Livro;
import com.example.livraria.repository.LivroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroRepository livroRepository;

    @GetMapping
    public ResponseEntity<List<Livro>> listarLivros() {
        List<Livro> livros = livroRepository.findAll();

        if (livros.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> findById(@PathVariable Integer id) {

        Optional<Livro> livroOpt = livroRepository.findById(id);

        if (livroOpt.isPresent()) {
            Livro livroEncontrado = livroOpt.get();
            return ResponseEntity.status(200).body(livroEncontrado);
        }
        return ResponseEntity.status(404).build();
    }

    @PostMapping()
    public ResponseEntity<Livro> cadastrar(@RequestBody @Valid Livro livro) {
        Livro livroSalvo = livroRepository.save(livro);
        return ResponseEntity.status(201).body(livroSalvo);
    }

    @PutMapping("{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Integer id, @RequestBody @Valid Livro livro) {
        Optional<Livro> livroPorId = livroRepository.findById(id);

        if (livroPorId.isPresent()) {

            livroPorId.get().setTitulo(livro.getTitulo());
            livroPorId.get().setDescricao(livro.getDescricao());
            livroPorId.get().setDataPublicacao(livro.getDataPublicacao());
            livroPorId.get().setPreco(livro.getPreco());

            return ResponseEntity.status(200).body(livroPorId.get());
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping("/titulo")
    public ResponseEntity<Livro> findByTitulo(@RequestParam String titulo) {

        Optional<Livro> livroPorTitulo = livroRepository.findByTitulo(titulo);

        if (livroPorTitulo.isPresent()) {
            Livro livroEncontrado = livroPorTitulo.get();
            return ResponseEntity.status(200).body(livroEncontrado);
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/data")
    public ResponseEntity<Livro> findByDataPublicacao(@RequestParam LocalDate dataPublicacao) {

        Optional<Livro> livroPorData = livroRepository.findByDataPublicacao(dataPublicacao);

        if (livroPorData.isPresent()) {
            Livro livroEncontrado = livroPorData.get();
            return ResponseEntity.status(200).body(livroEncontrado);
        }
        return ResponseEntity.status(404).build();
    }
}
