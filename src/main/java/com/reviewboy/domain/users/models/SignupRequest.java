package com.reviewboy.domain.users.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
public class SignupRequest {
    @NotBlank
    @Size(min = 5, max = 30)
    private String username;
    @NotBlank
    @Size(min = 8, max = 30)
    private String password;
    /**
     * =? @Email 은 유효성 검증만 해줄뿐, @NotBlank를 함께 써줘야 함
     */
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 1, max = 30)
    private String name;
}
