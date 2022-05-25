package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
public class MemberController {
    // Spring Container에 어노테이션이 붙은 애들을 Bean으로 만들어서 넣어놓고 스프링이 관리를 한다.
    // new 생성지로 생성을 해서 사용을 하면 사용할 때마다 생성하게 되어 결국 여러개를 생성하게 된다.
    // 하지만 Bean으로 등록된 것을 사용하면 하나를 가지고 여러군데에서 사용할 수 있다.(싱글톤 방식)
    // Autowired로 등록된 Bean을 연결해서 사용하며, @Controller, @Service, @Repository로 각각 Bean으로 등록한다.(컴포넌트 스캔)
    // 등록할 때 인스턴스 생성과 같이 생성자를 같이 호출한다.

//    @Autowired MemberService memberService;
    // 이렇게 주입하는 것을 필드주입이라고 한다.
    // 필드주입은 등록만 가능하고 이에 대해 어떤 조작도 불가능 하기 때문에 권장되지 않는다.


    //    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }
    // 위와 같은 방식을 생성자 주입이라고 한다.
    // Controller가 생성된 이후 setter를 호출하여 memberService가 setting이 된다.
    // 때문에 이 경우에는 memberService에 final을 못 붙인다.
    // 예전에는 많이 사용하였으나 이와 같은 방식은 어디서든 setter를 호출하여 값을 변경 가능하다는 단점이 있기 때문에 요즘에는 권장하지 않는다.
    // 의존관계는 처음 조립될 때 생성되고 그 이후에는 변경될 일이 거의 없으므로 생성자 주입을 권장한다.
    private final MemberService memberService;

    @Autowired
    // 생성자 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String creatForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
