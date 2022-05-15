package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

    private Lotto winLotto;

    @BeforeEach
    void setUp() {
        winLotto = Lotto.createCustomLotto(Arrays.asList(1, 2, 3, 4, 5, 6));
    }

    @Test
    @DisplayName("로또의 결과를 맞춰 본다")
    void LottoMatch() {
        Lotto lotto = Lotto.createCustomLotto(Arrays.asList(1, 3, 4, 5, 7, 9));
        LottoRank lottoRank = lotto.match(winLotto);

        assertThat(lottoRank).isEqualTo(LottoRank.THIRD);
        assertThat(lottoRank.rewordMoney()).isEqualTo(LottoRank.THIRD.rewordMoney());
    }

    @ParameterizedTest
    @MethodSource("provideLottoSize")
    @DisplayName("로또의 숫자는 6자리만 가능하다.")
    void lottoSize(List<Integer> lottoNumber) {
        assertThatIllegalArgumentException().isThrownBy(() -> Lotto.createCustomLotto(lottoNumber));
    }

    @Test
    @DisplayName("로또의 숫자는 순서대로 되어 있다.")
    void autoLottoNumbers() {
        Lotto lottoNumbers = Lotto.createCustomLotto(Arrays.asList(1, 4, 3, 5, 6, 7));

        assertThat(lottoNumbers).isEqualTo(Lotto.createCustomLotto(Arrays.asList(1, 3, 4, 5, 6, 7)));
    }

    @Test
    @DisplayName("로또숫자들의 맞은 갯수를 구한다.")
    void matchLottoNumberCount() {
        Lotto result = Lotto.createCustomLotto(Arrays.asList(1, 4, 3, 5, 6, 7));
        Lotto match = Lotto.createCustomLotto(Arrays.asList(1, 3, 5, 40, 44, 45));

        assertThat(result.matchCount(match)).isEqualTo(3);
    }

    @Test
    @DisplayName("로또 숫자들이 중복 되지 않아야 한다")
    void isNotDuplicated() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Lotto.createCustomLotto(Arrays.asList(1, 3, 5, 40, 44, 44)));
    }

    private static Stream<Arguments> provideLottoSize() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 3, 4, 5, 6, 7, 8)),
                Arguments.of(Arrays.asList(1, 2)),
                Arguments.of(new ArrayList<>()),
                Arguments.of((Object) null)
        );
    }


}