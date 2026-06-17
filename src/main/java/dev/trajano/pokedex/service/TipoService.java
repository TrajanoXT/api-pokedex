package dev.trajano.pokedex.service;

import dev.trajano.pokedex.dto.TipoRequestDTO;
import dev.trajano.pokedex.dto.TipoResponseDTO;
import dev.trajano.pokedex.entity.Tipo;
import dev.trajano.pokedex.mapper.PokemonMapper;
import dev.trajano.pokedex.repository.TipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoService {

    private final TipoRepository tipoRepository;

    public TipoResponseDTO criar(TipoRequestDTO dto) {
        Tipo tipo = PokemonMapper.toEntity(dto);
        return PokemonMapper.toResponse(tipoRepository.save(tipo));
    }

    public TipoResponseDTO buscarPorId(Long id) {
        Tipo tipo = tipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo não encontrado"));
        return PokemonMapper.toResponse(tipo);
    }

    public List<TipoResponseDTO> listarTodos() {
        return tipoRepository.findAll()
                .stream()
                .map(PokemonMapper::toResponse)
                .toList();
    }

    public TipoResponseDTO atualizar(Long id, TipoRequestDTO dto) {
        Tipo tipo = tipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo não encontrado"));
        tipo.setNome(dto.nome());
        return PokemonMapper.toResponse(tipoRepository.save(tipo));
    }

    public void deletar(Long id) {
        tipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo não encontrado"));
        tipoRepository.deleteById(id);
    }
}