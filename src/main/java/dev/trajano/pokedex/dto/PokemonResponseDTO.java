package dev.trajano.pokedex.dto;

import java.util.List;

public record PokemonResponseDTO(
        Long id,
        Integer numeroPokedex,
        String nome,
        Double altura,
        Double peso,
        List<TipoResponseDTO> tipos,
        EstatisticasResponseDTO estatisticas,
        String imageUrl,
        String imagemShinyUrl
) {
}
