package com.progmatic.tictactoeexam;

import com.progmatic.tictactoeexam.enums.PlayerType;
import com.progmatic.tictactoeexam.exceptions.CellException;
import com.progmatic.tictactoeexam.interfaces.Board;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gerencsér Gábor
 */
public class BoardImpl implements Board {
    
    private final Cell[][] board;
    private static final int ROW_COUNT = 3;
    private static final int COL_COUNT = 3;

    public BoardImpl() {
        this.board = new Cell[COL_COUNT][ROW_COUNT];
        for (int i = 0; i < COL_COUNT; i++) {
            for (int j = 0; j < ROW_COUNT; j++) {
                this.board[i][j] = new Cell(i,j);
            }
        }
    }

    public static int getROW_COUNT() {
        return ROW_COUNT;
    }

    public static int getCOL_COUNT() {
        return COL_COUNT;
    }

    @Override
    public PlayerType getCell(int rowIdx, int colIdx) throws CellException {
        if(rowIdx >= this.ROW_COUNT || rowIdx < 0 ||
           colIdx >= this.COL_COUNT || colIdx < 0 ){
            throw new CellException(colIdx, rowIdx, "This cell is not on the board.");
        }
        return this.board[colIdx][rowIdx].getCellsPlayer();
    }

    @Override
    public void put(Cell cell) throws CellException {
        if(getCell(cell.getRow(),cell.getCol()) != PlayerType.EMPTY){
            throw new CellException(cell.getCol(), cell.getRow(), "This cell is not empty.");
        }else{
            this.board[cell.getCol()][cell.getRow()].setCellsPlayer(cell.getCellsPlayer());
        }
    }

    @Override
    public boolean hasWon(PlayerType p) {
        if(p == PlayerType.EMPTY){
            return false;
        }
        for (int i = 0; i < COL_COUNT; i++) {
                if(this.board[i][0].getCellsPlayer() == p && this.board[i][1].getCellsPlayer() == p && this.board[i][2].getCellsPlayer() == p ||
                   this.board[0][i].getCellsPlayer() == p && this.board[1][i].getCellsPlayer() == p && this.board[2][i].getCellsPlayer() == p ){
                    return true;
                }
            
        }
        if(this.board[0][0].getCellsPlayer() == p && this.board[1][1].getCellsPlayer() == p && this.board[2][2].getCellsPlayer() == p ||
           this.board[0][2].getCellsPlayer() == p && this.board[1][1].getCellsPlayer() == p && this.board[2][0].getCellsPlayer() == p ){
            return true;
        }
        return false;
    }

    @Override
    public List<Cell> emptyCells() {
        List<Cell> emptyList = new ArrayList<>();
        for (int i = 0; i < COL_COUNT; i++) {
            for (int j = 0; j < ROW_COUNT; j++) {
                if(this.board[i][j].getCellsPlayer()== PlayerType.EMPTY){
                    emptyList.add(new Cell (i,j,PlayerType.EMPTY));
                }
            }
        }
        return emptyList;
    }
    
}
