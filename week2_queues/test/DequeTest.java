import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class DequeTest {
    private Deque<String> cut;

    @BeforeEach
    void setUp() {
        cut = new Deque<>();
    }

    @Test
    void shouldThrowIllegalArgumentByAddingNullAsFirstElement() {
        assertThrows(IllegalArgumentException.class, () -> cut.addFirst(null));
    }

    @Test
    void shouldReturnFirstItem() {
        // when
        cut.addFirst("foo");
        assertFalse(cut.isEmpty());
        assertEquals(1, cut.size());
        assertEquals("foo", cut.removeFirst());
    }


    @Test
    void shouldReturnLastItem() {
        // when
        cut.addLast("foo");

        assertFalse(cut.isEmpty());
        assertEquals(1, cut.size());
        assertEquals("foo", cut.removeLast());
    }

    @Test
    void shouldReturnFirstItemAsLastItemOfQueueIsSizeOne() {
        // when
        cut.addFirst("foo");

        // then
        assertFalse(cut.isEmpty());
        assertEquals(1, cut.size());
        assertEquals("foo", cut.removeLast());
    }

    @Test
    void testIterationAfterAddingFirst() {
        // given
        cut.addFirst("foo");
        cut.addFirst("bar");

        // when
        Iterator<String> iterator = cut.iterator();

        // then
        assertEquals("bar", iterator.next());
        assertEquals("foo", iterator.next());
    }

    @Test
    void testIterationAfterAddingLast() {
        // given
        cut.addLast("foo");
        cut.addLast("bar");

        // when
        Iterator<String> iterator = cut.iterator();

        // then
        assertEquals("foo", iterator.next());
        assertEquals("bar", iterator.next());
    }



}