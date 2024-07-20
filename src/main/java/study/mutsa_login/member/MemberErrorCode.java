package study.mutsa_login.member;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import study.mutsa_login.exception.BaseErrorCode;


@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {
    DUPLICATE_NICKNAME(HttpStatus.CONFLICT, "닉네임 중복"),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "이메일 중복"),
    INVALID_IN_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호 불일치");

    private final HttpStatus httpStatus;
    private final String message;

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getErrorMessage() {
        return message;
    }
}
