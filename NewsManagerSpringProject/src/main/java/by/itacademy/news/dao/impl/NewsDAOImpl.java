package by.itacademy.news.dao.impl;

import by.itacademy.news.dao.NewsDAO;
import by.itacademy.news.dao.DAOException;
import by.itacademy.news.entity.News;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsDAOImpl implements NewsDAO {

    @Autowired
    private SessionFactory mySessionFactory;

    private static final String SELECT_ALL = "from News";
    private static final String SELECT_BY_ID = "from News where id =:id";


    @Override
    public void save(News news) throws DAOException {
        Session currentSession = mySessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(news);
    }

    @Override
    public News selectById(int id) throws DAOException {
        Session currentSession = mySessionFactory.getCurrentSession();
        Query<News> theQuery = currentSession.createQuery(SELECT_BY_ID, News.class);
        theQuery.setParameter("id", id);
        News news = theQuery.uniqueResult();

        return news;
    }

    @Override
    public List<News> selectAll() throws DAOException {
        Session currentSession = mySessionFactory.getCurrentSession();
        Query<News> theQuery = currentSession.createQuery(SELECT_ALL, News.class);
        List<News> news = theQuery.getResultList();
        return news;
    }


    @Override
    public void delete(int id) throws DAOException {
        Session currentSession = mySessionFactory.getCurrentSession();
        News news = currentSession.load(News.class, id);
        currentSession.delete(news);
    }
}
