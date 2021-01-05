package by.itacademy.news.service;

import by.itacademy.news.entity.News;

import java.util.List;

public interface NewsService {

    void save(News news) throws ServiceException;

    News selectById(int id) throws ServiceException;

    List<News> selectAll() throws ServiceException;

    void delete(int id) throws ServiceException;
}
