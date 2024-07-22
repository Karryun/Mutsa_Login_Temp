package study.mutsa_login.member;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.mutsa_login.exception.CustomException;
import study.mutsa_login.member.dto.JoinMemberRequest;
import study.mutsa_login.member.dto.LoginMemberRequest;
import study.mutsa_login.member.MemberCustomException.*;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "^(010|011|016|017|018|019)-\\d{3,4}-\\d{4}$");

    public Member loginMember(LoginMemberRequest request) {
        String loginEmail = request.email();
        String loginPassword = request.password();
        Member member = memberRepository.findByEmail(loginEmail)
                .orElseThrow(EmailNotFoundException::new);

        if (!Objects.equals(loginPassword, member.getPassword())) {
            throw new IncorrectPasswordException();
        }
        log.info("로그인 성공");
        return member;
    }

    @Transactional
    public void joinMember(JoinMemberRequest request) {
        String joinnickname = request.nickname();
        String joinemail = request.email();
        String joinphonenumber = request.phonenumber();
        String joinpassword = request.password();

        checkDuplicateNickname(joinnickname);
        checkDuplicateEmail(joinemail);
        InvalidateEmailFormat(joinemail);
        checkDuplicatePhonenumber(joinphonenumber);
        validatePhoneNumberFormat(joinphonenumber);
        checkPasswordLength(joinpassword);

        Member member = new Member(joinnickname, joinemail, joinphonenumber ,joinpassword);
        memberRepository.save(member);
    }


    //함수들
    private void checkDuplicateNickname(String nickname) {
        Optional<Member> optionalNickname = memberRepository.findByNickname(nickname);
        if (optionalNickname.isPresent()) {
            throw new DuplicatedNicknameException();
        }
    }

    private void InvalidateEmailFormat(String email) {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidEmailFormatException();
        }
    }

    private void checkDuplicateEmail(String email) {
        Optional<Member> optionalEmail = memberRepository.findByEmail(email);
        if (optionalEmail.isPresent()) {
            throw new DuplicatedEmailException();
        }
    }

    private void checkDuplicatePhonenumber(String phonenumber) {
        Optional<Member> optionalEmail = memberRepository.findByPhonenumber(phonenumber);
        if (optionalEmail.isPresent()) {
            throw new DuplicatedPhonenumberException();
        }
    }

    private void validatePhoneNumberFormat(String phoneNumber) {
        if (!PHONE_PATTERN.matcher(phoneNumber).matches()) {
            throw new InvalidPhonenumberFormatException();
        }
    }

    private void checkPasswordLength(String password) {
        if (password.length() < 6) {
            throw new PasswordTooShortException();
        }
    }

}