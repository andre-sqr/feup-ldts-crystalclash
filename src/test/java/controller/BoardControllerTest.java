package controller;

import com.ldts.crystalclash.controller.BoardController;
import com.ldts.crystalclash.model.*;
import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.gui.GUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import java.io.IOException;


class BoardControllerTest {
    private BoardController controller;
    private Board board;

    @BeforeEach
    void setUp() {
        board = mock(Board.class);
        controller = new BoardController(board);
    }

    @Test
    void testSwapTiles() {
        Tile tile1 = mock(Tile.class);
        Tile tile2 = mock(Tile.class);

        when(tile1.getGridCoordinates()).thenReturn(new Position(0, 0));
        when(tile2.getGridCoordinates()).thenReturn(new Position(1, 1));

        controller.swapTiles(tile1, tile2);

        verify(board).setTile(0, 0, tile2);
        verify(board).setTile(1, 1, tile1);
    }

    @Test
    void testMoveCurrentTile() {
        Tile tile = mock(Tile.class);
        when(board.getCurrentTile()).thenReturn(tile);
        when(board.isValidPosition(1, 1)).thenReturn(true);

        controller.moveCurrentTile(1, 1);

        verify(board).setCurrentTile(tile);
        verify(tile).setCursorOn(true);
    }

    @Test
    void testShiftTilesDown() {
        Board board = mock(Board.class);

        Position position1 = new Position(0, 0);
        Position position2 = new Position(1, 1);

        Color color = Color.EMERALD;

        Tile emptyTile = new EmptyTile(position1, position2, color);

        when(board.getTile(anyInt(), anyInt())).thenReturn(emptyTile);

        BoardController controller = new BoardController(board);

        controller.shiftTilesDown();

        verify(board, atLeastOnce()).setTile(anyInt(), anyInt(), any(Tile.class));
    }

    @Test
    void testRefillBoard() {
        when(board.getRows()).thenReturn(5);
        when(board.getColumns()).thenReturn(5);

        Position position1 = new Position(0, 0);
        Position position2 = new Position(1, 0);

        Color color = Color.EMERALD;
        Tile emptyTile = new EmptyTile(position1, position2, color);
        when(board.getTile(0, 0)).thenReturn(emptyTile);

        controller.refillBoard();

        verify(board).setTile(eq(0), eq(0), any(Tile.class));
    }


    @Test
    void testStep() throws IOException {

        Game game = mock(Game.class);
        GUI gui = mock(GUI.class);

        when(gui.getNextAction()).thenReturn(GUI.ACTION.UP);

        when(gui.getNextAction()).thenReturn(GUI.ACTION.UP);

        Tile currentTile = mock(Tile.class);
        when(board.getCurrentTile()).thenReturn(currentTile);
        Tile tileOnTop = mock(Tile.class);
        when(board.getTileOnTop(currentTile)).thenReturn(tileOnTop);

        controller.step(game, GUI.ACTION.SELECT_TILE);

        verify(board).getTileOnTop(currentTile);
    }


}