package dev.trajano.pokedex.controller;

import dev.trajano.pokedex.dto.PokemonRequestDTO;
import dev.trajano.pokedex.dto.PokemonResponseDTO;
import dev.trajano.pokedex.dto.PokemonsResponse;
import dev.trajano.pokedex.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemons")
@RequiredArgsConstructor
public class PokemonController {

    private final PokemonService pokemonService;

    @PostMapping
    public ResponseEntity<PokemonResponseDTO> criar(@RequestBody PokemonRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pokemonService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<Page<PokemonsResponse>> listarTodos(Pageable pageable) {
        return ResponseEntity.ok(pokemonService.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pokemonService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PokemonResponseDTO> atualizar(@PathVariable Long id, @RequestBody PokemonRequestDTO dto) {
        return ResponseEntity.ok(pokemonService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pokemonService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
