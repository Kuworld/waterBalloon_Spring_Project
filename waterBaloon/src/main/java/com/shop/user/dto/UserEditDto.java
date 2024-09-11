package com.shop.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserEditDto {

    @NotBlank(message = "이름을 입력해 주세요.")
    @Size(max = 30, message = "이름은 30자 이내로 입력해 주세요.")
    private String name;

    @Size(max = 20, message = "전화번호는 20자 이내로 입력해 주세요.")
    private String tel;

    @Email(message = "유효한 이메일 주소를 입력해 주세요.")
    @Size(max = 100, message = "이메일은 100자 이내로 입력해 주세요.")
    private String email;

    @NotBlank(message = "주소를 입력해 주세요.")
    @Size(max = 300, message = "주소는 300자 이내로 입력해 주세요.")
    private String addr;

    @Size(max = 300, message = "상세 주소는 300자 이내로 입력해 주세요.")
    private String daddr;
}
