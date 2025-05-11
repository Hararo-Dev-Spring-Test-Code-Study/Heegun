// StudyCafePassOrder 클래스 getTotalPrice() 메서드 테스트 코드
// 테스트 목적 : 총 가격 계산 로직 검증
package cleancode.studycafe.tobe.model.order;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudyCafePassOrderTest {

    private StudyCafeSeatPass seatPassWithDiscount; // 사용자가 선택한 좌석 이용권 객체
    private StudyCafeLockerPass lockerPass; // 사용자가 선택한 사물함 이용권 객체

    @BeforeEach
    void setUp() {
        // given
        seatPassWithDiscount = StudyCafeSeatPass.of(
            StudyCafePassType.FIXED, // 라커 사용 가능한 타입으로 생성
            30,
            100000,
            0.2
        );

        lockerPass = StudyCafeLockerPass.of(
            StudyCafePassType.FIXED,
            30,
            20000
        );
    }

    @Test
    @DisplayName("사물함이 포함된 경우 총 가격이 정확히 계산되는지 확인")
    // 좌석 가격 + 사물함 가격 - 할인금액 공식을 따라 총 가격이 정확히 계산되는지 확인
    void calculateTotalPriceWithLocker() {
        // when
        StudyCafePassOrder order = StudyCafePassOrder.of(seatPassWithDiscount, lockerPass); // 좌석 + 사물함 결합 주문 객체
        int result = order.getTotalPrice();

        // then
        int expectedDiscount = 20000; // 100000 * 0.2
        int expectedTotal = (100000 + 20000) - expectedDiscount; // 직접 계산

        assertEquals(expectedTotal, result); // 직접 계산한 결과와 실제 값과 같은지 확인
    }

    @Test
    @DisplayName("사물함이 없는 경우 총 가격이 정확히 계산되는지 확인")
    // 사물함이 없을 때 좌석 가격 - 할인금액만 계산되는지 확인
    void calculateTotalPriceWithoutLocker() {
        // when
        StudyCafePassOrder order = StudyCafePassOrder.of(seatPassWithDiscount, null); // 사물함이 없는 주문 객체
        int result = order.getTotalPrice();

        // then
        int expectedDiscount = 20000; // 100000 * 0.2
        int expectedTotal = 100000 - expectedDiscount; // 직접 계산

        assertEquals(expectedTotal, result); // 직접 계산한 결과와 실제 값과 같은지 확인
    }
}
