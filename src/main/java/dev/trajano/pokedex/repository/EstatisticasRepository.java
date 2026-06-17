package dev.trajano.pokedex.repository;

import dev.trajano.pokedex.entity.Estatisticas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstatisticasRepository extends JpaRepository<Estatisticas, Long> {
}
