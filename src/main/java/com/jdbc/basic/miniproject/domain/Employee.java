package com.jdbc.basic.miniproject.domain;

import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    private int empNo; // 사원 번호

    private String empName; // 사원 이름

    private String empRank; // 사원 직급

    private String birthDay; // 사원 생일

    private int salary; // 급여
    
    
    // 사원 급여를 계산해주는 메서드
    public void inputSalary() {
        switch (this.empRank) {
            case "부장":
                this.salary = 4000000;
                return;
            case "과장":
                this.salary = 3000000;
                return;
            case "대리":
                this.salary = 2500000;
                return;
            case "사원":
                this.salary = 2000000;
                return;
        }
    }



}
