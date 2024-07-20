package study.mutsa_login.member;

import study.mutsa_login.exception.BaseErrorCode;
import study.mutsa_login.exception.CustomException;

public class MemberCustomException extends CustomException {

    public MemberCustomException(BaseErrorCode errorCode) {
        super(errorCode);
    }

    public static class DuplicatedNicknameException extends MemberCustomException {
        public DuplicatedNicknameException() {
            super(MemberErrorCode.DUPLICATE_NICKNAME);
        }
    }

    public static class DuplicatedEmailException extends MemberCustomException {
        public DuplicatedEmailException() {
            super(MemberErrorCode.DUPLICATE_EMAIL);
        }
    }

    public static class IncorrectPasswordException extends MemberCustomException {
        public IncorrectPasswordException() {
            super(MemberErrorCode.INVALID_IN_PASSWORD);
        }
    }

}