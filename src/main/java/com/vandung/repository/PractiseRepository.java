package com.vandung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vandung.model.Practise;

@Repository
public interface PractiseRepository extends JpaRepository<Practise, Long>{

}
