package ru.telros_test.core.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.telros_test.core.exception.ValidationException;

public class PaginationUtils {

    //Параметр page - количество страниц
    //Параметр size - количество записей на странице
    public static Pageable toPage(Integer page, Integer size) {
        if (page == null || size == null) {
            return null;
        }

        if (size <= 0 || page < 0) {
            throw new ValidationException("Уточните правильность параметров отображения");
        }

        return PageRequest.of(page, size);
    }
}
