package chess;

import gui.GameFrame;
import imhotep.Imhotep;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import support.GameDriver;

import static builders.PawnBuilder.aPawn;
import static builders.QueenBuilder.aQueen;
import static support.GameDriver.*;

@Imhotep(level="UI")
public class MoveTest {

    GameFrame game;
    GameDriver board;

    @Before public void
    showGame() {
        game = new GameFrame();
        game.setVisible( true );
        board = new GameDriver();
    }
    @After public void
    hideFrame() {
        game.setVisible( false );
    }

    @Test public void
    canMoveAPawn() {
        game.display( aPawn().white().on( "e2" ).build() );
        board.cell( "e2" ).click();
        board.cell( "e4" ).click();
        board.has( pawn(), on( "e4" ) );
        board.hasNothingOn( "e2" );
    }

    @Test public void
    canMoveAQueen() {
        game.display( aQueen().white().on( "d5" ).build() );
        board.cell( "d5" ).click();
        board.cell( "h5" ).click();
        board.has( queen(), on( "h5" ) );
    }

    @Test public void
    canMoveSeveralPieces() throws InterruptedException {
        game.display( aQueen().white().on( "d1" ).build(),
                aPawn().white().on( "d2" ).build() );

        board.cell( "d1" ).click();
        board.cell( "g4" ).click();
        board.has( queen(), on( "g4" ) );

        board.cell( "d2" ).click();
        board.cell( "d3" ).click();
        board.has( pawn(), on( "d3" ) );
    }

    @Test public void
    canOnlyMoveWhiteFirst() {
        game.display( aQueen().black().on( "d4" ).build(),
                      aPawn().white().on( "e2" ).build());

        board.cell( "d4" ).click();
        board.cell( "d2" ).click();
        board.hasNothingOn( "d2" );

        board.cell( "e2" ).click();
        board.cell( "e3" ).click();
        board.hasWhite( pawn(), on( "e3" ));

        board.cell( "d4" ).click();
        board.cell( "d2" ).click();
        board.hasBlack(queen(), on( "d2" ));

    }
}
