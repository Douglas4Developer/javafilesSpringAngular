package com.dev.javafiles.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diretorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    // Relacionamento com arquivos
    @OneToMany(mappedBy = "diretorio", cascade = CascadeType.ALL)
    @JsonManagedReference  // Controla a serialização de arquivos
    private List<Arquivo> arquivos;

    // Relacionamento com subdiretórios
    @OneToMany(mappedBy = "diretorioPai", cascade = CascadeType.ALL)
    @JsonManagedReference  // Controla a serialização de subdiretórios
    private List<Diretorio> subdiretorios;

    // Relacionamento com diretório pai
    @ManyToOne
    @JoinColumn(name = "diretorio_pai_id")
    @JsonBackReference  // Evita a serialização do diretório pai na serialização de subdiretórios
    private Diretorio diretorioPai;
}
