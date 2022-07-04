package com.jdbc.basic.score.controller;

import com.jdbc.basic.score.domain.Score;
import com.jdbc.basic.score.repository.ScoreOracleRepo;
import com.jdbc.basic.score.repository.ScoreRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 데이터들을 전처리 후처리하고 메모리에 저장, 관리하는 클래스
public class ScoreController {

    // 성적 정보가 저장될 맵 ( key : 학번, value : 성적 정보 )
    private static Map<Integer, Score> scoreMap; // 리스트를 쓰지 않는 이유는 중간에 전학가면 인덱스가 땡겨지는데 출석번호가 땡겨지는게 말이 돼?

    // ScoreRepository에 의존성 관계를 가진다. (DIP 원칙 : 의존관계 역전 원칙)
    private final ScoreRepository repository; // repository가 없으면 일을 못하기 때문에 컨트롤러는 얘한테 의존한다고 할 수 있다.
                                // 추상적(인터페이스) 존재에 의존을 해야한다. 다형성을 적용하기 위해서!! 누구라도 대체할 수 있게!
                                // 특정 직원에게 의존하는 것이 아닌 직원 역할 자체에 의존하는 것.

    static {
        scoreMap = new HashMap<>();
    }

    public ScoreController() { // 완벽한 DIP 원칙을 적용한다면 이 부분 조차도 향후 변동이 없어야 한다.
        this.repository = new ScoreOracleRepo(); // 레퍼지토리는 DB 관련 명령을 수행할 직원 같은 개념이다.
    }


    // 학생 성적 정보 입력 기능
    public void insertStudent(Score score) {

        scoreMap.put(score.getStuNum(), score); // 이렇게만 하면 메모리에만 저장되어 프로그램 재실행시 기록이 남지 않음.

        // DB에 저장 명령 - 명령만 내리고 실제 수행은 다른 클래스에서 해라. repository 패키지 소속 클래스에서 수행!!
        boolean result = repository.save(score); // 결과만 알려주니 캡슐화가 된 것이다. 수행은 자기가 알아서!
    }



    // 성적 전체 조회
    public List<Score> findAllStudent() {
        Map<Integer, Score> students = repository.findAll();
        scoreMap = students;

        List<Score> scoreList = new ArrayList<>();
        for (Integer stuNum : scoreMap.keySet()) {
            scoreList.add(scoreMap.get(stuNum));
        }
        return scoreList;
    }



    // 개인 성적 조회
    public Score findOneStudent(int stuNum) {
        Score score = repository.findOne(stuNum);

        return score;
    }



    // 성적 수정
    public boolean updateStudent(int stuNum, int kor, int eng, int math) {
        // 1. DB에서 해당 학생을 조회한다.
        Score target = findOneStudent(stuNum);

        if (target != null) { // 제대로 존재하는 학번을 입력했다면!!
            // 2. 수정 진행
            target.setKor(kor);
            target.setEng(eng);
            target.setMath(math);
            target.calc(); // 바뀐대로 갱신되어야 하니까!
            
            // 3. DB에 수정 반영
            return repository.modify(target);
        }

        return false;
    }


    
    // 성적 삭제
    public boolean deleteStudent(int stuNum) {
        return repository.remove(stuNum);
    }



    // 학번으로 조회했을 때 학생 존재 유무를 리턴하는 메서드
    public boolean hasScore(int stuNum) {
        return repository.findOne(stuNum) != null;
    }




    // 반 평균을 구하는 메서드
    public double calcClassAverage() {
        // case 1: 앱 내부에서 구한다.
        /*double avgSum = 0.0;
        for (Integer stuNum : scoreMap.keySet()) {
            avgSum += scoreMap.get(stuNum).getAverage();
        }
        return avgSum / scoreMap.size();*/


        // case 2: DB에서 전체평균을 구해서 가져온다.
        return repository.getClassAverage();
    }

}
