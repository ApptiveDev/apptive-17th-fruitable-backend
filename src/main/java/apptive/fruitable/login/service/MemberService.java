package apptive.fruitable.login.service;

import apptive.fruitable.login.dto.MemberDto;
import apptive.fruitable.login.entity.Member;
import apptive.fruitable.login.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long save(MemberDto memberDto) {
        Member member;
        member = Member.toSaveEntity(memberDto);
        Long savedId = memberRepository.save(member).getId();
        return savedId;
    }

    public MemberDto login(String pwd) {
        /**
         * login.html에서 아이디, 비번을 받아오고
         * DB로 부터 해당 아이디의 정보를 가져와서
         * 입력받은 비번과 DB에서 조회환 비번의 일치여부를 판단하여
         * 일치하면 로그인 성공, 일치하지 않으면 로그인 실패로 처리
         */

        return null;
    }

    public MemberDto findById(Long id) {
        Optional<Member> optionalMemberEntity = memberRepository.findById(id);
        if (optionalMemberEntity.isPresent()) {
            Member member = optionalMemberEntity.get();
            MemberDto memberDto = MemberDto.toMemberDto(member);
            return memberDto;
        } else {
            return null;
        }
    }

    public boolean nameDuplicate(String name) {
        return memberRepository.existsByName(name);
    }

    public void updateName(MemberDto memberDto) {
        memberRepository.save(Member.toUpdateEntityName(memberDto));
    }

    public void updatePwd(MemberDto memberDto) {
        memberRepository.save(Member.toUpdateEntityPwd(memberDto));
    }
}
