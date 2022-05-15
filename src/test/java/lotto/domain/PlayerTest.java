package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlayerTest {


    @ParameterizedTest
    @CsvSource(value = {"0,0", "1111,1", "2222,2", "500,0","10200,10"}
            ,delimiterString = ",")
    @DisplayName("금액이 가능한 만큼 구매를 한다.")
    void buyLotto(int money, int qty) {
        Player player = new Player(money);
        assertThat(player.getLottos()).hasSize(qty);
    }

    @Test
    @DisplayName("로또를 맞춰본 결과")
    void matchWinnerLotto() {
        List<Lotto> lottoList = Arrays.asList(
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6))  // three
                , new Lotto(Arrays.asList(1, 15, 20, 30, 40, 41)) //fail
                , new Lotto(Arrays.asList(1, 3, 4, 5, 20, 21)) //fail
                , new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6))); // three

        Player player = new Player(lottoList);
        LottoReport lottoReport = player.matchWinnerLotto(new Lotto(Arrays.asList(1, 2, 3, 42, 43, 44)));

        assertAll(() -> {
            assertThat(lottoReport.rewordTotalMoney()).isEqualTo(10000);
            assertThat(lottoReport.lottoResultCount(LottoRank.FOURTH)).isEqualTo(2);
        });

    }

}