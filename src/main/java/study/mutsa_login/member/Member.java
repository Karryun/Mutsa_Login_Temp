package study.mutsa_login.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import study.mutsa_login.BaseEntity;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Column(length = 16, nullable = false, unique = true)
    private String nickname;

    @Column(length = 64, nullable = false, unique = true)
    private String email;

    @Column(length = 20, nullable = false, unique = true)
    private String phonenumber;

    @Column(length = 64, nullable = false)
    private String password;
}

