package com.cart.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cart.model.Category;

import java.util.ArrayList;
import java.util.List;

public class FieldErrorDTO {

    private String field;

    private String message;

    public FieldErrorDTO(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}

