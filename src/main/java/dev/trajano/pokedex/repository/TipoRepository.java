package dev.trajano.pokedex.repository;

import dev.trajano.pokedex.entity.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoRepository extends JpaRepository<Tipo, Long> {
}
