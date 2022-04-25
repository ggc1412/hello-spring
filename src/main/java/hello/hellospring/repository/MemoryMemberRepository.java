package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        // findAny는 하나라도 해당 값이 있으면 찾아서 반환하고 루프를 종료한다. 하나도 없다면 반환값 타입이 Optional이기 때문에 Null 값이 Optional로 감싸져서 반환된다.
    }

    @Override
    public List<Member> findAll() {
        // 반환 타입이 List이다. 실무에서는 List를 루프 돌리기에도 편하고 여러 이유로 List를 자주 쓴다.
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
