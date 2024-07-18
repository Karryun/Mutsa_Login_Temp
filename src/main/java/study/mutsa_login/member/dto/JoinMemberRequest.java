package study.mutsa_login.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record JoinMemberRequest(
        @NotBlank
        @Size(min = 2, max = 10)
        String nickname,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 6)
        String password
) {
}
