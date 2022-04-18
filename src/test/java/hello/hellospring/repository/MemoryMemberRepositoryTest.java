package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @Test
    public  void save(){
        // Repository Save 메서드는 DB에 값을 저장하는 메서드
        // DB에 값을 저장하고 DB에서 저장한 값이 원래 값과 같은 지 확인한다.
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); // 반환값 type이 optional이기 때문에 여기서 값 가져옴
        assertThat(result).isEqualTo(member); // Assertions은 static으로 변환하여서 사용
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // shift + f6 같은 변수 전체 이름 변경
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member2);
    }
}
