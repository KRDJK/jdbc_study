package com.jdbc.basic;

import java.sql.Connection;
import java.sql.DriverManager;

// Oracle DB 연결
public class Connect {

    // 앱에서 db를 연결할 때
    // 데이터베이스 연결을 위한 정보 저장
    private final static String ACCOUNT = "sqld"; // 들어갈 계정명 입력
    private final static String PASSWORD = "1234"; // 비밀번호

    // 2. 데이터 베이스의 위치정보 (DB URL) 어디 컴퓨터에 어디 포트에 위치해있는지 -- DB 회사마다 패턴이 다르다. (웹에서의 HTTP:~~ 와는 다르다.)
    private final static String URL = "jdbc:oracle:thin:@localhost:1521:xe"; // 오라클의 DB URL 패턴을 알아야 함.

    // localhost 또는 127.0.0.1
    // jdbc:oracle:driver_type:[username/password]@//host_name:port_number:SID

    // 3. 데이터베이스 접속에 쓸 드라이버 클래스
    private final static String DRIVER = "oracle.jdbc.driver.OracleDriver"; // 이것도 DB 회사마다 다르다.


    // DB 연결 메서드 ==>> 이 메서드를 확인해보고 싶으면 컨트롤 + 쉬프트 + T를 하고 새 테스트 생성 후 그냥 엔터해보자
    public static Connection makeConnection() {

            // 메모리 누수를 막기 위한 close를 실행이 끝나고 나면 자동으로 해주는 try with resources 구문
        try {
            // 1. 드라이버 클래스를 동적 로딩
            Class.forName(DRIVER);

            // 2. 연결정보를 담아 연결 객체를 생성
            Connection conn = DriverManager.getConnection(URL, ACCOUNT, PASSWORD);
            return conn;
        } catch (Exception e) {
            e.printStackTrace(); // 실패 원인을 찍어줌.
            return null;
        }


    }


} // end class
