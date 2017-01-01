/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshiki;

import java.util.ArrayList;

/**
 *
 * @author MASUD
 */
public class Cell {
    
    private int domainSize;
    private boolean hasConstraintChoto=false;
    private boolean hasConstraintBoro= false;
    private int currentValue ;
    private boolean alter = true;
    
    private int [] domain;
    
    private int remainingValue ;
    
    
    
    private ArrayList<Position> constraintBoro= new ArrayList<Position>();
    private ArrayList<Position> constraintChoto= new ArrayList<Position>();
    
    private int constraintRowChotoGhor = -1;
    private int constraintColChotoGhor = -1;
    
    private int constraintRowBoroGhor = -1;
    private int constraintColBoroGhor = -1;
    
    
    
    Cell(int currentValue, int domainSize){
        this.currentValue = currentValue;
        this.domainSize= domainSize;
        
        if(currentValue!=0){
            alter = false;
            remainingValue = 0;
        }else{
            remainingValue=this.domainSize;
            domain= new int[this.domainSize];
            
            for(int i=0;i<domainSize;i++){
                domain[i]= i+1;
            }
        }
    }
    
    
    boolean canAlter(){
        return alter;
    }
    
    int getRemainingValue(){
        return remainingValue;
    }
    
    int getCurrentValue(){
        return currentValue;
    }
    
    int[] getDomain(){
        return domain;
    }
    
    /*void setConstraintRowChotoGhor(int constraintRow){
        this.constraintRowChotoGhor = constraintRow;
        this.hasConstraintChoto=true;
    }
    
    void setConstraintColChotoGhor(int constraintCol){
        this.constraintColChotoGhor = constraintCol;
        this.hasConstraintChoto=true;
    }*/
    
    void setConstraintRowColChotoGhor(int constraintRow, int constraintCol){
        
        Position pos = new Position();
        pos.col= constraintCol;
        pos.row= constraintRow;
        constraintChoto.add(pos);
        this.hasConstraintChoto = true;
    }
    
    
    /*void setConstraintRowBoroGhor(int constraintRow){
        this.constraintRowBoroGhor = constraintRow;
        this.hasConstraintBoro=true;
    }
    
    void setConstraintColBoroGhor(int constraintCol){
        this.constraintColBoroGhor = constraintCol;
        this.hasConstraintBoro=true;
    }*/
    
    void setConstraintRowColBoroGhor(int constraintRow, int constraintCol){
        
        Position pos = new Position();
        pos.col= constraintCol;
        pos.row= constraintRow;
        constraintBoro.add(pos);
        this.hasConstraintBoro=true;
    }
    
    boolean getConstraintStatusChoto(){
        return this.hasConstraintChoto;
    }
    
    boolean getConstraintStatusBoro(){
        return this.hasConstraintBoro;
    }
    
    ArrayList<Position> getConstraintRowColChotoGhor(){
        return constraintChoto;
    }
    
    ArrayList<Position> getConstraintRowColBoroGhor(){
        return constraintBoro;
    }
    
    /*int getConstraintRowChotoGhor(){
        return this.constraintRowChotoGhor;
    }
    
    int getConstraintColChotoGhor(){
        return this.constraintColChotoGhor;
    }
    
    int getConstraintRowBoroGhor(){
        return this.constraintRowBoroGhor;
    }
    
    int getConstraintColBoroGhor(){
        return this.constraintColBoroGhor;
    }*/
    
    void setCurrentValue(int currentValue){
       this.currentValue=currentValue;
    }
    
    void decreaseRemainingValue(){
        this.remainingValue--;
    }
    
    void increaseRemainingValue(){
        this.remainingValue++;
    }
    
    void resetRemainingValue(){
        this.remainingValue = this.domainSize;
    }
    
    void resetDomain(){
        
        for(int i=0 ; i<this.domainSize ; i++){
            domain[i]=i+1;
        }
    }
    
    
    
    
}
