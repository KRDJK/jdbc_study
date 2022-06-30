package com.jdbc.basic.miniproject.repository;

import com.jdbc.basic.Connect;
import com.jdbc.basic.miniproject.domain.Schedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ScheduleOracleRepo implements ScheduleRepository {
    @Override
    public boolean save(Schedule schedule) {
        String sql = "INSERT INTO schedule (schedule_no, schedule_nm, end_date, manager_nm, manager_rank) VALUES (seq_schedule.nextval, ?, ?, ?, ?)";

        try (Connection conn = Connect.makeConnection()) {
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, schedule.getScheduleName());
            pstmt.setString(2, schedule.getEndDate());
            pstmt.setString(3, schedule.getManagerName());
            pstmt.setString(4, schedule.getManagerRank());


            int result = pstmt.executeUpdate();

            if (result != 0) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Map<Integer, Schedule> findAllSchedule() {
        Map<Integer, Schedule> scheduleMap = new HashMap<>();

        String sql = "SELECT * FROM schedule ORDER BY schedule_no";

        try (Connection conn = Connect.makeConnection()) {
            // 트랜잭션 처리
            conn.setAutoCommit(false); // jdbc는 자동으로 커밋을 해버림. 그걸 방지하기 위해 자동커밋 설정 끈 것.

            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) { // 한줄 가져오고
                Schedule schedule = new Schedule(
                        rs.getInt("schedule_no")
                        , rs.getString("schedule_nm")
                        , rs.getString("start_date")
                        , rs.getString("end_date")
                        , rs.getString("manager_nm")
                        , rs.getString("manager_rank")
                );

                scheduleMap.put(schedule.getScheduleNo(), schedule);
            }

            return scheduleMap;

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }

    @Override
    public boolean modify(Schedule schedule) {
        String sql = "UPDATE schedule SET schedule_nm = ?, end_date = ?, manager_nm = ?, manager_rank = ? WHERE schedule_no = ?";

        try (Connection conn = Connect.makeConnection()) {
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, schedule.getScheduleName());
            pstmt.setString(2, schedule.getEndDate());
            pstmt.setString(3, schedule.getManagerName());
            pstmt.setString(4, schedule.getManagerRank());
            pstmt.setInt(5, schedule.getScheduleNo());

            int result = pstmt.executeUpdate();

            if (result != 0) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean remove(int scheduleNo) {
        String sql = "DELETE FROM employee WHERE emp_no = ?";

        try (Connection conn = Connect.makeConnection()) {
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, scheduleNo);

            int result = pstmt.executeUpdate();

            if (result != 0) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
