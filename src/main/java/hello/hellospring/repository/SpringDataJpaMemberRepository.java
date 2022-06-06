package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    // 스프링 데이터 JPA가 인터페이스만 있으면 알아서 구현체를 만들어서 스프링 빈으로 만들어서 등록해준다.

    @Override
    Optional<Member> findByName(String Name);
    // findByNameAndId 처럼 메서드 이름만으로 쿼리를 알아서 짜준다.
    // JapRepository 인터페이스에서 기본적인 CRUD 쿼리를 제공한다.
}
