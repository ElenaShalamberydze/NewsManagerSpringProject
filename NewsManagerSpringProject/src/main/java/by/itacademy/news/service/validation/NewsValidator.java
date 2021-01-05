package by.itacademy.news.service.validation;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.itacademy.news.entity.News;

public class NewsValidator {

    private static Pattern CALENDAR_PATTERN =
            Pattern.compile("^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$"
                    + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                    + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
                    + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$");


    public static boolean isCorrect(News news) throws NewsValidatorException {
        return TitleCheck(news.getTitle()) && BriefCheck(news.getBrief()) && ContentCheck(news.getContent())
                && DateCheck(news.getDate()) && isFieldsDifferent(news.getBrief(), news.getContent());
    }


    private static boolean TitleCheck(String title) throws NewsValidatorException {
        if((1 < title.length()) && (isEthical(title))) {
            return true;
        }
        return false;
    }

    private static boolean BriefCheck(String brief) throws NewsValidatorException {
        if((1 < brief.length()) && (isEthical(brief))){
            return true;
        }
        return false;
    }

    private static boolean ContentCheck(String content) throws NewsValidatorException {
        if((1 < content.length()) && (isEthical(content))) {
            return true;
        }
        return false;
    }

    private static boolean DateCheck(LocalDate date) {
        if((date.isBefore((LocalDate.now().plusDays(1)))) && (date.isAfter(LocalDate.of(2018, 01, 01)))
                && isDateFormat(date)) {
            return true;
        }
        return false;
    }

    private static boolean isFieldsDifferent(String brief, String content) {
        if(content.matches(brief)){
            return false;
        }
        return true;
    }


    private static boolean isEthical(String checkText) throws NewsValidatorException {
        boolean result = true;
        Scanner scanner = null;

        try {
            URL resource = NewsValidator.class.getResource("/blackList.txt");
            File file = Paths.get(resource.toURI()).toFile();

            scanner = new Scanner(file);
            List<String> words = new ArrayList<String>();
            while(scanner.hasNextLine()) {
                words.add(scanner.nextLine());
            }

            for(String word : words) {
                Pattern pattern = Pattern.compile(word);
                Matcher matcher = pattern.matcher(checkText);
                if(matcher.find()) {
                    result = false;
                }
            }

        }catch(FileNotFoundException | URISyntaxException e) {
            throw new NewsValidatorException();
        }finally {
            scanner.close();
        }

        return result;
    }

    private static boolean isDateFormat(LocalDate date) {
        String dateString = date.toString();
        return CALENDAR_PATTERN.matcher(dateString).matches();
    }

}

