package com.dev.javafiles.repository;

import com.dev.javafiles.model.Diretorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiretorioRepository extends JpaRepository<Diretorio, Long> {
}
