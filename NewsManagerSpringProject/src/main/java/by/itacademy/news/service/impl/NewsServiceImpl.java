package by.itacademy.news.service.impl;

import by.itacademy.news.dao.DAOException;
import by.itacademy.news.dao.NewsDAO;
import by.itacademy.news.entity.News;
import by.itacademy.news.service.NewsService;
import by.itacademy.news.service.ServiceException;
import by.itacademy.news.service.validation.NewsValidator;
import by.itacademy.news.service.validation.NewsValidatorException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDAO newsDAO;

    private static final Logger logger = Logger.getLogger(NewsServiceImpl.class);

    @Override
    @Transactional
    public void save(News news) throws ServiceException {
        if(!NewsValidator.isCorrect(news)){
            logger.error("Data validation error");
            throw new NewsValidatorException("Data validation error");
        }
        try{
            newsDAO.save(news);
        }catch (DAOException e){
            logger.error("Error saving news", e);
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public News selectById(int id) throws ServiceException {
        News news;
        try{
            news = newsDAO.selectById(id);
        } catch (DAOException e){
            logger.error("Error getting news by id", e);
            throw new ServiceException(e);
        }
        return news;
    }

    @Override
    @Transactional
    public List<News> selectAll() throws ServiceException {
        List<News> news;
        try{
            news = newsDAO.selectAll();
        } catch (DAOException e){
            logger.error("Error getting newsList", e);
            throw new ServiceException(e);
        }
        return news;
    }

    @Override
    @Transactional
    public void delete(int id) throws ServiceException {
        try{
            newsDAO.delete(id);
        } catch (DAOException e){
            logger.error("Error deleting news", e);
            throw new ServiceException(e);
        }
    }

}

