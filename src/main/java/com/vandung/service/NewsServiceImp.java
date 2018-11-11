package com.vandung.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vandung.model.Comment;
import com.vandung.model.News;
import com.vandung.model.Type_News;
import com.vandung.repository.NewsRepository;

@Service("newsService")
@Transactional
public class NewsServiceImp implements NewsService{

	@Autowired
	private NewsRepository newsRepository;
	
	@Autowired
	private EntityManager entityManager;

	public News findById(Long id_news) {
		return newsRepository.getOne(id_news);
	}

	public List<News> findAll() {
		List<News> listNews = newsRepository.findAll();
		listNews.sort((p1,p2) ->{
			if(p1.getTitle_news().compareTo(p2.getTitle_news()) > 0)
				return 1;
			else
				return -1;
		});
		return listNews;
	}

	public void save(News news) {
		newsRepository.save(news);
	}
	
	@Override
	public void update(News news, Long id_news) {
		News newsDb = newsRepository.getOne(id_news);
		newsDb.setTitle_news(news.getTitle_news());
		newsDb.setDescription_news(news.getDescription_news());
		newsDb.setContent_news(news.getContent_news());
		newsDb.setTimeupdate_news(news.getTimeupdate_news());	
		newsRepository.save(newsDb);
	}

	@Override
	public void deleteById(Long id_news) {
		newsRepository.deleteById(id_news);
	}

	@Override
	public News findByName(String title_news) {
	    TypedQuery<News> query = entityManager.createQuery(
	        "FROM News WHERE title_news=" + title_news, News.class);
	    return query.getSingleResult();
	}

	@Override
	public List<Type_News> getListType() {
		TypedQuery<Type_News> query = entityManager.createQuery("FROM Type_News", Type_News.class);		
		return query.getResultList();
	}

	@Override
	public void saveComment(Comment comment) {
		Query query = entityManager.createNativeQuery("INSERT INTO Comment (content_comment, email_comment) " +
	            " VALUES(?,?)");
		query.setParameter(1, comment.getContent_comment());
		query.setParameter(2, comment.getEmail_comment());
		query.executeUpdate();
	}

	@Override
	public List<News> getListNewsByType(Long id_type, int maxRecord) {
		TypedQuery<News> query = entityManager.createQuery("FROM News where id_type="+ id_type, News.class);
		query.setMaxResults(maxRecord); 
		return query.getResultList();
	} 
}
