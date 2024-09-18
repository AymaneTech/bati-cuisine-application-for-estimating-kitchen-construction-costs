package com.wora.client.domain.valueObject;

public record Name(String firstName, String lastName) {
    public String fullName() {
        return firstName + " " + lastName;
    }
}
