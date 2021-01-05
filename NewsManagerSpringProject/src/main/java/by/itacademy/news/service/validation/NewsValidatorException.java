package by.itacademy.news.service.validation;

import by.itacademy.news.service.ServiceException;

public class NewsValidatorException extends ServiceException {
    private static final long serialVersionUID = 1L;

    public NewsValidatorException() {
        super();
    }

    public NewsValidatorException(String message) {
        super(message);
    }

    public NewsValidatorException(Exception e) {
        super(e);
    }

    public NewsValidatorException(String message, Exception e) {
        super(message, e);
    }
}
