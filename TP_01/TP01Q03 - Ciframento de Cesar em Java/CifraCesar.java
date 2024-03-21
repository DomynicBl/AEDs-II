import java.util.*;

public class CifraCesar{
    public static void main(String[] args){

        Scanner sc = new Scanner (System.in, "UTF-8");
        String palavra = sc.nextLine();

        while(!palavra.equals("FIM")){
            String palavraCifrada = "";

            for(int i=0; i<palavra.length(); i++){
                char caracter = palavra.charAt(i);
                if(caracter == '\uFFFD'){
                    palavraCifrada += caracter;
                }else{
                    palavraCifrada += (char)(palavra.charAt(i) + 3);
                }
            }
            System.out.println(palavraCifrada);
            palavra = sc.nextLine();
        }
    }
}