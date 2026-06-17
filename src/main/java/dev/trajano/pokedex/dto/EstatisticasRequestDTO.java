package dev.trajano.pokedex.dto;

public record EstatisticasRequestDTO(
        Integer hp,
        Integer ataque,
        Integer defesa,
        Integer ataqueEspecial,
        Integer defesaEspecial,
        Integer velocidade
) {
}
