package com.vandung.service;

import java.util.List;

import com.vandung.model.Comment;
import com.vandung.model.News;
import com.vandung.model.Type_News;

public interface NewsService {

	News findById(Long id_news);
	News findByName(String name);
	List<News> findAll();
	void save(News news);
	void update(News news, Long id_news);
	void deleteById(Long id_news);
	List<Type_News> getListType();
	void saveComment(Comment comment);
	List<News> getListNewsByType(Long id_type, int maxRecord);
}
