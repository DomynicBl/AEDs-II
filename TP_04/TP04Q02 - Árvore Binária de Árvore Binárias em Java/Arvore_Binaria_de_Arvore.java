import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Personagem {
    private String id;
    private String name;
    private List<String> alternate_names;
    private String house;
    private String ancestry;
    private String species;
    private String patronus;
    private boolean hogwartsStaff;
    private boolean hogwatsStudent;
    private String actorName;
    private boolean alive;
    private Date dateOfBirth;
    private int yearOfBith;
    private String eyeColour;
    private String gender;
    private String hairColor;
    private boolean wizard;



    public Personagem(String id, String name, List<String> alternate_names, String house, String ancestry, String species,
            String patronus, boolean hogwartsStaff, boolean hogwatsStudent, String actorName, boolean alive,
            Date dateOfBirth, int yearOfBith, String eyeColour, String gender, String hairColor, boolean wizard) {
        this.id = id;
        this.name = name;
        this.alternate_names = alternate_names;
        this.house = house;
        this.ancestry = ancestry;
        this.species = species;
        this.patronus = patronus;
        this.hogwartsStaff = hogwartsStaff;
        this.hogwatsStudent = hogwatsStudent;
        this.actorName = actorName;
        this.alive = alive;
        this.dateOfBirth = dateOfBirth;
        this.yearOfBith = yearOfBith;
        this.eyeColour = eyeColour;
        this.gender = gender;
        this.hairColor = hairColor;
        this.wizard = wizard;
    }

    public Personagem() {
        this.id = "";
        this.name = "";
        this.alternate_names = new ArrayList<>();
        this.house = "";
        this.ancestry = "";
        this.species = "";
        this.patronus = "";
        this.hogwartsStaff = false;
        this.hogwatsStudent = false;
        this.actorName = "";
        this.alive = false;
        this.dateOfBirth = Date.from(null);
        this.yearOfBith = 0;
        this.eyeColour = "";
        this.gender = "";
        this.hairColor = "";
        this.wizard = false;
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

    public List<String> getAlternate_names() {
        return alternate_names;
    }

    public void setAlternate_names(List<String> alternate_names) {
        this.alternate_names = alternate_names;
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

    public boolean isHogwartsStaff() {
        return hogwartsStaff;
    }

    public void setHogwartsStaff(boolean hogwartsStaff) {
        this.hogwartsStaff = hogwartsStaff;
    }

    public boolean getHogwatsStudent() {
        return hogwatsStudent;
    }

    public void setHogwatsStudent(boolean hogwatsStudent) {
        this.hogwatsStudent = hogwatsStudent;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getYearOfBith() {
        return yearOfBith;
    }

    public void setYearOfBith(int yearOfBith) {
        this.yearOfBith = yearOfBith;
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

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public boolean isWizard() {
        return wizard;
    }

    public void setWizard(boolean wizard) {
        this.wizard = wizard;
    }

    public void imprimir(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("[" + id +" ## " + name +" ## "+ alternate_names.toString().replace('[', '{').replace(']', '}') + " ## " + house + " ## " + ancestry + " ## " + species + " ## " + patronus + " ## " + hogwartsStaff + 
        " ## " + hogwatsStudent + " ## " + actorName + " ## " + alive + " ## " + formatter.format(dateOfBirth) + " ## " + yearOfBith + " ## " + eyeColour + " ## " + gender + " ## " + hairColor + " ## " + wizard + "]");
    }

    public Personagem clonar(){
        return this;
    }

    public static String[] ler(String dados){
        String[] atributos = dados.split(";");
        
        return atributos;
    }
}

class SubNo{
    public Personagem personagem;
    public SubNo esq,dir;

    public SubNo(){
        this(null);
    }

    public SubNo(Personagem personagem) {
        this.personagem = personagem;
        this.esq = null;
        this.dir = null;
    }
}

class No{
    public int elemento;
    public No esq,dir;
    public SubNo subArvore;

    public No(){
        this(0);
    }

    public No(int elemento) {
        this.elemento = elemento;
        this.esq = null;
        this.dir = null;
        this.subArvore = null;
    }
}

class ArvoreBinaria{
    private No raiz;

    public ArvoreBinaria() {
        try {
            raiz = inserirArvorePrincipal(7, raiz);
            raiz = inserirArvorePrincipal(3, raiz);
            raiz = inserirArvorePrincipal(11, raiz);
            raiz = inserirArvorePrincipal(1, raiz);
            raiz = inserirArvorePrincipal(5, raiz);
            raiz = inserirArvorePrincipal(9, raiz);
            raiz = inserirArvorePrincipal(13, raiz);
            raiz = inserirArvorePrincipal(0, raiz);
            raiz = inserirArvorePrincipal(2, raiz);
            raiz = inserirArvorePrincipal(4, raiz);
            raiz = inserirArvorePrincipal(6, raiz);
            raiz = inserirArvorePrincipal(8, raiz);
            raiz = inserirArvorePrincipal(10, raiz);
            raiz = inserirArvorePrincipal(12, raiz);
            raiz = inserirArvorePrincipal(14, raiz);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private No inserirArvorePrincipal(int x, No i) throws Exception{

        if(i == null){
            i = new No(x);
        }else if(x < i.elemento){
            i.esq = inserirArvorePrincipal(x, i.esq);
        }else if(x > i.elemento){
            i.dir = inserirArvorePrincipal(x, i.dir);
        }else{
            throw new Exception("Elemento já inserido!");
        }

        return i;
    }

    public void caminharCentral(){
        caminharCentral(raiz);
    }

    private void caminharCentral(No i){
        if(i != null){
            caminharCentral(i.esq);
            System.out.println(i.elemento + " ");
            caminharCentralSubArvore(i.subArvore);
            caminharCentral(i.dir);
        }

    }

    private void caminharCentralSubArvore(SubNo i){
        if(i != null){
            caminharCentralSubArvore(i.esq);
            System.out.println(i.personagem.getName() + " ");
            caminharCentralSubArvore(i.dir);
        }

    }

    public void inserir(Personagem personagem) throws Exception{
        inserir(raiz, personagem);
    }

    private void inserir(No i, Personagem personagem) throws Exception{

        if(i == null){
            throw new Exception("Posição de inserção não encontrada");
        }

        if((personagem.getYearOfBith() % 15) == i.elemento){
            i.subArvore = inserirSubArvore(i.subArvore, personagem);
        }else if((personagem.getYearOfBith() % 15) > i.elemento){
            inserir(i.dir, personagem);
        }else{
            inserir(i.esq, personagem);
        }
    }

    private SubNo inserirSubArvore(SubNo i, Personagem personagem) throws Exception{
        if(i == null){
            i = new SubNo(personagem);
        }else if(i.personagem.getName().compareTo(personagem.getName()) < 0){
            i.dir = inserirSubArvore(i.dir, personagem);
        }else if(i.personagem.getName().compareTo(personagem.getName()) > 0){
            i.esq = inserirSubArvore(i.esq, personagem);
        }else{
            throw new Exception("Erro ao realizar a inserção: Elemento já inserido na Árvore");
        }

        return i;
    }


    public void pesquisar(String nome, int comparacoes[]){
        System.out.print(nome + " => raiz");

        boolean resp = pesquisar(raiz, nome, comparacoes);

        if(resp == true){
            System.out.println(" SIM");
        }else{
            System.out.println(" NAO");
        }
    }

    private boolean pesquisar(No i, String nome, int comparacoes[]){
        boolean resp = false;
        if(i != null){
            if(i.subArvore != null){
                resp = pesquisarSubArvore(i.subArvore, nome, comparacoes);
            }

            if(!resp){
                System.out.print(" ESQ");
                resp = pesquisar(i.esq, nome, comparacoes);
            }

            if(!resp){
                System.out.print(" DIR");
                resp = pesquisar(i.dir, nome, comparacoes);
            }
        }

        return resp;
    }

    private boolean pesquisarSubArvore(SubNo i, String nome, int comparacoes[]){
        boolean resp;

        if(i == null){
            comparacoes[0]++;
            resp = false;
        }else if(i.personagem.getName().equals(nome)){
            comparacoes[0] += 2;
            resp = true;
        }else if(i.personagem.getName().compareTo(nome) < 0){
            comparacoes[0] += 3;
            System.out.print("->dir");
            resp = pesquisarSubArvore(i.dir, nome, comparacoes);
        }else{
            comparacoes[0] += 3;
            System.out.print("->esq");
            resp = pesquisarSubArvore(i.esq, nome, comparacoes);
        }

        return resp;
    }
    
}

class Lista {
    private List<String> Lista;

    Lista(){
        this.Lista = new ArrayList<>();
    }

    public List<String> getLista() {
        return Lista;
    }

    public void setLista(List<String> lista) {
        Lista = lista;
    }

    public List<String> parseStringToList(String texto){
        Pattern pattern = Pattern.compile("'(.*?)'");
        Matcher matcher = pattern.matcher(texto);

        while (matcher.find()) {
            Lista.add(matcher.group(1));
        }
        return Lista;
    }
}

public class Arvore_Binaria_de_Arvore {
    public static void preencherVetor(Personagem[] personagens){
        String line;
        String[] atributos;
        Lista alternate_names;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dateOfBirth;
        try {
            Scanner sc = new Scanner(new File("/tmp/characters.csv"));
            int i = 0;
            sc.nextLine();
            while(sc.hasNextLine()){
                line = sc.nextLine();
                atributos = Personagem.ler(line);
                alternate_names = new Lista();
                try {
                    dateOfBirth = formatter.parse(atributos[12]);
                    personagens[i] = new Personagem(atributos[0],atributos[1],alternate_names.parseStringToList(atributos[2]), atributos[3], atributos[4], atributos[5], atributos[6], atributos[7].equals("VERDADEIRO")? true: false, atributos[8].equals("VERDADEIRO")? true: false, atributos[9], 
                    atributos[10].equals("VERDADEIRO")? true: false,dateOfBirth, Integer.parseInt(atributos[13]),atributos[14],atributos[15],atributos[16], atributos[17].equals("VERDADEIRO")? true: false);
                    i++;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void log(long tempoExecucao,int comparacoes){
        File log = new File("matrícula_arvoreArvore.txt");
        double segundos =tempoExecucao / 1_000_000_000.0;

        try{
            PrintWriter writer = new PrintWriter( new FileWriter(log, true));
            writer.println("822331\t"+segundos+"\t"+comparacoes);
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        long inicio = System.nanoTime();
        Scanner sc = new Scanner(System.in);
        String id, name;
        int comparacoes[] = {0};

        Personagem[] personagens = new Personagem[405];
        preencherVetor(personagens);
        ArvoreBinaria arvoreBinaria = new ArvoreBinaria();
        
        id = sc.nextLine();
        while(id.equals("FIM") != true){
            for(int i = 0; i < 405; i++){
                if(personagens[i].getId().equals(id)){
                    try {
                        arvoreBinaria.inserir(personagens[i]);
                        i = 405;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            id = sc.nextLine();
        }

        name = sc.nextLine();
        while(name.equals("FIM") != true){
            arvoreBinaria.pesquisar(name, comparacoes);
            name = sc.nextLine();
        }

        long fim = System.nanoTime();

        long tempoExecucao = fim - inicio;

        log(tempoExecucao, comparacoes[0]);
        sc.close();
    }

}