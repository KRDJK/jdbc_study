package com.jdbc.basic.miniproject.domain;


import lombok.*;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {
    private int scheduleNo; // 스케줄 번호
    private String scheduleName; // 스케줄명
    private String startDate; // 등록일자
    private String endDate; // 마감일자
    private String managerName; // 담당자 이름
//    private int managerNo; // 담당자 사원번호
    private String managerRank; // 담당자 직급


    @Override
    public String toString() {
        return String.format("%d || %s || %s || %s || %s || %s",
                scheduleNo, startDate, scheduleName, managerRank, managerName, endDate);
    }
}
