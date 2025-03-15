package ru.telros_test.core.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.telros_test.core.exception.ValidationException;

public class PaginationUtils {

    public static Pageable toPage(Integer from, Integer size) {
        if (from == null || size == null) {
            return null;
        }

        if (size <= 0 || from < 0) {
            throw new ValidationException("Уточнчите правильность параметров отображения");
        }

        int page = from / size;
        return PageRequest.of(page, size);
    }
}
