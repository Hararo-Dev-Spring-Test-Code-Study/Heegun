// StudyCafeSeatPasses 클래스 findPassBy() 메서드 테스트 코드
// 테스트 목적 : 주어진 좌석 리스트에서 특정 타입의 좌석만 정확히 필터링하는지 검증

package cleancode.studycafe.tobe.model.pass;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudyCafeSeatPassesTest {

    private StudyCafeSeatPass fixedPass1; // FIXED 타입 좌석 1
    private StudyCafeSeatPass fixedPass2; // FIXED 타입 좌석 2
    private StudyCafeSeatPass hourlyPass; // HOURLY 타입 좌석

    private StudyCafeSeatPasses seatPasses; // 좌석 리스트 객체

    @BeforeEach
    void setUp() {
        // given
        // FIXED 타입 객체 2 개, HOURLY 객체 1 개
        // WEEKLY 타입 객체는 존재하지 않는 타입 필터링 기능에 사용하기 위해 추가하지 않음
        fixedPass1 = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 30, 100000, 0.1);
        fixedPass2 = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 60, 180000, 0.15);
        hourlyPass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 10, 10000, 0.0);

        // 여러 타입의 객체가 담긴 리스트
        List<StudyCafeSeatPass> allPasses = List.of(fixedPass1, fixedPass2, hourlyPass);
        // allPasses 를 좌석 이용권 리스트로 가지고 있는 객체
        seatPasses = StudyCafeSeatPasses.of(allPasses);
    }

    @Test
    @DisplayName("특정 타입(FIXED)의 좌석만 정확히 필터링되는지 확인")
    // 여러 타입이 섞인 리스트에서 FIXED 타입 좌석만 반환하는지 확인
    void filterOnlyFixedType() {
        // when
        List<StudyCafeSeatPass> result = seatPasses.findPassBy(StudyCafePassType.FIXED);

        // then
        assertEquals(2, result.size()); // FIXED 타입 좌석은 2개
        assertTrue(result.contains(fixedPass1)); // fixedPass1 를 포함하는지 확인
        assertTrue(result.contains(fixedPass2)); // fixedPass2 를 포함하는지 확인
    }

    @Test
    @DisplayName("존재하지 않는 타입을 필터링할 경우 빈 리스트가 반환되는지 확인")
    // 타입에 해당하는 좌석이 없을 경우 빈 리스트가 반환되는지 검증
    void returnEmptyListWhenNoMatchingType() {
        // when
        List<StudyCafeSeatPass> result = seatPasses.findPassBy(StudyCafePassType.valueOf("WEEKLY"));

        // then
        assertTrue(result.isEmpty()); // 빈 리스트인지 확인
    }
}
