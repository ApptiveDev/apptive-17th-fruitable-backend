package apptive.fruitable.login.entity;

import apptive.fruitable.base.domain.BaseEntity;
import apptive.fruitable.login.dto.MemberDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "member")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 회원 고유번호

    // 글자수 8~20
    @Column(nullable = false)
    @Size(min = 8, max = 20)
    private String pwd;

    // 닉네임 중복허용 X, 글자수 2~10
    @Column(unique = true, nullable = false)
    @Size(min = 2, max = 10)
    private String name;

    public static Member toSaveEntity(MemberDto memberDto) {
        Member member = new Member();
        member.setPwd(memberDto.getPwd());
        member.setName(memberDto.getName());
        return member;
    }

    public static Member toUpdateEntityName(MemberDto memberDto) {
        Member member = new Member();
        member.setId(memberDto.getId());
        member.setPwd(memberDto.getPwd());
        member.setName(memberDto.getName());
        return member;
    }

    public static Member toUpdateEntityPwd(MemberDto memberDto) {
        Member member = new Member();
        member.setId(memberDto.getId());
        member.setPwd(memberDto.getPwd());
        member.setName(memberDto.getName());
        return member;
    }
}