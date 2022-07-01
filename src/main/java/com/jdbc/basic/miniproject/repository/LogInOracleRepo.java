package com.jdbc.basic.miniproject.repository;

import com.jdbc.basic.miniproject.domain.Member;

public class LogInOracleRepo implements LogInRepository {

    @Override
    public boolean saveMember(Member member) {
        return false;
    }

    @Override
    public boolean existID(String ID) {
        return false;
    }

    @Override
    public boolean matchesPW(String PW) {
        return false;
    }

}
