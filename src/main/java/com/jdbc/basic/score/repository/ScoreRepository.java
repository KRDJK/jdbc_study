package com.jdbc.basic.score.repository;

import com.jdbc.basic.score.domain.Score;

import java.util.Map;

public interface ScoreRepository {

    // 성적 정보 저장
    boolean save(Score score);

    // 성적 정보 삭제
    boolean remove(int stuNum); // pk인 학번을 받아서 해당 학번 학생 삭제

    // 성적 정보 수정
    boolean modify(Score score); // Score 객체 안에 수정할 데이터들이 담겨있어야 함.
    
    // 전체 성적 조회
    Map<Integer, Score> findAll();
    
    // 개별 성적 조회
    Score findOne(int stuNum); // pk인 학번으로 해당 학생 성적 조회

    // 반 전체 평균 조회
    double getClassAverage();
}
