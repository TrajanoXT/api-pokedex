package dev.trajano.pokedex.mapper;

import dev.trajano.pokedex.dto.*;
import dev.trajano.pokedex.entity.Estatisticas;
import dev.trajano.pokedex.entity.Pokemon;
import dev.trajano.pokedex.entity.Tipo;

import java.util.List;

public class PokemonMapper {
    public static TipoResponseDTO toResponse(Tipo tipo) {
        if (tipo == null) return null;
        return new TipoResponseDTO(
                tipo.getId(),
                tipo.getNome()
        );
    }

    public static Tipo toEntity(TipoRequestDTO dto) {
        if (dto == null) return null;
        Tipo tipo = new Tipo();
        tipo.setNome(dto.nome());
        return tipo;
    }

    public static EstatisticasResponseDTO toResponse(Estatisticas e) {
        if (e == null) return null;
        return new EstatisticasResponseDTO(
                e.getId(),
                e.getHp(),
                e.getAtaque(),
                e.getDefesa(),
                e.getAtaqueEspecial(),
                e.getDefesaEspecial(),
                e.getVelocidade()
        );
    }

    public static Estatisticas toEntity(EstatisticasRequestDTO dto) {
        if (dto == null) return null;
        Estatisticas e = new Estatisticas();
        e.setHp(dto.hp());
        e.setAtaque(dto.ataque());
        e.setDefesa(dto.defesa());
        e.setAtaqueEspecial(dto.ataqueEspecial());
        e.setDefesaEspecial(dto.defesaEspecial());
        e.setVelocidade(dto.velocidade());
        return e;
    }

    public static PokemonResponseDTO toResponse(Pokemon pokemon) {
        if (pokemon == null) return null;
        List<TipoResponseDTO> tipos = pokemon.getTipos() == null
                ? List.of()
                : pokemon.getTipos().stream()
                .map(PokemonMapper::toResponse)
                .toList();
        return new PokemonResponseDTO(
                pokemon.getId(),
                pokemon.getNumeroPokedex(),
                pokemon.getNome(),
                pokemon.getAltura(),
                pokemon.getPeso(),
                tipos,
                toResponse(pokemon.getEstatisticas()),
                pokemon.getImagemUrl(),
                pokemon.getImagemShinyUrl()
        );
    }
    public static PokemonsResponse toSimpleResponse(Pokemon pokemon) {
        if (pokemon == null) return null;
        return new PokemonsResponse(
                pokemon.getNumeroPokedex(),
                pokemon.getNome()
        );
    }


    public static Pokemon toEntity(PokemonRequestDTO dto, List<Tipo> tipos) {
        if (dto == null) return null;
        Pokemon pokemon = new Pokemon();
        pokemon.setNumeroPokedex(dto.numeroPokedex());
        pokemon.setNome(dto.nome());
        pokemon.setAltura(dto.altura());
        pokemon.setPeso(dto.peso());
        pokemon.setTipos(tipos);
        pokemon.setEstatisticas(toEntity(dto.estatisticas()));
        pokemon.setImagemUrl(dto.imagemUrl());
        pokemon.setImagemShinyUrl(dto.imagemShinyUrl());
        return pokemon;
    }
}
