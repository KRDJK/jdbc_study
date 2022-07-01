package com.jdbc.basic.miniproject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private String ID; // 회원 ID
    private String PW; // 비밀번호
    private int empNo; // 사번

}
