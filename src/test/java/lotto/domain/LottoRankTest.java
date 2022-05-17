package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoRankTest {

    @ParameterizedTest
    @MethodSource("lottoRankFilterProvideCount")
    @DisplayName("맞춘 갯수에 따른 LottoRank를 반환한다.")
    void lottoRankFilter(int matchCount,boolean bonus,LottoRank result) {
        LottoRank reword = LottoRank.reword(matchCount, bonus);

        assertThat(reword).isEqualTo(result);
    }

    @Test
    @DisplayName("당첨된 상태만 반환한다")
    void winnerRanks() {
        List<LottoRank> winnerRanks = LottoRank.winnerRanks();

        assertThat(winnerRanks.contains(LottoRank.FAIL)).isFalse();
        assertThat(winnerRanks)
                .contains(LottoRank.FIRST, LottoRank.SECOND, LottoRank.THIRD, LottoRank.FOURTH);
    }


    @Test
    @DisplayName("2등인지 확인한다.")
    public void isSecond() {
        LottoRank secondRank = LottoRank.SECOND;
        LottoRank firstRank = LottoRank.FIRST;

        assertThat(secondRank.isSecond()).isTrue();
        assertThat(firstRank.isSecond()).isFalse();
    }

    private static Stream<Arguments> lottoRankFilterProvideCount() {
        return Stream.of(
                 Arguments.of(0, true, LottoRank.FAIL)
                , Arguments.of(3, false, LottoRank.FIFTH)
                , Arguments.of(3, true, LottoRank.FIFTH)
                , Arguments.of(4, true, LottoRank.FOURTH)
                , Arguments.of(5, false, LottoRank.THIRD)
                , Arguments.of(5, true, LottoRank.SECOND)
                , Arguments.of(6, false, LottoRank.FIRST)
        );
    }

}