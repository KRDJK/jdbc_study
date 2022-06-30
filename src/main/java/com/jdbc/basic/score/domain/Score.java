package com.jdbc.basic.score.domain;

import lombok.*;

// 롬복 사용법 : 클래스 위에다가 써야 함.
@Setter @Getter
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드 초기화 생성자 // 기본이나 모든 필드 초기화 생성자가 아닌 경우는 직접 만들어야 한다.
@ToString
@Builder // 객체 생성시 생성자 역할을 대신한다.
// 데이터베이스 score 테이블의 행 데이터를 저장할 객체 (한 행 마다 저장할 것이다.)

//@Data // 이러면 위에 @ 들을 다 만들어주지만 쓰지 말도록 해라!! 하나하나 다 써라!! 매우 위험함!
public class Score {

    // 실무는 보통 long으로 잡는다. 주문 이력 테이블의 시퀀스 번호같은 경우는 일주일이면 23억을 넘는다.
    private int stuNum;
    private String stuName;
    private int kor;
    private int eng;
    private int math;
    private int total;
    private double average;

    // 아무데나 커서 놓고 컨트롤 쉬프트 T로 테스트 생성

    // 총점, 평균을 계산하는 메서드
    public void calc() {
        this.total = kor + eng + math;
        this.average = Math.round((total / 3.0) * 100) / 100.0;
    }

    // 1. 성적 저장 기능
    // 2. 전체 학생 성적 조회 기능
    // 3. 개별 학생 성적 조회 기능
    // 4. 성적 수정 기능
    // 5. 성적 정보 삭제 기능



    
}
