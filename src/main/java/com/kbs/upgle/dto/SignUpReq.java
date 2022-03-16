package com.kbs.upgle.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kbs.upgle.domain.user.User;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Author: kbs
 */
@Builder
@Data
public class SignUpReq {
    @Email
    @NotBlank(message="이메일을 입력해주셔야 합니다.")
    private final String email;

    //deserialize할때만 접근됨.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]{8,}$", message="영문, 숫자로 이루어진 8자 이상의 비밀번호이어야 합니다.")
    private final String password;

    private final String nickname;

    public static SignUpReq from(User user) {
        if(user == null) return null;

        return SignUpReq.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .build();
    }
}
