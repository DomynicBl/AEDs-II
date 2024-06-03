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
            setWizard(dados[17]);// alterar
        }
    }
}

class Pilha {
    private Personagem[] array;
    private int n;

    public Pilha() {
        this(6);
    }

    public Pilha(int tamanho) {
        array = new Personagem[tamanho];
        n = 0;
    }

    public void inserirFim(Personagem x) throws Exception {
        if (n >= array.length) {
            throw new Exception("Erro ao inserir!");
        }
        array[n] = x;
        n++;
    }

    public Personagem removerFim() throws Exception {
        if (n == 0) {
            throw new Exception("Erro ao remover!");
        }
        return array[--n];
    }

    public void mostrar() {
        System.out.print("[ ");
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("]");
    }

    public void imprimirPilha() {
        for (int i = n-1,j=0; i >= 0 && j<n; i--, j++) {
            System.out.print("["+j+" ## ");
            array[i].imprime();
        }
    }
    
    public boolean pesquisar(Personagem x) {
        boolean retorno = false;
        for (int i = 0; i < n && retorno == false; i++) {
            retorno = (array[i] == x);
        }
        return retorno;
    }
}

class Pilha_Alocacao_Sequencial {
    public static void main(String[] args) {
        Personagem[] personagens = new Personagem[500];
        Pilha pilhapersonagens = new Pilha(100);
        int j = 0;
        FileReader filereader;
        BufferedReader reader;
        Scanner entrada = new Scanner(System.in);
        String codigo;
        String nomeArquivo = "/tmp/characters.csv";
        int numoperacoes;
        int k = 0;
        String comandos;

        try {
            codigo = entrada.nextLine();
            String linha;
            Boolean existe = true;

            while (!(codigo.equals("FIM"))) {
                filereader = new FileReader(nomeArquivo);
                reader = new BufferedReader(filereader);
                reader.readLine();

                while ((linha = reader.readLine()) != null) {
                    for (int i = 0; i < 32; i++) {
                        if (linha.charAt(i) != codigo.charAt(i)) {
                            existe = false;
                            i = 32;
                        } else {
                            existe = true;
                        }
                    }

                    if (existe) {
                        Personagem personagem = new Personagem();
                        personagem.ler(linha);
                        try {
                            pilhapersonagens.inserirFim(personagem);
                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                        j++;
                    }
                }
                codigo = entrada.nextLine();
                filereader.close();
                reader.close();
            }
            numoperacoes = entrada.nextInt();

            while (k < numoperacoes) {
                comandos = entrada.next().trim();
                filereader = new FileReader(nomeArquivo);
                reader = new BufferedReader(filereader);
                reader.readLine();

                if (comandos.equals("I")) {
                    codigo = entrada.nextLine().trim();

                    while ((linha = reader.readLine()) != null) {

                        for (int i = 0; i < 32; i++) {
                            if (linha.charAt(i) != codigo.charAt(i)) {
                                existe = false;
                                i = 32;
                            } else {
                                existe = true;
                            }

                        }

                        if (existe) {
                            Personagem personagem = new Personagem();
                            personagem.ler(linha);
                            try {
                                pilhapersonagens.inserirFim(personagem);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                else if (comandos.equals("R")) {
                    try {
                        System.out.println("(R) "+pilhapersonagens.removerFim().getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } 
                filereader.close();
                reader.close();
                k++;
            }
            System.out.println("[ Top ]");
            pilhapersonagens.imprimirPilha();
            System.out.println("[ Bottom ]");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        entrada.close();
    }
}