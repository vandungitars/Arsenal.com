package com.vandung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vandung.model.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long>{

}
