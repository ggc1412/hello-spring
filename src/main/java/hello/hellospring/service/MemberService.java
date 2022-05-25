package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class MemberService {
    // Repository와 Domain을 활용하여 실제 비즈니스 로직을 작성
    // Repository는 CRUD에 충실한 네이밍
    // 비즈니스 로직은 실제 비즈니스 도메인에 맞는 용어 사용. 비즈니스에 의존적으로 설계
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // MemberService를 호출하여 사용하는 곳에서 쓰는 인스턴스와 동일한 인스턴스를 사용하도록 하기 위해 DI를 한다.
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member){
        validateDuplicateMember(member); // 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // 메서드가 길게 늘어지므로 따로 빼서 매서드를 만드는 것이 좋다.
        memberRepository.findByName(member.getName())
            .ifPresent(m ->{
                // Optional 안에 Member 객체가 있음
                // 그래서 Optional이 제공하는 여러가지 메서드를 사용할 수 있다.
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
