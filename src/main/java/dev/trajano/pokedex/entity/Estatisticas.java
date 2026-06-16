package dev.trajano.pokedex.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Estatisticas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer hp;
    private Integer ataque;
    private Integer defensa;
    private Integer ataqueEspecial;
    private Integer defensaEspecial;
    private Integer velocidade;
}
