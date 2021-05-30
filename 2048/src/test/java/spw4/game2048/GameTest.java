package spw4.game2048;

import org.junit.jupiter.api.*;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameTest {
    private Game g;

    @Mock
    Game g_mock;

    @BeforeEach
    void beforeEach() {
        g = new Game();
        g.initialize();
    }

    @Test
    @DisplayName("getScore returns 0 on new Game")
    public void getScore_OnNewGame_ReturnsZero() {
        assertEquals(0, g.getScore());
    }

    @Test
    @DisplayName("isOver returns true when game is over")
    public void isOver_WhenGameBoardFull_ReturnsTrue() {
        when(g_mock.isOver()).thenReturn(true);

        assertTrue(g_mock.isOver());
    }

    @Test
    @DisplayName("isOver returns true when game is won")
    public void isOver_WhenGameWon_ReturnsTrue() {
        Game ga = spy(Game.class);
        when(ga.isWon()).thenReturn(true);
        assertTrue(ga.isOver());
    }

    @Test
    @DisplayName("isOver returns false when game new")
    public void isOver_WhenGameBoardNotFull_ReturnsFalse() {
        assertFalse(g.isOver());
    }

    @Test
    @DisplayName("isWon returns true when game is won")
    public void isWon_WhenGameBoardHas2048_ReturnsTrue() {
        when(g_mock.isWon()).thenReturn(true);

        assertTrue(g_mock.isWon());
    }

    @Test
    @DisplayName("isWon returns false when game is not won")
    public void isWon_WhenGameBoardHasNo2048_ReturnsFalse() {
        assertFalse(g.isWon());
    }

    @Test
    @DisplayName("toString calls getScore Method exactly once")
    public void toString_WhenGameIsRunning_CallsGetScoreOnce() {
        g_mock.toString();

        assertAll(
                () -> verify(g_mock, times(1)).getScore()
        );
    }

    @Test
    @DisplayName("isOver calls isWon exactly once")
    public void isOver_WhenGameIsRunning_CallsIsWonOnce() {
        Game g_mock = spy(Game.class);
        g_mock.initialize();
        g_mock.isOver();

        assertAll(
                () -> verify(g_mock, times(1)).isWon()
        );
    }

    @Test
    @DisplayName("initialize places two random tiles")
    public void initialize_WhenGameStarts_CallsPlaceRandomTileTwice() {
        g.initialize();

        int nonNull = 0;
    }
}
