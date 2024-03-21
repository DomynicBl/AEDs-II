import java.util.*;

public class Is{
    public static void main(String[] args){

        Scanner sc = new Scanner (System.in);
        String palavra = sc.nextLine();

        while(!palavra.equals("FIM")){

            boolean isVogal = IsVogal(palavra);
            boolean isConsoante = IsConsoante(palavra);
            boolean isInteiro = IsInteiro(palavra);
            boolean isReal = IsReal(palavra);
            
            System.out.println((isVogal ? "SIM" : "NAO") + " " +
                               (isConsoante ? "SIM" : "NAO") + " " +
                               (isInteiro ? "SIM" : "NAO") + " " +
                               (isReal ? "SIM" : "NAO"));
            
            palavra = sc.nextLine();
        }
    }

    public static boolean IsVogal(String palavra){
        return palavra.matches("^[aAeEiIoOuU]+$");
    }

    public static boolean IsConsoante(String palavra){
        return palavra.matches("^[bBcCdDfFgGhHjJkKlLmMnNpPqQrRsStTvVxXyYzZ]+$");
    }

    public static boolean IsInteiro(String palavra){
        return palavra.matches("^[-+]?\\d+$");
    }

    public static boolean IsReal(String palavra){
        return palavra.matches("^[-+]?\\d*\\[.,]?\\d+");
    }
}