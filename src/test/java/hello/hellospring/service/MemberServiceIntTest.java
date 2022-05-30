package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
// 스프링 컨테이너와 테스트를 함께 실행한다.
@Transactional
// TEST 케이스의 경우, @Transactional이 붙어있으면 TEST 케이스 시작 전에 트랜젝션을 실행하고 매번 원래 상태로 Roll Back 한다.
class MemberServiceIntTest {
    // 스프링 컨테이너로 DB까지 다 연동하여 테스트 하는 것을 통합 테스트라고 하고
    // 순수 Java코드 작성하여 최소한의 테스트를 하는 것을 단위 테스트라고 한다.
    // 일반적으로 순수한 단위 테스트가 훨씬 좋은 테스트일 가능성이 높다.
    // 단위별로 잘 잘라서 테스트를 설계하는 것이 중요하다.

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
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

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}