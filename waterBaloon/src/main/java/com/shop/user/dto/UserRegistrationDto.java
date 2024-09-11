package com.shop.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationDto {

    @NotBlank(message = "이름을 입력해 주세요.")
    @Size(max = 30, message = "이름은 30자 이내로 입력해 주세요.")
	@Pattern(regexp = "^[가-힣a-zA-Z]+$", message = "이름은 한글 또는 영문만 입력 가능합니다.")
    private String name;

    @NotBlank(message = "아이디를 입력해 주세요.")
    @Pattern(regexp = "^[a-z0-9]{4,20}$", message = "아이디는 영문 소문자와 숫자 4~12자리여야 합니다.")
    @Size(min = 4, max = 30, message = "아이디는 4자 이상 30자 이내로 입력해 주세요.")
    private String username;

	@NotBlank(message = "비밀번호를 입력해 주세요.")
	@Size(min = 8, max = 30, message = "비밀번호는 8자 이상 30자 이하로 입력해 주세요.")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "비밀번호는 최소 하나의 영문자, 하나의 숫자, 하나의 특수문자를 포함해야 합니다.")
	private String password;
    
    @NotBlank(message = "비밀번호 확인을 입력해 주세요.")
    @Size(min = 8, max = 30, message = "비밀번호 확인은 8자 이상 30자 이하로 입력해 주세요.")
    private String confirmPassword;

    @Size(max = 11, message = "전화번호는 11자 이내로 입력해 주세요.")
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
