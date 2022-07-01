package com.jdbc.basic.miniproject.view;

import com.jdbc.basic.miniproject.controller.EmployeeController;
import com.jdbc.basic.miniproject.controller.ScheduleController;
import com.jdbc.basic.miniproject.domain.Employee;
import com.jdbc.basic.miniproject.domain.Schedule;

import java.util.List;
import java.util.Scanner;

public class ManagementMenu {
    private final Scanner sc;

    private final ScheduleController scheduleController;

    private final EmployeeController employeeController;

    public ManagementMenu() {
        sc = new Scanner(System.in);
        scheduleController = new ScheduleController();
        employeeController = new EmployeeController();
    }


    public void mainMenu(){

        System.out.println("안녕하세요. SL컴퍼니입니다.");
        System.out.println("프로그램을 이용하시려면 로그인해주세요.");

        System.out.println("# 1. 일반 직원으로 로그인");
        System.out.println("# 2. 관리자로 로그인");
        System.out.println("# 9. 프로그램 종료하기");

        int menu = inputNumber("- 메뉴 번호 : ");

        switch (menu) {
            case 1:
                logInMember();
                return;
            case 2:
                logInAdmin();
                return;
            case 9:
                System.out.println("프로그램을 종료합니다.");
                System.exit(0);
                return;
            default:
                System.out.println("잘못된 메뉴 번호입니다.");
        }
    }

    private void logInAdmin() {
        System.out.println("관리자로 로그인합니다.");
        while (true) {
            System.out.println("관리자 ID를 입력하세요.");
            System.out.print(">>> ");
            String id = sc.next();

//            if () 등록된 관리자 id라면 비밀번호 입력받고, 아니면 제대로 입력할 때까지 반복.
            break;
        } // 로그인에 성공하면 while문 탈출 후 managerMainMenu() 실행

        managerMainMenu();
    }


    private void logInMember() {
        System.out.println("직원으로 로그인합니다.");
        while (true) {
            System.out.println("ID를 입력하세요.");
            System.out.print(">>> ");
            String id = sc.next();

//        if () 등록된 id라면 비밀번호 입력받기
//        else 등록되지 않은 id라면 없는 아이디라고 띄우고 회원가입을 할지 다시 입력할지 물어보기.
            break;
        } // 로그인에 성공하면 while문 탈출 후 memberMenu() 실행

        memberMenu();
    }


    private void memberMenu() {
        System.out.printf("환영합니다. %s님!"); // 로그인한 직원 이름 띄우기
        
        while (true) {
            System.out.println("접속 중인 사원 : %s"); // 현재 로그인한 계정 주인명 출력
            System.out.println("# 1. SL컴퍼니 커뮤니티");
            System.out.println("# 2. 사내 일정 정보 조회");
            System.out.println("# 9. 프로그램 종료하기");

            int menu = inputNumber("- 메뉴 번호 : ");

            switch (menu) {
                case 1:
                    // 게시판 화면 출력
//                    community();
                    break;
                case 2:
                    findAllSchedule();
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                    return;
                default:
                    System.out.println("잘못된 메뉴 번호입니다.");
            }
        }
    }


