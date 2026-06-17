package dev.trajano.pokedex.service;

import dev.trajano.pokedex.dto.PokemonRequestDTO;
import dev.trajano.pokedex.dto.PokemonResponseDTO;
import dev.trajano.pokedex.dto.PokemonsResponse;
import dev.trajano.pokedex.entity.Estatisticas;
import dev.trajano.pokedex.entity.Pokemon;
import dev.trajano.pokedex.entity.Tipo;
import dev.trajano.pokedex.mapper.PokemonMapper;
import dev.trajano.pokedex.repository.PokemonRepository;
import dev.trajano.pokedex.repository.TipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PokemonService {

    private final PokemonRepository pokemonRepository;
    private final TipoRepository tipoRepository;

    public PokemonResponseDTO criar(PokemonRequestDTO dto) {
        List<Tipo> tipos = tipoRepository.findAllById(dto.tipoIds());
        Pokemon pokemon = PokemonMapper.toEntity(dto, tipos);
        return PokemonMapper.toResponse(pokemonRepository.save(pokemon));
    }

    public PokemonResponseDTO buscarPorId(Long id) {
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pokémon não encontrado"));
        return PokemonMapper.toResponse(pokemon);
    }

    public Page<PokemonsResponse> listarTodos(Pageable pageable) {
        Page<Pokemon> pokemons = pokemonRepository.findAll(pageable);
        return pokemons.map(PokemonMapper::toSimpleResponse);
    }


    public PokemonResponseDTO atualizar(Long id, PokemonRequestDTO dto) {
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pokémon não encontrado"));

        List<Tipo> tipos = tipoRepository.findAllById(dto.tipoIds());

        pokemon.setNumeroPokedex(dto.numeroPokedex());
        pokemon.setNome(dto.nome());
        pokemon.setAltura(dto.altura());
        pokemon.setPeso(dto.peso());
        pokemon.setTipos(tipos);

        Estatisticas e = pokemon.getEstatisticas();
        e.setHp(dto.estatisticas().hp());
        e.setAtaque(dto.estatisticas().ataque());
        e.setDefesa(dto.estatisticas().defesa());
        e.setAtaqueEspecial(dto.estatisticas().ataqueEspecial());
        e.setDefesaEspecial(dto.estatisticas().defesaEspecial());
        e.setVelocidade(dto.estatisticas().velocidade());

        return PokemonMapper.toResponse(pokemonRepository.save(pokemon));
    }

    public void deletar(Long id) {
        pokemonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pokémon não encontrado"));
        pokemonRepository.deleteById(id);
    }
}
