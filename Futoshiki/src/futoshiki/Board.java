/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshiki;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author MASUD
 */
public class Board {
    
    
    
    private Cell [][] grids;
    private int N;
    
    private int currentRow;
    private int currentCol;
    private ArrayList<Position> stack;
    private View view;
    private ArrayList<Solutions> sol;
    private int variableCount ;
    private int valueCount;
    
    
    Board(Cell[][] grids , int N){
        
        this.grids= grids;
        this.N = N;
        //stack = new ArrayList<>();
        view = new View (N);
        sol = new ArrayList<Solutions>();
        variableCount = 0;
        valueCount=0;
    }
    
    int getN(){
        return N;
    }
    
    void simulate(){
    
        
        recursiveBacktracking();
      
        view.setBoards(sol,variableCount,valueCount);
        
    }
    
    boolean isFinished(){
        
        for(int i = 0; i< N ; i++){
            for(int j=0; j< N ; j++){
                if(grids[i][j].getCurrentValue()==0){
                    return false;
                }
            }
        }
        
        return true;
    }
    
        boolean recursiveBacktracking(){
            sol.add(new Solutions(copyOfGrids()));
            showBoardInConsole();

            if(isFinished()) 
                return true;

            //currentRow = pos.row;
            //currentCol= pos.col;
            //Position pos = MinimumRemainingValue();
            Position pos = Degree();
            //Position pos= FirstAvailable();
            //stack.add(pos);
            int val=-1;
                for(int j=0;j<N;j++){
                        
                        if(grids[pos.row][pos.col].getDomain()[j]!=-1){
                            valueCount++;
                            val= grids[pos.row][pos.col].getDomain()[j];
                                if(checkConstraint(pos,val)){

                                    
                                    grids[pos.row][pos.col].setCurrentValue(val);
                                    grids[pos.row][pos.col].getDomain()[j]=-1;
                                    grids[pos.row][pos.col].decreaseRemainingValue();
                                    variableCount ++;
                                    
                                    for(int k=0;k<N;k++){
                                        
                                        if(k!=pos.row && grids[k][pos.col].canAlter()){
                                            grids[k][pos.col].getDomain()[j]=-1;
                                            if((grids[k][pos.col].getRemainingValue())>0){
                                                grids[k][pos.col].decreaseRemainingValue();
                                            }
                                        }
                                    }

                                    for(int k=0;k<N;k++){
                                        
                                        if(k!=pos.col && grids[pos.row][k].canAlter()){
                                            grids[pos.row][k].getDomain()[j]=-1;
                                            if((grids[pos.row][k].getRemainingValue())>0){
                                                grids[pos.row][k].decreaseRemainingValue();
                                            }
                                        }
                                    }
                                    

                                    boolean result = recursiveBacktracking();
                                    if(result!=false){
                                        return result;
                                    }
                                //reset everything
                                grids[pos.row][pos.col].setCurrentValue(0);
                                grids[pos.row][pos.col].increaseRemainingValue();
                                grids[pos.row][pos.col].getDomain()[j]=j+1;
                                //grids[pos.row][pos.col].resetDomain();

                                for(int k=0;k<N;k++){
                                        
                                        if(k!=pos.row && grids[k][pos.col].canAlter()){
                                            grids[k][pos.col].getDomain()[j]=j+1;
                                            if((grids[k][pos.col].getRemainingValue())<N){
                                                grids[k][pos.col].increaseRemainingValue();
                                            }
                                        }
                                    }

                                    for(int k=0;k<N;k++){
                                        
                                        if(k!=pos.col && grids[pos.row][k].canAlter()){
                                            grids[pos.row][k].getDomain()[j]=j+1;
                                            if((grids[pos.row][k].getRemainingValue())<N){
                                                grids[pos.row][k].increaseRemainingValue();
                                            }
                                        }
                                    }

                            }
                        }
                }
                
                

            return false;
        }
    
    Position MinimumRemainingValue(){
        
        int minVal=1000;
        Position minPosition = new Position();
        
        for(int i=0;i<N; i++){
            for(int j=0;j<N;j++){
                
                if(grids[i][j].canAlter() && grids[i][j].getRemainingValue()<minVal && grids[i][j].getCurrentValue()==0){
                    minVal= grids[i][j].getRemainingValue();
                    minPosition.row=i;
                    minPosition.col=j;
                }
                
            }
        }
        
        return minPosition;
    }
    
    Position Degree(){
        
        int max=-1;
        Position maxPosition = new Position();
        
        for(int i=0;i<N; i++){
            for(int j=0;j<N;j++){
                
                if(grids[i][j].canAlter() && grids[i][j].getCurrentValue()==0){
                    int constraintNumber= grids[i][j].getConstraintRowColBoroGhor().size()+
                            grids[i][j].getConstraintRowColChotoGhor().size();
                    if(constraintNumber>max){
                        max=constraintNumber;
                        maxPosition.row=i;
                        maxPosition.col=j;
                    }
                }
                
            }
        }
        
        return maxPosition;
    }
    
    
    Position FirstAvailable(){
        
        
        Position position = new Position();
        
        for(int i=0;i<N; i++){
            for(int j=0;j<N;j++){
                
                if(grids[i][j].canAlter() && grids[i][j].getCurrentValue()==0){
                        position.row=i;
                        position.col=j;
                        return position;
                }
            }
                
        }
        
        
        return position;
    }
    
    boolean checkConstraint(Position pos, int value){
        boolean constraintStatusChoto= grids[pos.row][pos.col].getConstraintStatusChoto();
        if(constraintStatusChoto==true){
            
            ArrayList<Position> constraintList = grids[pos.row][pos.col].getConstraintRowColChotoGhor();
            
            for(int i=0; i<constraintList.size();i++){
                int constraintRow = constraintList.get(i).row;
                int constraintCol = constraintList.get(i).col;
            
            if(value<=grids[constraintRow][constraintCol].getCurrentValue() && 
                    grids[constraintRow][constraintCol].getCurrentValue()!=0)
                return false;
            }
            
        }
        
        boolean constraintStatusBoro= grids[pos.row][pos.col].getConstraintStatusBoro();
        if(constraintStatusBoro==true){
            
           ArrayList<Position> constraintList = grids[pos.row][pos.col].getConstraintRowColBoroGhor();
            
            for(int i=0; i<constraintList.size();i++){
                int constraintRow = constraintList.get(i).row;
                int constraintCol = constraintList.get(i).col;
            
            if(value>=grids[constraintRow][constraintCol].getCurrentValue() && 
                    grids[constraintRow][constraintCol].getCurrentValue()!=0)
                return false;
            }
        }
        
        
        
        //Row-wise Check
        for(int i=0; i<this.N; i++){
            if(i!=pos.row){
                if(grids[i][pos.col].getCurrentValue()==value){
                    return false;
                }
            }
        }
        
        //Col-wise Check
        
        for(int i=0; i<this.N; i++){
            if(i!=pos.col){
                if(grids[pos.row][i].getCurrentValue()==value){
                    return false;
                }
            }
        }
        
        return true;
        
    }
    
    
    
    
    void showBoardInConsole(){
        System.out.println("-------------------------------------------------\n--------------------------------\n");
        for(int i=0;i<N;i++){
            for(int j=0;j<N; j++){
                System.out.print(grids[i][j].getCurrentValue()+" ");
            }
            System.out.println();
        }
    }
    
    int[][] copyOfGrids(){
        
        int [][] copy = new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N; j++){
                copy[i][j]=grids[i][j].getCurrentValue();
            }
          
        }
        return copy;
    }
}
