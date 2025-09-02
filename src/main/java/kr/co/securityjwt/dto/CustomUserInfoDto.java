package kr.co.securityjwt.dto;

import jakarta.validation.constraints.NotNull;
import kr.co.securityjwt.entity.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserInfoDto {

  private Long memberId;
  private String email;
  private String password;
  private String name;
  private RoleType role;
}
