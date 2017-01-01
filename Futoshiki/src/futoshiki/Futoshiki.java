/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshiki;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Futoshiki {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        
        
        
       
            Cell [][] grids;

            Scanner in = new Scanner(new File("in7-3.txt"));




                int N = in.nextInt();

                


                grids = new Cell[N][N];

                for (int i = 0; i < N; i++){

                    for (int j = 0; j < N; j++){

                        grids[i][j] = new Cell(in.nextInt(),N);

                    }
                }
                
                in.close();

                in = new Scanner(new File("const7-3.txt"));
                int C = in.nextInt();


                for(int i=0;i<C;i++){

                    int x;
                    int y;
                    x= in.nextInt();
                    y= in.nextInt();
                    int z = in.nextInt();
                    int t = in.nextInt();
                    grids[x][y].setConstraintRowColChotoGhor(z, t);
                    //grids[x][y].setConstraintRowChotoGhor(z);
                    //grids[x][y].setConstraintColChotoGhor(t);
                    grids[z][t].setConstraintRowColBoroGhor(x, y);
                    //grids[z][t].setConstraintRowBoroGhor(x);
                    //grids[z][t].setConstraintColBoroGhor(y);

                }

                in.close();

                Board board = new Board(grids,N);
                board.simulate();
                //ArrayList<Solutions> sol=board.simulate();
                //view.setBoards(sol);
                
        
                //View view = new View(board,N);
    

    }
    
    
}
