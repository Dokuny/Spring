package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
 // 데이터 변경은 트랜잭션 안에서 이루어져야 한다. public 메소드들이 트랜잭션에 걸린다.
@Transactional(readOnly = true)  // 전체적으로 읽기전용으로 건다.
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원가입
    @Transactional // 얘는 읽기전용 X
    public Long join(Member member) {

        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // Exception
        List<Member> findMembers = memberRepository.findByName(member.getName());
        // 이렇게 제한을 걸더라도 만에하나 동시에 들어온다면 저장될 수 있기에 이러한 경우 데이터베이스에서 컬럼을 유니크 제약조건으로 걸어줘야한다.
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    // 회원 전체 조회
    // @Transactional(readOnly = true)   데이터를 단순히 읽는 곳에선 readOnly 해놓으면 성능 최적화가 된다.
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    // 회원 한명 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

//    회원수정
    @Transactional
    public void update(Long id, String name, Address address) {
        Member member = memberRepository.findOne(id);
        member.setAddress(address);
        member.setName(name);
    }
}
