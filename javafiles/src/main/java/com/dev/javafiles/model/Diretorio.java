package com.dev.javafiles.model;

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

    @ManyToOne
    @JoinColumn(name = "diretorio_pai_id")
    private Diretorio diretorioPai;

    @OneToMany(mappedBy = "diretorioPai", cascade = CascadeType.ALL)
    private List<Diretorio> subdiretorios;

    @OneToMany(mappedBy = "diretorio", cascade = CascadeType.ALL)
    private List<Arquivo> arquivos;
}
