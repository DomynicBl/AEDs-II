import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Personagem {
    private String id;
    private String name;
    private ArrayList<String> alternate_names;
    private String house;
    private String ancestry;
    private String species;
    private String patronus;
    private Boolean hogwartsStaff;
    private Boolean hogwartsStudent;
    private String actorName;
    private Boolean alive;
    private Date dateOfBirth;
    private int yearOfBirth;
    private String eyeColour;
    private String gender;
    private String hairColour;
    private Boolean wizard;
    private SimpleDateFormat formatodedata = new SimpleDateFormat("dd-MM-yyyy");

    public Personagem() {
        this.id = null;
        this.name = null;
        this.alternate_names = new ArrayList<String>();
        this.house = null;
        this.ancestry = null;
        this.species = null;
        this.patronus = null;
        this.hogwartsStaff = null;
        this.hogwartsStudent = null;
        this.actorName = null;
        this.alive = null;
        this.dateOfBirth = null;
        this.yearOfBirth = 0;
        this.eyeColour = null;
        this.gender = null;
        this.hairColour = null;
        this.wizard = null;
    }

    public Personagem(String id, String name, ArrayList<String> alternate_names, String house, String ancestry,
            String species, String patronus, Boolean hogwartsStaff, Boolean hogwartsStudent, String actorName,
            Boolean alive, String dateOfBirth, int yearOfBirth, String eyeColour, String gender, String hairColour,
            Boolean wizard) {
        this.id = id;
        this.name = name;
        this.alternate_names = alternate_names;
        this.house = house;
        this.ancestry = ancestry;
        this.species = species;
        this.patronus = patronus;
        this.hogwartsStaff = hogwartsStaff;
        this.hogwartsStudent = hogwartsStudent;
        this.actorName = actorName;
        this.alive = alive;
        setDate(dateOfBirth);
        this.yearOfBirth = yearOfBirth;
        this.eyeColour = eyeColour;
        this.gender = gender;
        this.hairColour = hairColour;
        this.wizard = wizard;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getList() {

        return alternate_names;
    }

    public void setList(String todosapelidos) {
        this.alternate_names.add(todosapelidos);
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getAncestry() {
        return ancestry;
    }

    public void setAncestry(String ancestry) {
        this.ancestry = ancestry;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getPatronus() {
        return patronus;
    }

    public void setPatronus(String patronus) {
        this.patronus = patronus;
    }

    public Boolean getHogwartsStaff() {
        return hogwartsStaff;
    }

    public void setHogwartsStaff(String condicaostaff) {
        if (condicaostaff.equals("VERDADEIRO")) {
            this.hogwartsStaff = true;
        } else {
            this.hogwartsStaff = false;
        }
    }

    public Boolean getHogwartsStudent() {
        return hogwartsStudent;
    }

    public void setHogwartsStudent(String hogwartsStudent) {
        if (hogwartsStudent.equals("VERDADEIRO")) {
            this.hogwartsStudent = true;
        } else {
            this.hogwartsStudent = false;
        }
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public Boolean getAlive() {
        return alive;
    }

    public void setAlive(String vivo) {
        if (vivo.equals("VERDADEIRO")) {
            this.alive = true;
        } else {
            this.alive = false;
        }
    }

    public Date getDate() {
        return dateOfBirth;
    }

    public void setDate(String dataoriginal) {
        try {
            this.dateOfBirth = formatodedata.parse(dataoriginal);
        } catch (ParseException e) {
            System.out.println("Erro ao converter a data: " + e.getMessage());
        }
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int anoConvertido) {
        this.yearOfBirth = anoConvertido;

    }

    public String getEyeColour() {
        return eyeColour;
    }

    public void setEyeColour(String eyeColour) {
        this.eyeColour = eyeColour;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHairColour() {
        return hairColour;
    }

    public void setHairColour(String hairColour) {
        this.hairColour = hairColour;
    }

    public Boolean getWizard() {
        return wizard;
    }

    public void setWizard(String mago) {
        if (mago.equals("VERDADEIRO")) {

            this.wizard = true;
        } else {
            this.wizard = false;
        }
    }

    public Personagem clone() {
        Personagem temp = new Personagem();
        temp.setYearOfBirth(this.yearOfBirth);
        temp.setName(this.name);
        return temp;
    }

    public void imprime() {
        System.out.print(  getId() + " ## " + getName() + " ## ");
        System.out.print("{");
        for (int i = 0; i < alternate_names.size(); i++) {
            System.out.print(alternate_names.get(i));
            if (i < alternate_names.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("} ");
        System.out.println("## " + getHouse() + " ## " + getAncestry() + " ## " + getSpecies() + " ## " + getPatronus()
                + " ## " + getHogwartsStaff() + " ## " + getHogwartsStudent() + " ## " + getActorName() + " ## "
                + getAlive() + " ## " + formatodedata.format(getDate()) + " ## " + getYearOfBirth() + " ## "
                + getEyeColour() + " ## " + getGender() + " ## " + getHairColour() + " ## " + getWizard() + "]");
    }

    public void ler(String linha) {
        String[] dados = linha.split(";");
        if (dados.length >= 17) {
            setId(dados[0]);
            setName(dados[1]);

            String regex = "\\s+'|\\[|\\]|\\'";
            dados[2] = dados[2].replaceAll(regex, "");
            String[] temp = dados[2].split(",");
            for (int i = 0; i < temp.length; i++) {
                setList(temp[i]);
            }
            setHouse(dados[3]);
            setAncestry(dados[4]);
            setSpecies(dados[5]);
            setPatronus(dados[6]);
            setHogwartsStudent(dados[8]);
            setActorName(dados[9]);
            setAlive(dados[10]);
            setDate(dados[12]);

            try {
                int anoConvertido = Integer.parseInt(dados[13]);

                setYearOfBirth(anoConvertido);

            } catch (NumberFormatException e) {
                System.out.println("Erro ao converter a string para inteiro: " + e.getMessage());
            }

            setEyeColour(dados[14]);
            setGender(dados[15]);
            setHairColour(dados[16]);
            setWizard(dados[17]);
        }
    }
}

class Celula {
    public int elemento;
    public Celula prox;

    public Celula() {
        this(0, null);
    }

    public Celula(int elemento) {
        this(elemento, null);
    }

    public Celula(int elemento, Celula prox) {
        this.elemento = elemento;
        this.prox = prox;
    }
}

class Matriz {
    private Celula[][] matriz;
    private int linhas;
    private int colunas;

    public Matriz(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        matriz = new Celula[linhas][colunas];
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                matriz[i][j] = new Celula();
            }
        }
    }

    public void setElemento(int linha, int coluna, int valor) {
        matriz[linha][coluna].elemento = valor;
    }

    public int getElemento(int linha, int coluna) {
        return matriz[linha][coluna].elemento;
    }

    public Matriz soma(Matriz outra) {
        if (this.linhas != outra.linhas || this.colunas != outra.colunas) {
            throw new IllegalArgumentException("Matrizes de tamanhos diferentes não podem ser somadas.");
        }

        Matriz resultante = new Matriz(this.linhas, this.colunas);
        for (int i = 0; i < this.linhas; i++) {
            for (int j = 0; j < this.colunas; j++) {
                resultante.setElemento(i, j, this.getElemento(i, j) + outra.getElemento(i, j));
            }
        }
        return resultante;
    }

    public Matriz multiplicacao(Matriz outra) {
        if (this.colunas != outra.linhas) {
            throw new IllegalArgumentException("Matrizes de tamanhos incompatíveis para multiplicação.");
        }

        Matriz resultante = new Matriz(this.linhas, outra.colunas);
        for (int i = 0; i < this.linhas; i++) {
            for (int j = 0; j < outra.colunas; j++) {
                int soma = 0;
                for (int k = 0; k < this.colunas; k++) {
                    soma += this.getElemento(i, k) * outra.getElemento(k, j);
                }
                resultante.setElemento(i, j, soma);
            }
        }
        return resultante;
    }

    public void mostrarDiagonalPrincipal() {
        for (int i = 0; i < Math.min(linhas, colunas); i++) {
            System.out.print(getElemento(i, i) + " ");
        }
        System.out.println();
    }

    public void mostrarDiagonalSecundaria() {
        for (int i = 0; i < Math.min(linhas, colunas); i++) {
            System.out.print(getElemento(i, colunas - i - 1) + " ");
        }
        System.out.println();
    }

    public void imprimir() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                System.out.print(getElemento(i, j) + " ");
            }
            System.out.println();
        }
    }
}

public class Matriz_Dinamica {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCasos = scanner.nextInt();

        for (int caso = 0; caso < numCasos; caso++) {

            int linhas1 = scanner.nextInt();
            int colunas1 = scanner.nextInt();
            Matriz matriz1 = new Matriz(linhas1, colunas1);
            for (int i = 0; i < linhas1; i++) {
                for (int j = 0; j < colunas1; j++) {
                    matriz1.setElemento(i, j, scanner.nextInt());
                }
            }

            int linhas2 = scanner.nextInt();
            int colunas2 = scanner.nextInt();
            Matriz matriz2 = new Matriz(linhas2, colunas2);
            for (int i = 0; i < linhas2; i++) {
                for (int j = 0; j < colunas2; j++) {
                    matriz2.setElemento(i, j, scanner.nextInt());
                }
            }

            matriz1.mostrarDiagonalPrincipal();
            matriz1.mostrarDiagonalSecundaria();

            Matriz soma = matriz1.soma(matriz2);
            soma.imprimir();

            Matriz multiplicacao = matriz1.multiplicacao(matriz2);
            multiplicacao.imprimir();
        }

        scanner.close();
    }
}