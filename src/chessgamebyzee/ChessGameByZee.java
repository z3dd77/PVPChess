/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chessgamebyzee;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author zee
 */
public class ChessGameByZee {

    public static LinkedList<Pieces> ps = new LinkedList<>();
    public static Pieces selectedPiece = null;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        
        BufferedImage all = ImageIO.read(new File("C:\\Users\\djz3d\\OneDrive\\Documents\\NetBeansProjects\\ChessGameByZee\\chess.png"));
        Image imgs[] = new Image[12];
        int ind = 0;
        for (int y = 0; y < 400; y += 200) {
            for (int x = 0; x < 1200; x += 200) {
                imgs[ind] = all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                ind++;
            }
        }
        
        Pieces brook = new Pieces(0, 0, false, "rook", ps);
        Pieces bkinght = new Pieces(1, 0, false, "knight", ps);
        Pieces bbishop = new Pieces(2, 0, false, "bishop", ps);
        Pieces bqueen = new Pieces(3, 0, false, "queen", ps);
        Pieces bking = new Pieces(4, 0, false, "king", ps);
        Pieces bbishop2 = new Pieces(5, 0, false, "bishop", ps);
        Pieces bkight2 = new Pieces(6, 0, false, "knight", ps);
        Pieces brook2 = new Pieces(7, 0, false, "rook", ps);
        Pieces bpawn1 = new Pieces(1, 1, false, "pawn", ps);
        Pieces bpawn2 = new Pieces(2, 1, false, "pawn", ps);
        Pieces bpawn3 = new Pieces(3, 1, false, "pawn", ps);
        Pieces bpawn4 = new Pieces(4, 1, false, "pawn", ps);
        Pieces bpawn5 = new Pieces(5, 1, false, "pawn", ps);
        Pieces bpawn6 = new Pieces(6, 1, false, "pawn", ps);
        Pieces bpawn7 = new Pieces(7, 1, false, "pawn", ps);
        Pieces bpawn8 = new Pieces(0, 1, false, "pawn", ps);
        
        Pieces wrook = new Pieces(0, 7, true, "rook", ps);
        Pieces wkinght = new Pieces(1, 7, true, "knight", ps);
        Pieces wbishop = new Pieces(2, 7, true, "bishop", ps);
        Pieces wqueen = new Pieces(3, 7, true, "queen", ps);
        Pieces wking = new Pieces(4, 7, true, "king", ps);
        Pieces wbishop2 = new Pieces(5, 7, true, "bishop", ps);
        Pieces wkight2 = new Pieces(6, 7, true, "knight", ps);
        Pieces wrook2 = new Pieces(7, 7, true, "rook", ps);
        Pieces wpawn1 = new Pieces(1, 6, true, "pawn", ps);
        Pieces wpawn2 = new Pieces(2, 6, true, "pawn", ps);
        Pieces wpawn3 = new Pieces(3, 6, true, "pawn", ps);
        Pieces wpawn4 = new Pieces(4, 6, true, "pawn", ps);
        Pieces wpawn5 = new Pieces(5, 6, true, "pawn", ps);
        Pieces wpawn6 = new Pieces(6, 6, true, "pawn", ps);
        Pieces wpawn7 = new Pieces(7, 6, true, "pawn", ps);
        Pieces wpawn8 = new Pieces(0, 6, true, "pawn", ps);
        
        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 512, 512);
        frame.setUndecorated(true);
        JPanel pn = new JPanel() {
            @Override
            public void paint(Graphics g) {
                boolean white = true;
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {
                        if (white) {
                            g.setColor(new Color(235, 235, 208));
                        } else {
                            g.setColor(new Color(119, 148, 85));
                        }
                        
                        g.fillRect(x * 64, y * 64, 64, 64);
                        white = !white;
                    }
                    white = !white;
                }
                
                for (Pieces p : ps) {
                    int ind = 0;
                    if (p.name.equalsIgnoreCase("king")) {
                        ind = 0;
                    }
                    if (p.name.equalsIgnoreCase("queen")) {
                        ind = 1;
                    }
                    if (p.name.equalsIgnoreCase("bishop")) {
                        ind = 2;
                    }
                    if (p.name.equalsIgnoreCase("knight")) {
                        ind = 3;
                    }
                    if (p.name.equalsIgnoreCase("rook")) {
                        ind = 4;
                    }
                    if (p.name.equalsIgnoreCase("pawn")) {
                        ind = 5;
                    }
                    if (!p.isWhite) {
                        ind += 6;
                    }
                    g.drawImage(imgs[ind], p.x, p.y, this);
                }
                
            }
        };
        frame.add(pn);
        
        frame.addMouseMotionListener(new MouseMotionListener() {
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if(selectedPiece != null) {
                    selectedPiece.x = e.getX() - 32;
                    selectedPiece.y = e.getY() - 32;
                    frame.repaint();
                }
            }
            
            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
        
        frame.addMouseListener(new MouseListener() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println((getPiece(e.getX(), e.getY()).isWhite? "white ": "black ") + getPiece(e.getX(), e.getY()).name);
                selectedPiece = getPiece(e.getX(), e.getY());
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                selectedPiece.move(e.getX() / 64, e.getY() / 64);
                frame.repaint();
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
        
    }
    
    public static Pieces getPiece(int x, int y) {
        
        int xp = x / 64;
        int yp = y / 64;
        for(Pieces p: ps) {
            if(p.xp == xp && p.yp == yp){
                return p;
            }
            
        }
        return null;
    }
    
}
