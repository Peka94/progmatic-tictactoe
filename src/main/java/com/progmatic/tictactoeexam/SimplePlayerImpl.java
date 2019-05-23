package com.progmatic.tictactoeexam;

import com.progmatic.tictactoeexam.enums.PlayerType;
import com.progmatic.tictactoeexam.exceptions.CellException;
import com.progmatic.tictactoeexam.interfaces.AbstractPlayer;
import com.progmatic.tictactoeexam.interfaces.Board;
import java.util.List;


/**
 *
 * @author Gerencsér Gábor
 */
public class SimplePlayerImpl extends AbstractPlayer{

    public SimplePlayerImpl(PlayerType p) {
        super(p);
    }

    @Override
    public Cell nextMove(Board b) {
        if(b.emptyCells().isEmpty()){
            return null;
        }
        Cell c = b.emptyCells().get(0);
        return new Cell(c.getRow(),c.getCol(), this.myType);
    }
    
}
