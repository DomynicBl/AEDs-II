import java.util.Random;
import java.util.Scanner;

public class AlteracaoAleatoria {
    public static void main(String[] args) {
        Random gerador = new Random();
        gerador.setSeed(4);

        Scanner scanner = new Scanner(System.in);
        String frase;

        while (true) {
            frase = scanner.nextLine();

            if (frase.equalsIgnoreCase("FIM")) {
                break;
            }

            char letra1 = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));
            char letra2 = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));

            String resultado = Alterar(frase, letra1, letra2);

            System.out.println(resultado);
        }
    }

    public static String Alterar(String frase, char letra1, char letra2) {
        StringBuilder resultado = new StringBuilder();

        for (char c : frase.toCharArray()) {
            if (c == letra1) {
                resultado.append(letra2);
            } else {
                resultado.append(c);
            }
        }

        return resultado.toString();
    }
}