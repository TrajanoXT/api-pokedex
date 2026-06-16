package dev.trajano.pokedex.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numeroPokedex;

    private String nome;

    private Double altura;

    private Double peso;

    @ManyToMany
    @JoinTable(
            name = "pokemon_tipo",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "tipo_id")
    )
    private List<Tipo> tipos;

    @OneToOne(cascade = CascadeType.ALL)
    private Estatisticas estatisticas;
}
