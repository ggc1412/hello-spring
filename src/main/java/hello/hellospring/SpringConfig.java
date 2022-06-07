package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    // @Configuration 어노테이션을 달아주면 스프링이 올라갈 때 해당 클래스에서 설정을 읽어온다.
    // @Bean으로 되어있는 부분을 확인하여 스프링 컨테이너에 Bean으로 등록한다.
    // 레거시 프로젝트들은 XML로 설정을 하였으나 최근에는 거의 사용하지 않는다.

//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    // 생성자가 하나일 경우에는 @Autowired를 달아주지 않아도 된다.
    // 1. 스프링 컨테이너가 MemberRepository 구현체를 찾는다.
    // 2. 스프링 data jpa가 구현한 구현체가 빈 컨테이너에 등록되어 있다.
    // 3. 스프링 컨테이너가 그 구현체를 가져다 memberRepository에 주입힌다.
    // 4. 이것만으로 memberRepository가 구현된다.


    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

    @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }
    // @Component로 빈에 등록할 수도 있지만, 일반적인 빈 객체가 아니기 때문에 등록하여 사용함을 명시적으로 보여주는 것이 좋다.

//    @Bean
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
        // 객체지향적인 설계의 예이다.
        // 다형성 활용의 예시로 인터페이스를 두고 구현체를 바꿔끼우기 한다.
        // 기존 코드는 건들지 않고 설정 파일만 바꿈으로 의존 관계를 바꾸고 있다.
        // (memory리포지토리 -> jdbc리포지토리 의존)
        // 스프링은 스프링 컨테이너에서 빈을 관리하여 DI 관리를 알아서 해주고 있다.
        // 이를 통해 다형성 활용을 지원해주고 있다.
//    }

 /*   @Bean
    public MemberController memberController(){
        return new MemberController(memberService());
    }*/
}
