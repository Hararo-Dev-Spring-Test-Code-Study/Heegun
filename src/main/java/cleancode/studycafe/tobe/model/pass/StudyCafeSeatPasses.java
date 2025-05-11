package cleancode.studycafe.tobe.model.pass;

import java.util.List;

public class StudyCafeSeatPasses {
    // 좌석의 리스트에서 특정 타입의 이용권을 필터링해 반환합니다.

    private final List<StudyCafeSeatPass> passes;

    private StudyCafeSeatPasses(List<StudyCafeSeatPass> passes) {
        this.passes = passes;
    }

    public static StudyCafeSeatPasses of(List<StudyCafeSeatPass> passes) {
        return new StudyCafeSeatPasses(passes);
    }

    public List<StudyCafeSeatPass> findPassBy(StudyCafePassType studyCafePassType) {
        return passes.stream()
            .filter(studyCafePass -> studyCafePass.isSamePassType(studyCafePassType))
            .toList();
    }

}
