package br.com.taxi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.taxi.entity.PosicaoEntity;

public interface PosicaoRepository extends JpaRepository<PosicaoEntity, Long> {

}
