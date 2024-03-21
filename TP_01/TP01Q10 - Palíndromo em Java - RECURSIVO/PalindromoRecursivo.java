import java.util.*;

public class PalindromoRecursivo{
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        String palavra = sc.nextLine();

        while(!palavra.equals("FIM")){
            String palavraInvertida = "";
            for(int i = (palavra.length() -1); i>=0; i--){
                palavraInvertida += palavra.charAt(i);
            }
                if(palavraInvertida.equals(palavra)){
                    System.out.println("SIM");
                }else{
                    System.out.println("NAO");
                } 
            palavra = sc.nextLine();
        }
    }
}