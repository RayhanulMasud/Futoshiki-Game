/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshiki;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;


public class View extends JFrame implements ActionListener
{
   
   int count;
   private JButton[] placing;
  
   
   private JButton[][] grid;
  
   
   private Board game;
   
   private Cell[][] grids;
   private JButton nextButton;
   private JButton previousButton;
   private JButton fromStartButton;
   private JButton fromLastButton;
   private ArrayList<Solutions> sol;
   private int N;
   private JButton label;
   
  public View(int N)
  {
     

    this.N= N;
    
    setLayout(new BorderLayout());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Futoshiki");
    setPreferredSize(new Dimension(800, 600));
       
       JPanel piecesPanel = new JPanel();
       piecesPanel.setLayout(new GridLayout(N,N));
       grid = new JButton[N][N];
       for(int i = 0; i <N; i++)
       {
         for(int j = 0; j < N; j++)
         {
           grid[i][j] = new JButton("");
           
           piecesPanel.add(grid[i][j]);
           grid[i][j].setBackground(Color.BLACK);
           grid[i][j].setForeground(Color.MAGENTA);
           grid[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
         }
       }       
       
       JPanel userClickPanel = new JPanel();
       userClickPanel.setLayout(new GridLayout(1,4));
       
       nextButton= new JButton("Next");
       nextButton.addActionListener(this);
       userClickPanel.add(nextButton);
       
       previousButton= new JButton("Previous");
       previousButton.addActionListener(this);
       userClickPanel.add(previousButton);
       
       fromStartButton= new JButton("From First");
       fromStartButton.addActionListener(this);
       userClickPanel.add(fromStartButton);
       
       fromLastButton= new JButton("From Last");
       fromLastButton.addActionListener(this);
       userClickPanel.add(fromLastButton);
        
       
       label = new JButton();
       label.setText("");
       JPanel performancePanel = new JPanel();
       performancePanel.setLayout(new GridLayout(1,1));
       performancePanel.add(label);
       
       
       add(performancePanel, BorderLayout.WEST);
       add(userClickPanel, BorderLayout.SOUTH);
       add(piecesPanel, BorderLayout.CENTER);
       //
       
       pack();
       setVisible(true);
  }
  
  void setBoards(ArrayList<Solutions> sol, int variableCount, int valueCount){
      
      this.sol= sol;
      count= sol.size();
      label.setText("Var Count:"+variableCount+" Val Count:"+valueCount);
      
  }

    @Override
    public void actionPerformed(ActionEvent e) {
      
        
        if(e.getSource()==nextButton){
            
            count++;
            if(count==sol.size()){
                return ;
            }
            
           
            showBoard();
            //System.out.println("count:"+count+"sol"+Solutions.solutionNum);
        }
        else if(e.getSource()==previousButton){
            count--;
            if(count<0){
                count=0;
            }
            showBoard();
        }
        else if(e.getSource()==fromStartButton){
            count =0;
            showBoard();
        }
        else if(e.getSource()==fromLastButton){
            count=sol.size()-1;
            showBoard();
        }
    }
    
    void showBoard(){
         for(int i=0;i<this.N;i++){
                for(int j=0;j<this.N;j++){
                    grid[i][j].setText("" + sol.get(count).grids[i][j]);
                }
            }
    }
    
    ArrayList<Solutions> board;
    int selectedFile = 0;
  
 }
