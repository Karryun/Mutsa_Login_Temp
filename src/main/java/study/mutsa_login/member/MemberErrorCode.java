package study.mutsa_login.member;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import study.mutsa_login.exception.BaseErrorCode;


@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    //회원가입 예외처리
    DUPLICATE_NICKNAME(HttpStatus.CONFLICT, "닉네임 중복"),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "이메일 중복"),
    INVALID_EMAIL_FORMAT(HttpStatus.BAD_REQUEST, "유효한 이메일 형식이어야 합니다"),
    DUPLICATE_PHONENUMBER(HttpStatus.CONFLICT, "전화번호 중복"),
    INVALID_PHONENUMBER_FORMAT(HttpStatus.BAD_REQUEST, "유효한 전화번호 형식이어야 합니다(ex.010-XXXX-XXXX)"),
    PASSWORD_TOO_SHORT(HttpStatus.BAD_REQUEST, "비밀번호를 6자리 이상으로 해주세요"),


    //로그인 예외처리
    EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 이메일"),
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
