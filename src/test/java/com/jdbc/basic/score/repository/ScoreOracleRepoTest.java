package com.jdbc.basic.score.repository;

import com.jdbc.basic.score.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ScoreOracleRepoTest {

    ScoreRepository repository = new ScoreOracleRepo();

    @Test
    @DisplayName("성적 정보를 DB에 삽입해야 한다.")
    void insertTest() {

        Score kim = new Score();
        kim.setStuName("김테스트");
        kim.setKor(58);
        kim.setEng(77);
        kim.setMath(99);
        kim.calc();


        boolean result = repository.save(kim);
        assertTrue(result);
    }

    @Test
    @DisplayName("전체 성적 정보를 조회해야 한다")
    void findAllTest() {

        Map<Integer, Score> scoreMap = repository.findAll();

        for (Integer stuNum : scoreMap.keySet()) {
            System.out.println(scoreMap.get(stuNum));
        }

        assertEquals(4, scoreMap.size()); // 테스트가 성공하면 사이즈가 4였던거고 실패면 4가 아니었던 거임.
    }

    @Test
    @DisplayName("학번을 주면 해당 학번을 가진 학생이 삭제되어야 한다")
    void removeTest() {

        // given
        int stuNum = 5; // 제거할 삭제 대상 학번

        // when
        boolean result = repository.remove(stuNum);

        // then
//        assertTrue(result);
        Score score = repository.findOne(stuNum);
        assertNull(score); // 삭제가 됐으니 찾을 때 못찾고 null이 나올거라고 예상한다.

        // 실무에서라면 롤백도 해야함. 지우는걸 테스트해보려고 했지 실제로 지우려고 한건 아니잖아..
    }

    @Test
    @DisplayName("학번과 바꿀 정보들을 주변 해당 학번을 가진 학생의 데이터가 바뀌어야 한다")
    void modifyTest() {

        // given : 테스트를 위해서 주어진 데이터
//        Score second = new Score();
        Score second = repository.findOne(2);
        second.setKor(80);
        second.setMath(80);
        second.setEng(80);
        second.calc();
//        second.setTotal(second.getEng() + second.getKor() + second.getMath());
//        second.setAverage(Math.round(second.getTotal() / 3.0 * 100) / 100.0);


        // when : 테스트할 상황
        boolean result = repository.modify(second);


        // then: 테스트 후 예상되는 결과
//        assertTrue(result);
        Score newScore = repository.findOne(2);
        assertEquals(80, newScore.getMath()); // 바뀐 수학 점수가 80점일거라고 단언한다.

    }
}