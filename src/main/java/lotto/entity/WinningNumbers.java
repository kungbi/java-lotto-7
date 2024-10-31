package lotto.entity;

import static lotto.exception.WinnerNumberExceptionMessage.BONUS_NUMBER_DUPLICATE;
import static lotto.exception.WinnerNumberExceptionMessage.BONUS_NUMBER_OUT_OF_RANGE;

import java.util.List;
import lotto.configuration.LottoConfiguration;
import lotto.exception.LottoValidationException;

public class WinningNumbers {
    private final Lotto mainNumbers;
    private final int bonusNumber;

    public WinningNumbers(List<Integer> mainNumbers, int bonusNumber) {
        validate(mainNumbers, bonusNumber);
        this.mainNumbers = new Lotto(mainNumbers);
        this.bonusNumber = bonusNumber;
    }

    private void validate(List<Integer> mainNumbers, int bonusNumber) {
        if (!(LottoConfiguration.LOTTO_MIN_NUMBER.getValue() <= bonusNumber
                && bonusNumber <= LottoConfiguration.LOTTO_MAX_NUMBER.getValue())) {
            throw new LottoValidationException(BONUS_NUMBER_OUT_OF_RANGE);
        }
        if (mainNumbers.stream().anyMatch(number -> number == bonusNumber)) {
            throw new LottoValidationException(BONUS_NUMBER_DUPLICATE);
        }
    }
}