import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PercolationTest {

    private static final int GRID_SIZE = 5;
    private Percolation percolation;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        percolation = new Percolation(GRID_SIZE);
    }

    @Test
    void shouldOpenAGrid() {
        // given
        assertFalse(percolation.isOpen(1, 1));

        // when
        percolation.open(1, 1);

        // then
        assertTrue(percolation.isOpen(1, 1));
    }

    @Test
    void shouldPercolate() {
        // when
        percolation.open(1, 2);
        percolation.open(2, 2);
        percolation.open(3, 2);
        percolation.open(4, 2);
        percolation.open(5, 2);

        // then
        assertTrue(percolation.percolates());
    }

    @Test
    void shouldNotPercolate() {
        // when
        percolation.open(1, 1);
        percolation.open(1, 2);
        percolation.open(1, 4);
        percolation.open(1, 5);

        // then
        assertFalse(percolation.percolates());
    }

    @Test
    void shouldBeFull() {
        // when
        percolation.open(1, 1);
        percolation.open(1, 2);

        // then
        assertTrue(percolation.isFull(1, 2));
    }

    @Test
    void shouldNotBeFull() {
        // when
        percolation.open(3, 2);

        // then
        assertFalse(percolation.isFull(3, 2));
    }

    @Test
    void shouldNotBeFilled() {
        // when
        percolation.open(1, 3);
        percolation.open(2, 3);
        percolation.open(3, 3);
        percolation.open(4, 3);
        percolation.open(5, 3);
        percolation.open(5, 5);

        // then
        assertTrue(percolation.isFull(1, 3));
        assertTrue(percolation.isFull(2, 3));
        assertTrue(percolation.isFull(3, 3));
        assertTrue(percolation.isFull(4, 3));
        assertTrue(percolation.isFull(5, 3));
        assertFalse(percolation.isFull(5, 5));
    }

    @Test
    void shouldNotPercolateInCornerCase() {
        Percolation percolation = new Percolation(1);
        assertFalse(percolation.percolates());
        percolation.open(1, 1);
        assertTrue(percolation.percolates());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfGridSizeIsZero() {
        assertThrows(IllegalArgumentException.class, () -> new Percolation(0));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfGridSizeIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Percolation(-5));
    }

    @Test
    void shouldThrowIndexOutOfBoundsExceptionIfOpenIsCalledWithZero() {
        assertThrows(Exception.class, () -> percolation.open(GRID_SIZE + 1, GRID_SIZE + 1));
    }


}