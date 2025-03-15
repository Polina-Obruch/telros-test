package ru.telros_test.core.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String nameEntity, Long id) {
        super(nameEntity + " with id=" + id + " not found");
    }
}
