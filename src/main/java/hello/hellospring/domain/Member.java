package hello.hellospring.domain;

import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 쿼리에 값을 넣으면 DB가 아이디를 자동으로 생성해주는 전략을 Identity 전략이라고 한다.
    private Long id;

//    @Column(name = "username ")
    // 컬럼명이 username인 경우 column 어노테이션을 달고 이름을 지정해줄 수 있다.
    // 어노테이션들을
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
