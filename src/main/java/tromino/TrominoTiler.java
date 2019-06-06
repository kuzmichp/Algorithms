package tromino;

import java.util.Stack;

/*
    @author Agata
 */
class TrominoTiler {

    // TODO or NOT TODO
    // It could be simplified by change Quarter reprezentation to upper left corner and length of the square
    class Quarter {
        Square upperLeft;
        Square lowerRight;

        Quarter(Square upperLeft, Square lowerRight) {
            this.upperLeft = upperLeft;
            this.lowerRight = lowerRight;
        }
    }

    private boolean containsSpecial(Quarter quarter, Square special) {
        if (quarter.upperLeft.x <= special.x && special.x <= quarter.lowerRight.x
                && quarter.upperLeft.y <= special.y && special.y <= quarter.lowerRight.y) {
            return true;
        }
        return false;
    }

    void paint(int[][] matrix, int color, Stack<Quarter> quarters, Stack<Square> paintedOrMissing) {
        Quarter curr = quarters.pop();
        Square special = paintedOrMissing.pop();

        int len = curr.lowerRight.x - curr.upperLeft.x + 1;
        if (len == 1) {
            return;
        }
        len = len / 2;

        Quarter upperLeft = new Quarter(new Square(curr.upperLeft.x, curr.upperLeft.y),
                new Square(curr.lowerRight.x - len, curr.lowerRight.y - len));
        Quarter upperRight = new Quarter(new Square(curr.upperLeft.x, curr.upperLeft.y + len),
                new Square(curr.lowerRight.x - len, curr.lowerRight.y));
        Quarter lowerLeft = new Quarter(new Square(curr.upperLeft.x + len, curr.upperLeft.y),
                new Square(curr.lowerRight.x, curr.lowerRight.y - len));
        Quarter lowerRight = new Quarter(new Square(curr.upperLeft.x + len, curr.upperLeft.y + len),
                new Square(curr.lowerRight.x, curr.lowerRight.y));

        int x = curr.upperLeft.x + len - 1;
        int y = curr.upperLeft.y + len - 1;
        processQuarter(matrix, quarters, paintedOrMissing, special, upperLeft, color, x, y);

        x = curr.upperLeft.x + len - 1;
        y = curr.upperLeft.y + len;
        processQuarter(matrix, quarters, paintedOrMissing, special, upperRight, color, x, y);

        x = curr.upperLeft.x + len;
        y = curr.upperLeft.y + len - 1;
        processQuarter(matrix, quarters, paintedOrMissing, special, lowerLeft, color, x, y);

        x = curr.upperLeft.x + len;
        y = curr.upperLeft.y + len;
        processQuarter(matrix, quarters, paintedOrMissing, special, lowerRight, color, x, y);
    }

    private void processQuarter(int[][] matrix, Stack<Quarter> quarters, Stack<Square> paintedOrMissing,
                                Square special, Quarter quarter, int color, int x, int y) {
        quarters.push(quarter);
        if (!containsSpecial(quarter, special)) {
            matrix[x][y] = color;
            paintedOrMissing.push(new Square(x, y));
        } else {
            paintedOrMissing.push(special);
        }
    }

    int[][] tile(int n, Square missingSquare) {
        int[][] matrix = new int[n][n];
        int color = 1;
        Stack<Quarter> quarters = new Stack<>();
        Stack<Square> paintedOrMissing = new Stack<>();
        quarters.push(new Quarter(new Square(0, 0), new Square(n - 1, n - 1)));
        paintedOrMissing.push(missingSquare);

        while (!quarters.isEmpty()) {
            paint(matrix, color, quarters, paintedOrMissing);
            color++;
        }
        return matrix;
    }
}
