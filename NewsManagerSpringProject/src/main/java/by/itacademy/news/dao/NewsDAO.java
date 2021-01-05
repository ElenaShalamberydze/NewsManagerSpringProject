package by.itacademy.news.dao;

import by.itacademy.news.entity.News;

import java.util.List;

public interface NewsDAO {
    void save(News news) throws DAOException;

    News selectById(int id) throws DAOException;

    List<News> selectAll() throws DAOException;

    void delete(int id) throws DAOException;
}
