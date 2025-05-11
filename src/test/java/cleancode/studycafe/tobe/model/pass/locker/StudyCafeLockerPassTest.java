// StudyCafeLockerPass 클래스 isSamePassType() 메서드 테스트 코드
// 테스트 목적 : 사물함 이용권의 타입이 주어진 타입과 일치하는지 검증

package cleancode.studycafe.tobe.model.pass.locker;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudyCafeLockerPassTest {

    private StudyCafeLockerPass lockerPass; // 사물함 이용권 객체

    @BeforeEach
    void setUp() {
        // given
        lockerPass = StudyCafeLockerPass.of(
            StudyCafePassType.FIXED, // FIXED 타입으로 설정
            30,
            20000
        );
    }

    @Test
    @DisplayName("타입이 동일한 경우 true를 반환해야 한다")
    // 사물함의 타입이 주어진 타입과 동일할 경우 true 반환
    void returnTrueWhenTypeMatches() {
        // when
        boolean result = lockerPass.isSamePassType(StudyCafePassType.FIXED);

        // then
        assertTrue(result); // true 인지 확인
    }

    @Test
    @DisplayName("타입이 다른 경우 false를 반환해야 한다")
    // 사물함의 타입이 주어진 타입과 다를 경우 false 반환
    void returnFalseWhenTypeDiffers() {
        // when
        boolean result = lockerPass.isSamePassType(StudyCafePassType.WEEKLY);

        // then
        assertFalse(result); // false 인지 확인
    }
}