    public void managerMainMenu() {
        System.out.println("\n======================== 회사 관리 프로그램(관리자) ========================");

        System.out.println("어서오세요 관리자님!");

        while (true) {
            System.out.println("# 1. 직원 등록");
            System.out.println("# 2. 직원 정보 조회");
            System.out.println("# 3. 직원 정보 수정");
            System.out.println("# 4. 직원 삭제");
            System.out.println("# 5. 지급 급여 총액 조회");
            System.out.println("# 6. 일정 관리");
            System.out.println("# 9. 끝내기");

            int menu = inputNumber("- 메뉴 입력: ");
            sc.nextLine();

            switch (menu) {
                case 1:
                    // 1. 신규 직원 등록
                    insertEmployee();
                    break;
                case 2:
                    // 2. 직원 정보 조회
                    findEmployee();
                    break;
                case 3:
                    // 3. 직원 정보 수정
                    modifyEmployee();
                    break;
                case 4:
                    // 4. 직원 삭제
                    deleteEmployee();
                    break;
                case 5:
                    // 5. 향후 급여 관리 메뉴로 수정
                    salaryManager();
                    break;
                case 6:
                    // 6. 일정 관리
                    scheduleManager();
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0); // 프로세스 종료
                    break;
                default:
                    System.out.println("잘못된 메뉴 번호입니다.");
            }
        }
    }


    private void salaryManager() {
        while (true){
            // 향후 급여 관리 메뉴로 전환 예정.
            long totalSalary = employeeController.totalSalary();

            System.out.println("직원들에게 지급될 급여의 총액은 " + totalSalary + "원 입니다.");

            return;
        }
    }


    private void scheduleManager() {
        while (true) {
            System.out.println("\n#. 일정 관리를 시작합니다.");
            System.out.println("#1. 신규 일정 등록");
            System.out.println("#2. 전체 일정 조회");
            System.out.println("#3. 기존 일정 수정");
            System.out.println("#4. 기존 일정 삭제");
            System.out.println("#9. 메인으로 돌아가기");

            int menu = inputNumber("- 메뉴 입력 : ");
            sc.nextLine();

            switch (menu) {
                case 1:
                    // 신규 일정 등록
                    insertSchedule();
                    break;
                case 2:
                    // 전체 일정 조회
                    findAllSchedule();
                    break;
                case 3:
                    // 일정 수정
                    modifySchedule();
                    break;
                case 4:
                    // 일정 삭제
                    deleteSchedule();
                    break;
                case 9:
                    System.out.println("#. 메인 메뉴로 돌아갑니다.");
                    return;
                default:
                    System.out.println("잘못된 메뉴번호입니다.");
            }

        }
    }

    private void deleteSchedule() {
        System.out.println("\n#. 등록된 일정을 삭제합니다.");
        int scheduleNo = inputNumber("#. 삭제할 일정 번호를 입력하세요.\n>>> ");
        sc.nextLine();

        Schedule schedule = scheduleController.findOneSchedule(scheduleNo);

        if (schedule != null) {
            boolean result = scheduleController.deleteSchedule(scheduleNo);

            if (result) {
                System.out.println("#. 성공적으로 삭제되었습니다.");
            } else {
                System.out.println("#. 삭제에 실패하였습니다.");
            }
        } else {
            System.out.println("#. 존재하지 않는 일정 번호입니다.");
        }
    }


    private void modifySchedule() {
        while (true) {
            System.out.println("\n#. 등록된 일정을 수정합니다.");
            System.out.println("# 1. 일정 내용 수정");
            System.out.println("# 2. 일정 담당자 수정");
            System.out.println("# 3. 마감일자 수정");
            System.out.println("# 9. 메인으로 돌아가기");
            int menu = inputNumber("- 메뉴 번호 : ");
            sc.nextLine();

            switch (menu) {
                case 1:
                    modifyScheduleName();
                    break;
                case 2:
                    modifyScheduleManager();
                    break;
                case 3:
                    modifyScheduleEndDate();
                    break;
                case 9:
                    System.out.println("#. 메인 메뉴로 돌아갑니다.");
                    return;
                default:
                    System.out.println("잘못된 메뉴 번호입니다.");
            }
        }
    }


    private void modifyScheduleEndDate() {
        System.out.println("\n#. 일정 마감일자를 수정합니다.");
        System.out.println("#. 수정하려는 일정 번호를 입력하세요.");
        int scheduleNo = inputNumber(">>> ");
        sc.nextLine();

        Schedule schedule = scheduleController.findOneSchedule(scheduleNo);

        if (schedule != null) {
            System.out.println("#. 수정할 일정 마감일자를 입력하세요.");
            System.out.print(">>> ");
            String newEndDate = sc.next();

            boolean result = scheduleController.updateScheduleEndDate(scheduleNo, newEndDate);

            if (result) {
                System.out.println("#. 일정 마감일자가 성공적으로 수정되었습니다.");
            } else {
                System.out.println("#. 일정 마감일자 수정에 실패하였습니다.");
            }

        } else {
            System.out.println("#. 존재하지 않는 일정번호입니다.");
        }
    }


    private void modifyScheduleManager() {
        System.out.println("\n#. 일정 담당자 정보를 수정합니다.");
        System.out.println("#. 수정하려는 일정 번호를 입력하세요.");
        int scheduleNo = inputNumber(">>> ");
        sc.nextLine();

        Schedule schedule = scheduleController.findOneSchedule(scheduleNo);

        if (schedule != null) {
            System.out.println("#. 수정할 담당자 직급을 입력하세요.");
            System.out.print(">>> ");
            String managerRank = sc.next();

            System.out.println("#. 수정할 담당자 이름을 입력하세요.");
            System.out.print(">>> ");
            String managerName = sc.next();

            boolean result = scheduleController.updateScheduleManager(scheduleNo, managerName, managerRank);

            if (result) {
                System.out.println("#. 담당자 정보가 성공적으로 수정되었습니다.");
            } else {
                System.out.println("#. 담당자 정보 수정에 실패하였습니다.");
            }

        } else {
            System.out.println("#. 존재하지 않는 일정번호입니다.");
        }
    }


    private void modifyScheduleName() {
        System.out.println("\n#. 일정 내용을 수정합니다.");
        System.out.println("#. 수정하려는 일정 번호를 입력하세요.");
        int scheduleNo = inputNumber(">>> ");
        sc.nextLine();

        Schedule schedule = scheduleController.findOneSchedule(scheduleNo);

        if (schedule != null) {
            System.out.println("#. 수정할 일정 내용을 입력하세요.");
            System.out.print(">>> ");
            String scheduleName = sc.nextLine();

            boolean result = scheduleController.updateScheduleName(scheduleNo, scheduleName);

            if (result) {
                System.out.println("#. 일정 내용이 성공적으로 수정되었습니다.");
            } else {
                System.out.println("#. 일정 내용 수정에 실패하였습니다.");
            }

        } else {
            System.out.println("#. 존재하지 않는 일정번호입니다.");
        }
    }


    private void findAllSchedule() {
        System.out.println("\n#. 등록된 일정을 조회합니다.");

        List<Schedule> scheduleList = scheduleController.findAllSchedule();

        System.out.println("일정번호 || 등록일자 || 일정 내용 || 담당자 직급 || 담당자명 || 마감일자");

        for (Schedule schedule : scheduleList) {
            System.out.println(schedule);
        }
    }


    private void insertSchedule() {
        System.out.println("\n#. 신규 일정을 등록합니다.");
        System.out.println("#. 등록하려는 일정의 내용을 입력해주세요.");
        System.out.print(">>> ");
        String name = sc.nextLine();
        System.out.println("#. 해당 일정의 담당자 정보를 입력해주세요. 아직 정해지지 않았다면 '미정'이라고 입력하세요.");

        System.out.print("#. 일정 담당자 직급 : ");
        String managerRank = sc.next();

        System.out.print("#. 일정 담당자 이름 : ");
        String managerName = sc.next();

        System.out.println("#. 일정의 마감일자를 입력해주세요. [ex: 20220701]");
        System.out.print(">>> ");
        String endDate = sc.next();


        Schedule schedule = new Schedule();
        schedule.setScheduleName(name);
        schedule.setManagerRank(managerRank);
        schedule.setManagerName(managerName);
        schedule.setEndDate(endDate);

        scheduleController.insertSchedule(schedule);

        System.out.println("#. 일정이 정상적으로 등록되었습니다");
    }


    private void deleteEmployee() {
        System.out.println("\n#. 직원 정보를 삭제합니다.");
        int empNo = inputNumber("#. 삭제할 직원의 사번을 입력해주세요.\n>>> ");
        sc.nextLine();

        if (employeeController.hasEmployee(empNo)) {
            boolean result = employeeController.deleteEmployee(empNo);

            if (result) {
                System.out.println("#. 삭제가 완료되었습니다.");
            } else {
                System.out.println("#. 삭제에 실패했습니다.");
            }

        } else {
            System.out.println("#. 존재하지 않는 사번입니다.");
        }

    }


    private void modifyEmployee() {
        while (true) {
            System.out.println("\n#. 직원 정보를 수정합니다.");
            System.out.println("# 1. 이름 수정");
            System.out.println("# 2. 직급 수정");
            System.out.println("# 3. 생년월일 수정");
            System.out.println("# 9. 메인으로 돌아가기");

            int menu = inputNumber("- 메뉴 입력 : ");
            sc.nextLine();
            switch (menu) {
                case 1:
                    modifyEmployeeName();
                    break;
                case 2:
                    modifyEmployeeRank();
                    break;
                case 3:
                    modifyEmployeeBirthDay();
                    break;
                case 9:
                    System.out.println("#. 메인 메뉴로 돌아갑니다.");
                    return;
                default:
                    System.out.println("잘못된 메뉴 번호입니다.");
            }
        }

    }


    private void modifyEmployeeBirthDay() {
        System.out.println("\n#. 직원 이름을 수정합니다.");
        System.out.println("#. 수정할 직원의 사번을 입력하세요.");
        int empNo = inputNumber(">>> ");
        sc.nextLine();

        if (employeeController.hasEmployee(empNo)) {
            System.out.println("#. 변경할 직원의 생년월일을 입력해주세요.\n [ex:19950930]>>> ");
            String birthDay = sc.next();

            boolean result = employeeController.updateEmployeeBirthDay(empNo, birthDay);

            if (result) {
                System.out.println("#. 성공적으로 수정되었습니다.");
            } else {
                System.out.println("#. 수정에 실패하였습니다.");
            }
        } else {
            System.out.println("#. 존재하지 않는 사번입니다.");
        }
    }


    private void modifyEmployeeRank() {
        System.out.println("\n#. 직원 직급을 수정합니다.");
        System.out.println("#. 수정할 직원의 사번을 입력하세요.");
        int empNo = inputNumber(">>> ");
        sc.nextLine();

        if (employeeController.hasEmployee(empNo)) {
            System.out.println("#. 변경할 직원 직급을 입력해주세요.\n [사원, 대리, 과장, 부장]>>> ");
            String rank = sc.next();

            boolean result = employeeController.updateEmployeeRank(empNo, rank);

            if (result) {
                System.out.println("#. 성공적으로 수정되었습니다.");
            } else {
                System.out.println("#. 수정에 실패하였습니다.");
            }
        } else {
            System.out.println("#. 존재하지 않는 사번입니다.");
        }
    }


    private void modifyEmployeeName() {
        System.out.println("\n#. 직원 이름을 수정합니다.");
        System.out.println("#. 수정할 직원의 사번을 입력하세요.");
        int empNo = inputNumber(">>> ");
        sc.nextLine();

        if (employeeController.hasEmployee(empNo)) {
            System.out.println("#. 변경할 직원 이름을 입력해주세요.\n >>> ");
            String name = sc.next();

            boolean result = employeeController.updateEmployeeName(empNo, name);

            if (result) {
                System.out.println("#. 성공적으로 수정되었습니다.");
            } else {
                System.out.println("#. 수정에 실패하였습니다.");
            }
        } else {
            System.out.println("#. 존재하지 않는 사번입니다.");
        }
    }


    private void findEmployee() {
        while (true) {
            System.out.println("\n#. 직원 정보를 조회합니다.");
            System.out.println("# 1. 전체 직원 정보 조회");
            System.out.println("# 2. 개별 직원 정보 조회");
            System.out.println("# 9. 메인으로 돌아가기");

            int menu = inputNumber("- 메뉴 입력 : ");
            sc.nextLine();

            switch (menu) {
                case 1:
                    findEmployeeAll();
                    break;
                case 2:
                    findOneEmployee();
                    break;
                case 9:
                    System.out.println("#. 메인 메뉴로 돌아갑니다.");
                    return;
                default:
                    System.out.println("잘못된 메뉴 번호입니다.");
            }
        }

    }


    private void findOneEmployee() {
        System.out.println("\n#. 조회할 직원의 사번을 입력하세요.");
        int empNo = inputNumber(">>> ");
        sc.nextLine();

        Employee employee = employeeController.findOneEmployee(empNo);

        if (employee != null) {
            System.out.println("\n#. 해당 사번으로 조회된 직원 정보입니다.");
            System.out.println("사번 : " + employee.getEmpNo());
            System.out.println("이름 : " + employee.getEmpName());
            System.out.println("직급 : " + employee.getEmpRank());
            System.out.println("생년월일 : " + employee.getBirthDay());
            System.out.println("기본급 : " + employee.getSalary());
        } else {
            System.out.println("#. 존재하지 않는 사번입니다.");
        }
    }


    private void findEmployeeAll() {
        System.out.println("\n#. 전체 직원 정보를 조회합니다.");

        List<Employee> employeeList = employeeController.findAllEmployee();

        System.out.println("사번 || 이름 || 직급 || 생년월일 || 기본급");
        System.out.println("------------------------------------------------------------");
        for (Employee employee : employeeList) {
            System.out.println(employee.getEmpNo() + " || " + employee.getEmpName() + " || " + employee.getEmpRank() + " || " + employee.getBirthDay() + " || " + employee.getSalary());
        }
    }


    private void insertEmployee() {
        System.out.println("\n#. 새로운 직원을 등록합니다.");

        System.out.print("- 새로운 직원의 이름을 입력해주세요.\n>>>");
        String name = sc.next();

        System.out.print("\n- 새로운 직원의 직급을 입력해주세요.\n[입력 가능 직급 : 사원, 대리, 과장, 부장] >>>");
        String rank = sc.next();

        System.out.print("\n- 새로운 직원의 생년월일을 입력해주세요.\n[ex.19950930] >>>");
        String birthDay = sc.next();


        Employee employee = new Employee();
        employee.setEmpName(name);
        employee.setEmpRank(rank);
        employee.setBirthDay(birthDay);
        employee.inputSalary();

        employeeController.insertEmployee(employee);

        System.out.printf("새로운 직원 %s님이 정상적으로 등록되었습니다.", name);
    }


    

    private int inputNumber(String msg) {
        int n;

        while (true) {
            try {
                System.out.print(msg);
                n = sc.nextInt();
//                sc.nextLine();
                break;
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("# 정수로만 입력하세요");
            }
        }

        return n;
    }


}
