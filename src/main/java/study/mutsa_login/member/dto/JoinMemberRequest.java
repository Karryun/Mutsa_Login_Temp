package study.mutsa_login.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record JoinMemberRequest(String nickname, String email, String phonenumber, String password) {
}
