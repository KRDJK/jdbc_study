package com.jdbc.basic.miniproject.repository;

import com.jdbc.basic.miniproject.domain.Member;

public interface LogInRepository {

    // 회원 가입 후 DB에 저장
    boolean saveMember(Member member);


    // 로그인 시도 및 회원가입 시에 해당 id가 존재하는 회원 id인지 찾아주는 메서드
    boolean existID(String ID);


    // 비밀번호가 일치하는지 확인해주는 메서드
    boolean matchesPW(String PW);

}
