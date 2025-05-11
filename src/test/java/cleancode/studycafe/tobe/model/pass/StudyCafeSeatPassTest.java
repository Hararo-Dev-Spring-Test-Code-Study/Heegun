// StudyCafeSeatPass 클래스
// 1) cannotUseLocker() 메서드 테스트 코드
// 2) isSameDurationType() 메서드 테스트 코드
// 3) getDiscountPrice() 메서드 테스트 코드
// 테스트 목적 :
// 1) 좌석 타입이 사물함 사용이 불가능한 타입인지 여부를 판단하는 로직 검증
// 2) 좌석과 사물함의 타입과 이용 기간이 모두 일치하는지 판단하는 로직 검증
// 3) 가격과 할인율을 곱한 할인 금액이 정확히 계산되는지 검증 (좌석)

package cleancode.studycafe.tobe.model.pass;

import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudyCafeSeatPassTest {

    // cannotUseLocker()
    private StudyCafeSeatPass fixedPass;  // 사물함 사용 가능한 좌석 타입 객체
    private StudyCafeSeatPass hourlyPass; // 사물함 사용 불가능한 좌석 타입 객체

    // isSameDurationType()
    private StudyCafeLockerPass matchedLockerPass; // 타입과 기간이 모두 같은 사물함
    private StudyCafeLockerPass mismatchedTypeLockerPass; // 타입이 다른 사물함
    private StudyCafeLockerPass mismatchedDurationLockerPass; // 기간이 다른 사물함

    // getDiscountPrice()
    private StudyCafeSeatPass discountedPass; // 할인율이 적용된 좌석 이용권 객체
    private StudyCafeSeatPass noDiscountPass; // 할인율이 0인 좌석 이용권 객체

    @BeforeEach
    void setUp() {
        // given
        fixedPass = StudyCafeSeatPass.of( // isSameDurationType 메서드 검증 기준 객체
            StudyCafePassType.FIXED, // 사물함 사용 가능한 타입 (1인 고정석)
            30,
            100000,
            0.2
        );

        hourlyPass = StudyCafeSeatPass.of(
            StudyCafePassType.HOURLY, // 사물함 사용 불가능한 타입 (시간 단위 이용권)
            10,
            10000,
            0.2
        );

        matchedLockerPass = StudyCafeLockerPass.of( // fixedPass 와 동일한 타입, 동일한 기간을 가진 객체
            StudyCafePassType.FIXED, // 동일한 타입
            30, // 동일한 기간
            20000
        );

        mismatchedTypeLockerPass = StudyCafeLockerPass.of( // fixedPass 와 다른 타입, 동일한 기간을 가진 객체
            StudyCafePassType.WEEKLY, // 다른 타입
            30, // 동일한 기간
            20000
        );

        mismatchedDurationLockerPass = StudyCafeLockerPass.of( // fixedPass 와 동일한 타입, 다른 기간을 가진 객체
            StudyCafePassType.FIXED, // 동일한 타입
            14, // 다른 기간
            20000
        );


        discountedPass = StudyCafeSeatPass.of(
            StudyCafePassType.FIXED,
            30,
            100000, // 가격: 10만원
            0.15 // 할인율: 15%
        );

        noDiscountPass = StudyCafeSeatPass.of(
            StudyCafePassType.FIXED,
            30,
            100000,
            0.0 // 할인율 없음
        );

    }


    // cannotUseLocker()
    @Test
    @DisplayName("사물함 사용이 불가능한 좌석 타입인 경우 true를 반환해야 한다")
    // HOURLY 타입은 사물함 사용이 불가능하므로 true가 반환되는지 확인
    void returnTrueIfLockerNotAllowed() {
        // when
        boolean result = hourlyPass.cannotUseLocker(); // 사물함 사용 가능 여부 판단

        // then
        assertTrue(result); // true 인지 확인
    }

    @Test
    @DisplayName("사물함 사용이 가능한 좌석 타입인 경우 false를 반환해야 한다")
    // FIXED 타입은 사물함 사용이 가능하므로 false가 반환되는지 확인
    void returnFalseIfLockerAllowed() {
        // when
        boolean result = fixedPass.cannotUseLocker(); // 사물함 사용 가능 여부 판단

        // then
        assertFalse(result); // false 인지 확인
    }


    // isSameDurationType()
    @Test
    @DisplayName("좌석과 사물함의 타입과 기간이 모두 일치하는 경우 true 반환")
    // 타입과 기간이 모두 동일할 경우 true를 반환해야 함
    void returnTrueWhenTypeAndDurationMatch() {
        // when
        boolean result = fixedPass.isSameDurationType(matchedLockerPass);

        // then
        assertTrue(result); // true 인지 확인
    }

    @Test
    @DisplayName("좌석과 사물함의 타입이 다르면 false 반환")
    // 타입이 다르면 기간이 같아도 false 반환
    void returnFalseWhenTypeDiffers() {
        // when
        boolean result = fixedPass.isSameDurationType(mismatchedTypeLockerPass); // 타입 비교

        // then
        assertFalse(result); // false 인지 확인
    }

    @Test
    @DisplayName("좌석과 사물함의 기간이 다르면 false 반환")
    // 기간이 다르면 타입이 같아도 false 반환
    void returnFalseWhenDurationDiffers() {
        // when
        boolean result = fixedPass.isSameDurationType(mismatchedDurationLockerPass); // 기간 비교

        // then
        assertFalse(result); // false 인지 확인
    }


    // getDiscountPrice()
    @Test
    @DisplayName("할인율이 적용된 경우 정확한 할인 금액이 계산되어야 한다")
    // 가격 * 할인율인 할인 금액이 정확히 계산되는지 확인
    void calculateDiscountedPriceCorrectly() {
        // when
        int result = discountedPass.getDiscountPrice();

        // then
        int expected = (int)(100000 * 0.15); // 직접 계산: 15000
        assertEquals(expected, result); // 기대값과 계산값 일치 여부 검증
    }

    @Test
    @DisplayName("할인율이 0인 경우 할인 금액은 0이어야 한다")
    // 할인율이 없을 경우 할인 금액은 0인지 확인
    void returnZeroIfNoDiscount() {
        // when
        int result = noDiscountPass.getDiscountPrice();

        // then
        assertEquals(0, result); // 할인 없음 → 할인 금액 0원
    }
}
