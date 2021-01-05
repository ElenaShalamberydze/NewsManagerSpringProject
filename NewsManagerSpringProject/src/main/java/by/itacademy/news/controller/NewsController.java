package by.itacademy.news.controller;

import by.itacademy.news.entity.News;
import by.itacademy.news.service.NewsService;
import by.itacademy.news.service.ServiceException;
import by.itacademy.news.service.validation.NewsValidatorException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

import static by.itacademy.news.controller.ConstantValues.*;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    private static final Logger logger = Logger.getLogger(NewsController.class);

    @RequestMapping("/main")
    public String allNews(HttpSession session, Model model){

        List<News> news;
        String page;
        try{
            news = newsService.selectAll();
            page = MAIN_PAGE;
            model.addAttribute("newsList", news);
        } catch(ServiceException e){
            logger.error("Error getting newsList", e);
            session.setAttribute("errorId", ERROR_1);
            page = ERROR_PAGE;
        }
        session.setAttribute("currentPage", MAIN_PAGE);
        return page;
    }


    @RequestMapping("/shownews")
    public String showNews(@RequestParam(ID) int id, HttpSession session, Model model){
        News news;
        String page;

        try{
            news = newsService.selectById(id);
            model.addAttribute(news);
            page = SHOW_NEWS_PAGE;
        } catch (ServiceException e){
            logger.error("Error getting news", e);
            session.setAttribute("errorId", ERROR_1);
            page = ERROR_PAGE;
        }
        session.setAttribute("currentPage", SHOW_NEWS_ID + id);
        return page;
    }


    @PostMapping("/delete")
    public String deleteNews(HttpServletRequest request){
        int id;
        String page;
        String[] deleteId = request.getParameterValues(DELETE_ID);
        try{
            for(String delete : deleteId){
                id = Integer.parseInt(delete);
                newsService.delete(id);
            }
            page = MAIN_PAGE;
        }catch (NullPointerException e) {
            logger.error("Error deleting news - nothing checked", e);
            request.getSession().setAttribute("errorId", ERROR_2);
            page = ERROR_PAGE;
        } catch (ServiceException e){
            logger.error("Error deleting news", e);
            request.getSession().setAttribute("errorId", ERROR_1);
            page = ERROR_PAGE;
        }
        return REDIRECT + page;
    }

    @RequestMapping("/createnews")
    public String createNews(HttpSession session, Model model){
        News news = new News();
        model.addAttribute(news);
        session.setAttribute("currentPage", CREATE_NEWS);
        return CREATE_NEWS_PAGE;
    }

    @PostMapping("/savenews")
    public String saveNews(@ModelAttribute(NEWS) News news, HttpServletRequest request){
        String page;
        news.setDate(LocalDate.now());

        try{
            newsService.save(news);
            page = MAIN_PAGE;
        } catch (NewsValidatorException e){
            logger.error("Validation error while saving news", e);
            request.getSession().setAttribute("errorId", ERROR_3);
            page = ERROR_PAGE;
        } catch (ServiceException e){
            logger.error("Error saving news", e);
            request.getSession().setAttribute("errorId", ERROR_1);
            page = ERROR_PAGE;
        }

        return REDIRECT + page;
    }


    @RequestMapping("/editnews")
    public String editNews(HttpServletRequest request, Model model){
        String page;
        News news;
        int id = Integer.parseInt(request.getParameter(ID));
        try{
            news = newsService.selectById(id);
            news.setDate(LocalDate.now());
            model.addAttribute(news);
            request.getSession().setAttribute("currentPage", EDIT_NEWS_ID + id);
            page = EDIT_NEWS_PAGE;
        } catch(ServiceException e){
            logger.error("Error getting edit-news page", e);
            request.getSession().setAttribute("errorId", ERROR_1);
            page = ERROR_PAGE;
        }

        return page;
    }


    @RequestMapping("/changelocale")
    public String changeLocale(@RequestParam(LANG) String lang, HttpSession session){
        session.setAttribute("locale", lang);
        String currentPage = (String) session.getAttribute(CURRENT_PAGE);
        return REDIRECT + currentPage;
    }

    @GetMapping("/error")
    public String error(HttpSession session){
        session.setAttribute("currentPage", ERROR_PAGE);
        return ERROR_PAGE;
    }
}

