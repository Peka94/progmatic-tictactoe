package com.progmatic.tictactoeexam;

import com.progmatic.tictactoeexam.enums.PlayerType;
import com.progmatic.tictactoeexam.exceptions.CellException;
import com.progmatic.tictactoeexam.interfaces.AbstractPlayer;
import com.progmatic.tictactoeexam.interfaces.Board;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gerencsér Gábor
 */
public class VictoryAwarePlayerImpl extends AbstractPlayer {

    public VictoryAwarePlayerImpl(PlayerType p) {
        super(p);
    }

    @Override
    public Cell nextMove(Board b) {
        if(b.emptyCells().isEmpty()){
            return null;
        }
        try{
            for (Cell eC : b.emptyCells()) {
                BoardImpl anotherBoard = new BoardImpl();
                for (int i = 0; i < BoardImpl.getCOL_COUNT(); i++) {
                    for (int j = 0; j < BoardImpl.getROW_COUNT(); j++) {
                        if(b.getCell(j, i) != PlayerType.EMPTY){
                            anotherBoard.put(new Cell(j, i, b.getCell(j, i)));
                        }
                    }
                }
                anotherBoard.put(new Cell(eC.getRow(), eC.getCol(), myType));
                if(anotherBoard.hasWon(myType)){
                    return new Cell(eC.getRow(), eC.getCol(), myType);
                }
            }
        }catch(CellException e){
            //TODO
        }
        Cell c = b.emptyCells().get(0);
        return new Cell(c.getRow(),c.getCol(), this.myType);
    }
    
}
