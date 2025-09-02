package kr.co.securityjwt.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

    @NotNull(message = "이메일은 필수 입력 값입니다.")
    private String email;

    @NotNull(message = "비밀번호는 필수 입력 값입니다.")
    private String password;
}
