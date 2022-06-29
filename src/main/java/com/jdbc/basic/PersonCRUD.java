package com.jdbc.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonCRUD {
    
    // 내부 클래스 선언
    public static class Person {
        private String ssn; // 주민번호
        private String name; // 이름
        private int age; // 나이

        public Person(String ssn, String name, int age) {
            this.ssn = ssn;
            this.name = name;
            this.age = age;
        }

        public String getSsn() {
            return ssn;
        }

        public void setSsn(String ssn) {
            this.ssn = ssn;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "ssn='" + ssn + '\'' +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    } // end inner class


    // 메서드 선언

    // 사람 객체를 생성해서 반환하는 팩토리메서드 선언
    public static Person makePerson(String ssn, String name, int age) {
        return new Person(ssn, name, age);
    }

    // INSERT 메서드 : DB에 입력해보자
    public static boolean save(Person p) { // p에 들어온 Person 객체를 데이터베이스에 insert 하겠다는 뜻!
        // 1. SQL 구문을 작성
        String sql = "INSERT INTO person (ssn, person_name, age) VALUES (?, ?, ?)"; // 값이 변동하면서 들어갈 부분을 "?"로 처리

        // 2. DB에 접속
        try (Connection conn = Connect.makeConnection()){

            // 3. SQL 실행을 위한 객체 PreparedStatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 4. SQL에  ? 값 채우기
            pstmt.setString(1, p.getSsn()); // 1번 물음표는~~ , p에서 가져와라
            pstmt.setString(2, p.getName());
            pstmt.setInt(3, p.getAge());
            
            // 5. 실행 명령 내리기
            //  - INSERT, UPDATE, DELETE : executeUpdate()
            // - SELECT : executeQuery()
            
            // executeUpdate는 삽입이 실패하면 0을 리턴
            int resultNumber = pstmt.executeUpdate();

            if (resultNumber == 0) return false;
            return true;

//            conn.rollback();

        } catch (Exception e) {
            e.printStackTrace(); // 실패 이유 띄우기
            return false;
        }
    } // end INSERT


    // DELETE 메서드 : DB에 입력해보자
    public static boolean remove(String ssn) { // 주민번호(식별 가능한 pk)로 지운다.
        // 1. SQL 구문을 작성
        String sql = "DELETE FROM person WHERE ssn=?";

        // 2. DB에 접속
        try (Connection conn = Connect.makeConnection()){

            // 3. SQL 실행을 위한 객체 PreparedStatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 4. SQL에  ? 값 채우기
            pstmt.setString(1, ssn);

            // 5. 실행 명령 내리기
            //  - INSERT, UPDATE, DELETE : executeUpdate()
            // - SELECT : executeQuery()

            // executeUpdate는 삭제가 실패하면 0을 리턴
            int resultNumber = pstmt.executeUpdate();

            if (resultNumber == 0) return false;
            return true;

//            conn.rollback();

        } catch (Exception e) {
            e.printStackTrace(); // 실패 이유 띄우기
            return false;
        }
    } // end DELETE


    // UPDATE 메서드
    public static boolean modify(String name, String ssn) {
        // 1. SQL구문을 작성
        String sql = "UPDATE person " +
                "SET person_name = ? " +
                "WHERE ssn = ?";

        // 2. DB에 접속
        try(Connection conn = Connect.makeConnection()) {

            // 3. SQL실행을 위한 객체 PreparedStatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 4. SQL에  ? 값 채우기
            pstmt.setString(1, name);
            pstmt.setString(2, ssn);

            // 5. 실행 명령 내리기
            // - INSERT, UPDATE, DELETE : executeUpdate()
            // - SELECT : executeQuery()

            // executeUpdate는 삭제가 실패하면 0을 리턴
            int resultNumber = pstmt.executeUpdate();

            return resultNumber != 0;

        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    } // end modify


    // SELECT ALL
    public static List<Person> findAll() {

        List<Person> personList = new ArrayList<>();

        // 1. SQL 작성
        String sql = "SELECT * FROM person";

        // 2. DB 연결
        try (Connection conn = Connect.makeConnection()) {

            // 3. SQL 실행을 위한 pstmt 객체 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 4. ?값 채우기 // 전체 조회일 때는 ?를 채울게 없다.
            
            // 5. SQL 실행 - SELECT 실행시 executeQuery()
            ResultSet rs = pstmt.executeQuery();
            // 쿼리 실행 결과로 뱉어낸 조회 결과인 2차원의 표가 ResultSet이다.

            // ResultSet을 순회해서 소비 (2차원 표에서 데이터들을 뽑아오는 것, 뽑아와야 리스트에 담지)
            // a - next() 메서드를 통해 행들을 순서대로 지목
                // 다음 행을 가져올게 있으면 true가 나오고, 없다면 false가 나온다.
            // b - getter 메서드를 통해 열 데이터를 추출.
            while (rs.next()) { // 이러면 표에서 한 줄씩을 가져온다.
                String ssn = rs.getString("ssn");
//                System.out.println("ssn = " + ssn); // 출력이 목적이 아니니까 리스트에 담자.

                String name = rs.getString("person_name");
//                System.out.println("name = " + name);

                int age = rs.getInt("age");
//                System.out.println("age = " + age);

                Person p = makePerson(ssn, name, age);
                personList.add(p);

//                System.out.println("=================================");
            }

            return personList;

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); // null해도 되지만, 빈리스트를 반환하는 것도 좋다.
        }
    }

    
} // end PersonCRUD class
