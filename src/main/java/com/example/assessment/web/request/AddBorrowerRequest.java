package com.example.assessment.web.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddBorrowerRequest {
    @NotBlank(message = "Name must not be blank")
    private String name;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email must not be blank")
    private String email;
}
