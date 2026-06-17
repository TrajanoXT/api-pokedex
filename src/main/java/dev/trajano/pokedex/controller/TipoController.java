package dev.trajano.pokedex.controller;

import dev.trajano.pokedex.dto.TipoRequestDTO;
import dev.trajano.pokedex.dto.TipoResponseDTO;
import dev.trajano.pokedex.service.TipoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos")
@RequiredArgsConstructor
public class TipoController {

    private final TipoService tipoService;

    @PostMapping
    public ResponseEntity<TipoResponseDTO> criar(@RequestBody TipoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<TipoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(tipoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tipoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoResponseDTO> atualizar(@PathVariable Long id, @RequestBody TipoRequestDTO dto) {
        return ResponseEntity.ok(tipoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tipoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
