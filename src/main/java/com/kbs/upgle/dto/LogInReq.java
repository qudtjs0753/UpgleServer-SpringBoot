package com.kbs.upgle.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @Author: kbs
 */
@Data
public class LogInReq {
    @Email
    @NotBlank
    private final String email;

    @NotBlank
    private final String password;

}
