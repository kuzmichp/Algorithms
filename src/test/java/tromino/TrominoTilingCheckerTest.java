package tromino;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TrominoTilingCheckerTest {

    @DataProvider(name = "inputParametersProvider")
    public Object[][] getInputParameters() {
        int[][] twoByTwoBoard = new int[][]{{0, 1}, {1, 1}};
        int[][] fourByFourBoard = new int[][]{
                {5, 5, 4, 4},
                {5, 1, 0, 4},
                {2, 1, 1, 3},
                {2, 2, 3, 3}
        };
        int[][] incorrectlyColoredTwoByTwoBoard = new int[][]{{0, 1}, {2, 3}};
        int[][] incorrectlyColoredFourByFourBoard = new int[][]{
                {5, 5, 5, 4},
                {2, 1, 0, 4},
                {2, 1, 3, 4},
                {2, 1, 3, 3}
        };
        return new Object[][]{
                {twoByTwoBoard, new Square(0, 0), 1, true},
                {fourByFourBoard, new Square(1, 2), 5, true},
                {incorrectlyColoredTwoByTwoBoard, new Square(0, 0), 3, false},
                {incorrectlyColoredFourByFourBoard, new Square(1, 2), 5, false}
        };
    }

    @Test(dataProvider = "inputParametersProvider")
    public void shouldCountNumberOfColors(int[][] board, Square missingSquare,
                                          int expectedColorsNumber, boolean expectedCorrectness) {
        assertThat(TrominoTilingChecker.countColors(board, missingSquare))
                .as("Number of used colors")
                .isEqualTo(expectedColorsNumber);
    }

    @Test(dataProvider = "inputParametersProvider")
    public void shouldCheckShapes(int[][] board, Square missingSquare,
                                  int expectedColorsNumber, boolean expectedCorrectness) {
        assertThat(TrominoTilingChecker.areAllTilesHaveCorrectShape(board, missingSquare))
                .as("Correctness of all tile shapes")
                .isEqualTo(expectedCorrectness);
    }
}
