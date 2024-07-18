package study.mutsa_login.member;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.mutsa_login.member.dto.JoinMemberRequest;
import study.mutsa_login.member.dto.LoginMemberRequest;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member loginMember(LoginMemberRequest request) {
        String loginEmail = request.email();
        String loginPassword = request.password();
        Member member = memberRepository.findByEmail(loginEmail)
                .orElseThrow(EntityNotFoundException::new);

        if (!Objects.equals(loginEmail, member.getEmail()) || !Objects.equals(loginPassword, member.getPassword())) {
            log.error("로그인 정보 일치하지 않음");
            throw new IllegalArgumentException("로그인 정보 일치하지 않음");
        }
        log.info("로그인 성공");
        return member;
    }

    @Transactional
    public void joinMember(@Valid JoinMemberRequest request) {
        String joinnickname = request.nickname();
        String joinemail = request.email();
        String joinpassword = request.password();

        checkDuplicateNickname(joinnickname);
        checkDuplicateEmail(joinemail);

        Member member = new Member(joinnickname, joinemail, joinpassword);
        memberRepository.save(member);
    }



    private void checkDuplicateNickname(String nickname) {
        Optional<Member> optionalNickname = memberRepository.findByNickname(nickname);
        if (optionalNickname.isPresent()) {
            log.error("중복 닉네임");
            throw new IllegalArgumentException("중복 닉네임");
        }
    }

    private void checkDuplicateEmail(String email) {
        Optional<Member> optionalEmail = memberRepository.findByEmail(email);
        if (optionalEmail.isPresent()) {
            log.error("중복 이메일");
            throw new IllegalArgumentException("중복 이메일");
        }
    }
}