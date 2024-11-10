package com.zy.mallmember;

import com.zy.mallmember.entity.MemberEntity;
import com.zy.mallmember.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MallMemberApplicationTests {

    @Autowired
    private MemberService memberService;

    @Test
    void contextLoads() {
    }

    @Test
    public void test1(){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUsername("zhaoyu");
        memberEntity.setPassword("123456");

        memberService.save(memberEntity);
        MemberEntity byId = memberService.getById(1);
        System.out.println(byId);

        boolean b = memberService.removeById(1);
        System.out.println(b);
    }

}
