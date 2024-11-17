package com.zy.mallmember;

import com.zy.mallmember.dto.MemberDTO;
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
        MemberDTO memberEntity = new MemberDTO();
        memberEntity.setUsername("zhaoyu");
        memberEntity.setPassword("123456");

        memberService.save(memberEntity);
        MemberDTO byId = memberService.get(1L);
        System.out.println(byId);
    }

}
