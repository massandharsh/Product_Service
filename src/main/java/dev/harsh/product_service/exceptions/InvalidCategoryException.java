package dev.harsh.product_service.exceptions;

public class InvalidCategoryException extends RuntimeException {
    public InvalidCategoryException(String msg) {
        super(msg);
    }
}
