package tromino;

import java.util.*;

/**
 * This checker assumes that all tiles have different colors starting from 1 to (n*n - 1) / 3.
 */
class TrominoTilingChecker {

    private TrominoTilingChecker() {
    }

    static int countColors(int[][] board, Square missingSquare) {
        Set<Integer> usedColors = new HashSet<>();
        int n = board.length;
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                if (x == missingSquare.x && y == missingSquare.y) {
                    continue;
                }
                usedColors.add(board[x][y]);
            }
        }
        return usedColors.size();
    }

    static boolean areAllTilesHaveCorrectShape(int[][] board, Square missingSquare) {
        int n = board.length;
        boolean[][] checked = new boolean[n][n];
        // Mark the missing square as checked
        checked[missingSquare.x][missingSquare.y] = true;
        TreeSet<Square> squares = getValidSquares(n, missingSquare);
        while (!squares.isEmpty()) {
            Square square = squares.pollFirst();
            List<Square> tile = findTile(board, square, checked);
            // Mark all squares from a tile as checked
            tile.forEach(s -> checked[s.x][s.y] = true);
            if (tile.size() != 3 || !isCorrectShape(tile)) {
                return false;
            }
            squares.removeAll(tile);
        }
        return true;
    }

    private static boolean isCorrectShape(List<Square> tile) {
        boolean areVerticallyAligned = tile.stream().allMatch(s -> s.x == tile.get(0).x);
        boolean areHorizontallyAligned = tile.stream().allMatch(s -> s.y == tile.get(0).y);
        return !areHorizontallyAligned && !areVerticallyAligned;
    }

    private static List<Square> findTile(int[][] board, Square square, boolean[][] checked) {
        int n = board.length;
        List<Square> tile = new ArrayList<>();
        for (int y = square.y - 1; y <= square.y + 1; y++) {
            for (int x = square.x - 1; x <= square.x + 1; x++) {
                // Check if a square is out of the board or already checked
                if (y < 0 || y >= n || x < 0 || x >= n || checked[x][y]) {
                    continue;
                }
                if (board[x][y] == board[square.x][square.y]) {
                    tile.add(new Square(x, y));
                }
            }
        }
        return tile;
    }

    private static TreeSet<Square> getValidSquares(int n, Square p) {
        TreeSet<Square> squares = new TreeSet<>();
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                if (y == p.y && x == p.x) {
                    continue;
                }
                squares.add(new Square(x, y));
            }
        }
        return squares;
    }
}
