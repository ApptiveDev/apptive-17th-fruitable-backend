package apptive.fruitable.login.dto;

import apptive.fruitable.login.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String pwd;
    private String pwd2; // 비밀번호 재확인
    private String newPwd; // 새 비밀번호
    private String newPwd2; // 새 비밀번호 재확인
    private String name;
    private String newName; // 새 닉네임

    public MemberDto(Long id, String pwd, String name) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
    }

    public static MemberDto toMemberDto(Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(member.getId());
        memberDto.setPwd(member.getPwd());
        memberDto.setPwd2(member.getPwd());
        memberDto.setNewPwd(memberDto.getNewPwd());
        memberDto.setNewPwd2(memberDto.getNewPwd2());
        memberDto.setName(member.getName());
        memberDto.setNewName(memberDto.getNewName());
        return memberDto;
    }
}
