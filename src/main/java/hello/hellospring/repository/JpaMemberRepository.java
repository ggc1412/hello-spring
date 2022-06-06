package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;
    // JPA는 EntitiyManager가 모두 관리한다.
    // data-jpa 라이브러리를 다운받으면 스프링 부트가 자동으로 EntityManager를 생성하고
    // EntityManager는 application.properties에 설정된 정보를 가지고 DB를 관리한다.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // JPA는 모든 데이터 변경이 transactional 안에서 실행되어야 한다.
        // 따라서 데이터 변경 메서드를 호출하는 부분에 transactional 어노테이션을 달아준다.
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        // key값으로 찾을 때는 쿼리를 작성할 필요가 없다.
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        // key 값으로 찾는 경우가 아닐 때는 JPQL(객체지향 쿼리) 을 작성해줘야 한다.
        // JPA를 스프링으로 한번 더 감싸서 제공하는 스프링 데이터 JPA 기술은 JPQL도 작성할 필요가 없다.
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
