package dev.trajano.pokedex.dto;

import java.util.List;

public record PokemonRequestDTO(
        Integer numeroPokedex,
        String nome,
        Double altura,
        Double peso,
        List<Long> tipoIds,
        EstatisticasRequestDTO estatisticas,
        String imagemUrl,
        String imagemShinyUrl
) {
}
