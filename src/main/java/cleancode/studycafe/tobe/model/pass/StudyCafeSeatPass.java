package cleancode.studycafe.tobe.model.pass;

import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;

public class StudyCafeSeatPass implements StudyCafePass {
    // 사용자가 좌석을 선택했을 때, 좌석의 타입, 기간, 할인율 등을 확인합니다

    private final StudyCafePassType passType; // 좌석 타입 - 시간, 주, 1인 고정
    private final int duration; // 기간
    private final int price; // 가격
    private final double discountRate; // 할인율

    private StudyCafeSeatPass(StudyCafePassType passType, int duration, int price, double discountRate) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
        this.discountRate = discountRate;
    }

    public static StudyCafeSeatPass of(StudyCafePassType passType, int duration, int price, double discountRate) {
        return new StudyCafeSeatPass(passType, duration, price, discountRate);
    }

    public boolean cannotUseLocker() {
        return this.passType.isNotLockerType();
    }

    public boolean isSameDurationType(StudyCafeLockerPass lockerPass) {
        return lockerPass.isSamePassType(this.passType)
            && lockerPass.isSameDuration(this.duration);
    }

    public boolean isSamePassType(StudyCafePassType passType) {
        return this.passType == passType;
    }

    @Override
    public StudyCafePassType getPassType() {
        return passType;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public int getDiscountPrice() {
        return (int) (this.price * this.discountRate);
    }

}
