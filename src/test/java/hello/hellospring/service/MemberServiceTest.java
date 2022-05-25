package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class MemberServiceTest {

//    MemberService memberService = new MemberService();
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
//    // Test가 아닌 MemberService에서도 new로 Repository를 새로 생성하고 있다.
//    // Test에서 서로 별도의 인스턴스를 생성하여 사용하는 것이 바람직하지 않다.
//    // 따라서 memberService를 불러올 때 같은 것을 사용할 수 있도록 repository를 넣어준다. => THIS IS DI!!

    @Autowired
    MemberService memberService;
    @Autowired
    MemoryMemberRepository memberRepository;

//    @BeforeEach
//    public void beforeEach(){
//        memberRepository = new MemoryMemberRepository();
//    }


    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() { // 빌드될 때 TEST 코드는 실제 코드에 포함되지 않기 때문에 한글로도 많이 적는다.
        // Given
        Member member = new Member();
        member.setName("hello");

        // When
        Long saveId = memberService.join(member);

        // Then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외(){ // Test는 예외 상황 처리가 더 중요하다.
        // Givien
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // When
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


//        try{
//            memberService.join(member2);
//            fail();
//        }catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        // Then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}