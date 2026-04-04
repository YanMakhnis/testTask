package com.example.api_project.dto.createRequestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ContactCreateData {
    @NotBlank(message = "Phone is required")
    private String phone;

    @Email(message = "Email should be valid")
    private String email;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
