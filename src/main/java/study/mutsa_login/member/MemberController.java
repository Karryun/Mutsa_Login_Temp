package study.mutsa_login.member;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.mutsa_login.JwtService;
import study.mutsa_login.member.dto.JoinMemberRequest;
import study.mutsa_login.member.dto.LoginMemberRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final JwtService jwtService;
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<String> login(@RequestBody LoginMemberRequest request){
        Member member = memberService.loginMember(request);
        String token = jwtService.create(member.getEmail());
        return ResponseEntity.ok().header("Authorization", token).body("로그인 되었습니다");
    }

    @PostMapping
    public ResponseEntity<String> join(@Valid @RequestBody JoinMemberRequest request){
        memberService.joinMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("가입 완료");
    }
}
