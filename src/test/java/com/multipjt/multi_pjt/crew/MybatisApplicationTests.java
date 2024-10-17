package com.multipjt.multi_pjt.crew;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multipjt.multi_pjt.crew.dao.crew.CrewMapper;
import com.multipjt.multi_pjt.crew.domain.crew.CrewMemberRequestDTO;
import com.multipjt.multi_pjt.crew.domain.crew.CrewRequestDTO;
import com.multipjt.multi_pjt.crew.domain.crew.CrewResponseDTO;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
// @Transactional
public class MybatisApplicationTests {
    
    @Autowired
    private CrewMapper crewMapper;

    @Test
    @DisplayName("001 : 크루 생성 테스트")
    public void createCrewTest(){
        //크루 생성에 필요한 크루 정보
        CrewRequestDTO request = CrewRequestDTO.builder()
                                    .member_id(1)
                                    .crew_name("testCrew3")
                                    .crew_goal("벌크업")
                                    .crew_title("벌크업 목표 크루원 모집")
                                    .crew_location("전주")
                                    .crew_sport("헬스")
                                    .crew_gender("남성")
                                    .crew_frequency("주 6회")
                                    .crew_age("20대")
                                    .crew_date("2024-10-15 17:25:10")
                                    .build();
        //크루 생성
        crewMapper.createCrewRow(request);
        System.out.println("debug >>> 크루 생성 테스트 성공");
    }

    @Test
    @DisplayName("002 : 전체 크루 리스트 조회 테스트")
    public void selectCrewTest(){
        List<CrewResponseDTO> crewList = crewMapper.selectCrewRow();
        for (CrewResponseDTO dto:crewList){
            System.out.println(dto);
        }
        assertFalse(crewList.isEmpty(), "크루가 존재해야 합니다.");
        System.out.println("크루 전체 조회 테스트 성공");
    }

    @Test
    @DisplayName("003 : 특정 크루 리스트 조회 (종목)")
    public void selectCrewSportTest(){
        Map<String, String> map = new HashMap<>();
        map.put("crew_sport", "헬스");

        List<CrewResponseDTO> crewList = crewMapper.selectCrewSportRow(map);
        for (CrewResponseDTO dto : crewList){
            System.out.println(dto);
        }
        assertFalse(crewList.isEmpty(), "크루가 존재해야 합니다.");
        System.out.println("크루 종목 조회 테스트 성공");
    }

    @Test
    @DisplayName("004 : 특정 크루 정보 조회")
    public void selectCrewInfoTest(){
        Map<String, Integer> map = new HashMap<>();
        map.put("crew_id", 2);
        CrewResponseDTO crewInfo = crewMapper.selectCrewInfoRow(map);
        System.out.println(crewInfo);
        assertNotNull(crewInfo, "크루가 존재해야 합니다.");
        System.out.println("크루 정보 조회 테스트 성공");
    }

    @Test
    @DisplayName("005 : 크루원 신청")
    public void insertNewMemberTest(){
        CrewMemberRequestDTO Member1 = CrewMemberRequestDTO.builder()
                                    .crew_id(1)
                                    .member_id(1)
                                    .crew_member_state(0)
                                    .battle_wins(0)
                                    .build();
        crewMapper.insertCrewMember(Member1);
        System.out.println("크루원 신청 테스트 성공");
    }

}
