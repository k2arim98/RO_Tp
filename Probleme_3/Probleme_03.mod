/*********************************************
 * OPL 12.7.0.0 Model
 * Author: Hp
 * Creation Date: 08 avril. 2022 at 11:17:51
 *********************************************/
int nbSommet = ...; //nombre de sommets;

range I = 1..nbSommet;
int c[I][I] = ...;

dvar int+ F[I][I]; 

maximize sum(i in I)F[i,12];


subject to{

	forall(i in I){
		forall(j in I){
			F[i,j] <= c[i,j];		
		}	
	}
	
	forall(j in I){
		if(j != 11 && j != 12){
			sum(i in I)F[i,j] == sum(i in I)F[j,i];		
		}	
	}
}
 