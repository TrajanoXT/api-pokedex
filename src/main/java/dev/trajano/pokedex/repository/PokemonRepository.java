package dev.trajano.pokedex.repository;

import dev.trajano.pokedex.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}
