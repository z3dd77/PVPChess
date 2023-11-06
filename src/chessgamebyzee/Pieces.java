/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chessgamebyzee;

import java.util.LinkedList;

/**
 *
 * @author zee
 */
public class Pieces {

    int xp;
    int yp;
    
    int x;
    int y;
    
    boolean isWhite;
    LinkedList<Pieces> ps;
    String name;

    public Pieces(int xp, int yp, boolean isWhite, String n, LinkedList<Pieces> ps) {

        this.xp = xp;
        this.yp = yp;
        
        x = xp * 64;
        y = yp * 64;
        
        this.isWhite = isWhite;
        this.ps = ps;
        name = n;
        ps.add(this);

    }

    public void move(int xp, int yp) {

        if(ChessGameByZee.getPiece(xp * 64, yp * 64) != null) {
            if(ChessGameByZee.getPiece(xp * 64, yp * 64).isWhite != isWhite) {
            ChessGameByZee.getPiece(xp * 64, yp * 64).kill();
            }
            else {
                
                x = this.xp * 64;
                y = this.yp * 64;
                
                return;
            }
        }
        
        this.xp = xp;
        this.yp = yp;
        
        x = xp * 64;
        y = yp * 64;
        
    }

    public void kill() {
        ps.remove(this);
    }

    public boolean isValidMove(int targetX, int targetY) {
    int dx = Math.abs(targetX - xp);
    int dy = Math.abs(targetY - yp);

    if (name.equalsIgnoreCase("pawn")) {
        if (isWhite) {
            if (dx == 0 && dy == 1) {
                return true; // Valid move for a white pawn
            }
        } else {
            if (dx == 0 && dy == -1) {
                return true; // Valid move for a black pawn
            }
        }
    } else if (name.equalsIgnoreCase("rook")) {
        if (dx == 0 || dy == 0) {
            return true; // Valid move for a rook (horizontal or vertical)
        }
    } else if (name.equalsIgnoreCase("knight")) {
        if ((dx == 1 && dy == 2) || (dx == 2 && dy == 1)) {
            return true; // Valid move for a knight (L-shaped)
        }
    } else if (name.equalsIgnoreCase("bishop")) {
        if (dx == dy) {
            return true; // Valid move for a bishop (diagonal)
        }
    } else if (name.equalsIgnoreCase("queen")) {
        if (dx == 0 || dy == 0 || dx == dy) {
            return true; // Valid move for a queen (horizontal, vertical, or diagonal)
        }
    } else if (name.equalsIgnoreCase("king")) {
        if (dx <= 1 && dy <= 1) {
            return true; // Valid move for a king (one square in any direction)
        }
    }
    
    return false;
}
    
}
