import java.util.*;

public class CifraCesarRecursiva{
    public static void main(String[] args){

        Scanner sc = new Scanner (System.in, "UTF-8");
        String palavra = sc.nextLine();

        while(!palavra.equals("FIM")){
            String palavraCifrada = "";

            Cifrar(palavra);
            palavraCifrada = Cifrar(palavra);

            System.out.println(palavraCifrada);
            palavra = sc.nextLine();
        }
    }

    public static String Cifrar(String palavra){
        String palavraCifrada = "";
        
        for(int i=0; i<palavra.length(); i++){
            char caracter = palavra.charAt(i);
            if(caracter == '\uFFFD'){
                palavraCifrada += caracter;
            }else{
                palavraCifrada += (char)(palavra.charAt(i) + 3);
            }
        }
        
        return palavraCifrada;
    }
}