package com.client.clinique.rapportDiabete.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.client.clinique.rapportDiabete.model.RapportDiabete;

public interface RapportRepository extends JpaRepository<RapportDiabete, Integer> {

}
