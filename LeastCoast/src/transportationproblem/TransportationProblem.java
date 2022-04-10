package transportationproblem;

import java.util.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class TransportationProblem {
    
    // Initialisation    
    double []required;
    double []stock;
    double [][]cost;
    LinkedList<Variable> feasible = new LinkedList<Variable>();
    
    int stockSize;    
    int requiredSize;    
    
    
    public TransportationProblem(int stockSize, int requiredSize ){
    // initialisation du probleme
    	this.stockSize = stockSize;
        this.requiredSize = requiredSize;
        
        stock = new double[stockSize];
        required = new double[requiredSize];
        cost = new double[stockSize][requiredSize];
        
        for(int i=0; i < (requiredSize + stockSize -1); i++)
            feasible.add(new Variable());

    }
    
    public void setStock(double value, int index){
        stock[index] = value;        
    }
    
    public void setRequired(double value, int index){
        required[index] = value;        
    }
    
    
    public void setCost(double value, int stock, int required){
        cost[stock][required] = value;
    }
    
        
    // initialisation de la liste des solutions réalisables à l'aide de la règle du moindre coût
    public double leastCostRule() {
        long start = System.nanoTime();

        double min;        
        int k = 0; //compteur de solutions réalisables
        
        //isSet est chargé d'annoter les cellules qui ont été attribuées
        boolean [][]isSet = new boolean[stockSize][requiredSize];        
        for (int j = 0; j < requiredSize; j++)
            for (int i = 0;  i < stockSize; i++)
                    isSet[i][j] = false;
        
        int i = 0, j = 0;
        Variable minCost = new Variable();
        
        //while loop est responsable de la sélection des cellules candidates par leur moindre coût
        while(k < (stockSize + requiredSize - 1)){
            
            minCost.setValue(Double.MAX_VALUE);            
            //choisir la cellule la moins coûteuse          
            for (int m = 0;  m < stockSize; m++)
                for (int n = 0; n < requiredSize; n++)
                    if(!isSet[m][n])
                        if(cost[m][n] < minCost.getValue()){
                            minCost.setStock(m);
                            minCost.setRequired(n);
                            minCost.setValue(cost[m][n]);
                        }            
            
            i = minCost.getStock();
            j = minCost.getRequired();            
            
            //allocation du stock de la bonne manière
            min = Math.min(required[j], stock[i]);

            feasible.get(k).setRequired(j);
            feasible.get(k).setStock(i);
            feasible.get(k).setValue(min);
            k++;

            required[j] -= min;
            stock[i] -= min;
            
            //allouer des valeurs nulles dans la ligne/colonne supprimée
            if(stock[i] == 0)
                for(int l = 0; l < requiredSize; l++)
                    isSet[i][l] = true;                    
            else
                for(int l = 0; l < stockSize; l++)
                    isSet[l][j] = true;

        }
        
        return (System.nanoTime() - start) * 1.0e-9;    
            
    }
    
    public double getSolution(){
        double result = 0;
        for(Variable x: feasible){
            result += x.getValue() * cost[x.getStock()][x.getRequired()];
        }
        
        return result;
    
    }
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please enter the problem size:");
        int s = scanner.nextInt();
        int r = scanner.nextInt();
        double x;
        TransportationProblem test = new TransportationProblem(s, r);
        
        System.out.println("Please enter the stocks capacity:");
        for (int i = 0; i < test.stockSize; i++){
            x = scanner.nextDouble();
            test.setStock(x, i);
        }
        
        System.out.println("Please enter the requirements:");
        for (int i = 0; i < test.requiredSize; i++){
            x = scanner.nextDouble();
            test.setRequired(x, i);
        }
        
        System.out.println("Please enter the transportation costs:");
        for (int i = 0; i < test.stockSize; i++)
            for (int j = 0; j < test.requiredSize; j++) {
                x = scanner.nextDouble();
                test.setCost(x, i, j);
            }
        
        test.leastCostRule();
        
        for(Variable t: test.feasible){
            System.out.println(t);        
        }
        
        System.out.println("la solution est: " + test.getSolution());
        
    }
   
}