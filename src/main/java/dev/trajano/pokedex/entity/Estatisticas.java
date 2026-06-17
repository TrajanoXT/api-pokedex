package dev.trajano.pokedex.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estatisticas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer hp;
    private Integer ataque;
    private Integer defesa;
    private Integer ataqueEspecial;
    private Integer defesaEspecial;
    private Integer velocidade;
}
