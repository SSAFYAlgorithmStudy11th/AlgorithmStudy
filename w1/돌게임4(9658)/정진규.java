import java.io.*;
import java.util.*;

public class Main {
    

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] game = new int[1001];
        
        //0은 상근 , 1은 창영
      
        game[1] = 1;
        game[3] = 1;
        
        for(int i = 6;i<=n;i++) {
        	if(game[i-1] + game[i-3] + game[i-4] == 0) {
        		game[i] = 1;
        	}
        }
        
 
        if(game[n]==0) {
        	System.out.println("SK");
        }
        else
        	System.out.println("CY");

    }
    
    
   
   
}
