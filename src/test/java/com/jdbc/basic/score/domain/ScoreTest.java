package com.jdbc.basic.score.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    @Test
    void lombokTest() {
        Score s = new Score(1, "김철수", 99, 88, 11, 220, 22); // 매개변수 예시 보기 = 컨트롤 + p
        s.setTotal(222);

        System.out.println(s.getStuName());

//        assert
    }

    @Test
    void lombokBuilderTest() {

//        new Score(3, "김철수", 100, 90, 190, 95); 국어 성적이 필수값이 아니라 안 넣고 싶을 때!! 안넣고 쓰면 생성자가 그렇게 만들어진게 없어서 안된다.

        // 그럴땐 이 빌더를 사용하면 좋다. 내가 원하는 파라미터만 넣는게 가능하다
        Score park = new Score.ScoreBuilder()
                .stuNum(2)
                .stuName("박영희")
                .math(92)
                .eng(100)
//                .total(192)
//                .average(96)
                .build();
            // 생성자를 넣지 않고도 원하는 객체를 생성할 수 있다.

        System.out.println(park);
    }

}