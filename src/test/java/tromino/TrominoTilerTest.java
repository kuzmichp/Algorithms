package tromino;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TrominoTilerTest {

    private TrominoTiler trominoTiler = new TrominoTiler();

    @DataProvider(name = "inputParametersProvider")
    public Object[][] getInputParameters() {
        return new Object[][]{
                { 4, new Square(2,1) },
                { 8, new Square(4, 4) }
        };
    }

    @Test(dataProvider = "inputParametersProvider")
    public void shouldTileBoard(int n, Square missingSquare) {
        // when
        int[][] board = trominoTiler.tile(n, missingSquare);

        // then
        int expectedNumberOfColors = (n * n - 1) / 3;
        assertThat(TrominoTilingChecker.countColors(board, missingSquare))
                .as("Number of used colors")
                .isEqualTo(expectedNumberOfColors);
        assertThat(TrominoTilingChecker.areAllTilesHaveCorrectShape(board, missingSquare))
                .as("All tiles have correct shape")
                .isTrue();
    }
}
