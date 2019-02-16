package tromino;

import java.util.Objects;

class Square implements Comparable<Square> {

    int x;
    int y;

    Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return x == square.x &&
                y == square.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public int compareTo(Square o) {
        return x != o.x ? x - o.x : y - o.y;
    }
}
