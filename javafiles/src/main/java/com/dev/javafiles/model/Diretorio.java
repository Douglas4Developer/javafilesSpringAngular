package com.dev.javafiles.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"diretorioPai"})  // Ignora o diretorioPai na serialização
public class Diretorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "diretorio_pai_id")
    @JsonIgnoreProperties("subdiretorios")  // Evita a recursão dos subdiretórios quando serializar
    private Diretorio diretorioPai;

    @OneToMany(mappedBy = "diretorioPai", cascade = CascadeType.ALL)
    private List<Diretorio> subdiretorios;

    @OneToMany(mappedBy = "diretorio", cascade = CascadeType.ALL)
    private List<Arquivo> arquivos;
}
