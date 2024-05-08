import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


class Lista {
    private String[] elementos;
    private int tamanho;

    public Lista(int capacidade) {
        this.elementos = new String[capacidade];
        this.tamanho = 0;
    }

    public void adicionar(String elemento) {
        this.elementos[this.tamanho] = elemento;
        this.tamanho++;
    }

    public String obter(int indice) {
        if (!(indice >= 0 && indice < this.tamanho)) {
            throw new IllegalArgumentException("Índice inválido");
        }
        return this.elementos[indice];
    }

    public int tamanho() {
        return this.tamanho;
    }
}

class Personagem {
    private String id;
    private String name;
    private Lista alternativeNames;
    private String house;
    private String ancestry;
    private String species;
    private String patronus;
    private boolean hogwartsStaff;
    private boolean hogwartsStudent;
    private String actorName;
    private Lista alternativeActors;
    private boolean alive;
    private LocalDate dateOfBirth;
    private String yearOfBirth;
    private String eyeColour;
    private String gender;
    private String hairColour;
    private boolean wizard;

    public Personagem() {
        id = "";
        name = "";
        alternativeNames = new Lista(10);
        house = "";
        ancestry = "";
        species = "";
        patronus = "";
        hogwartsStaff = false;
        hogwartsStudent = false;
        actorName = "";
        alternativeActors = new Lista(10);
        alive = false;
        dateOfBirth = null;
        yearOfBirth = "";
        eyeColour = "";
        gender = "";
        hairColour = "";
        wizard = false;
    }

    public void setId(String s) {
        id = s;
    }

    public String getId() {
        return id;
    }

    public void setName(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }

    public Lista getAlternativeNames() {
        return alternativeNames;
    }

    public void setHouse(String s) {
        house = s;
    }

    public String getHouse() {
        return house;
    }

    public void setAncestry(String s) {
        ancestry = s;
    }

    public String getAncestry() {
        return ancestry;
    }

    public void setSpecies(String s) {
        species = s;
    }

    public String getSpecies() {
        return species;
    }

    public void setPatronus(String s) {
        patronus = s;
    }

    public String getPatronus() {
        return patronus;
    }

    public void setHogwartsStaff(boolean x) {
        hogwartsStaff = x;
    }

    public boolean isHogwartsStaff() {
        return hogwartsStaff;
    }

    public void setHogwartsStudent(boolean x) {
        hogwartsStudent = x;
    }

    public boolean isHogwartsStudent() {
        return hogwartsStudent;
    }

    public void setActorName(String s) {
        actorName = s;
    }

    public String getActorName() {
        return actorName;
    }

    public Lista getAlternativeActors() {
        return alternativeActors;
    }

    public void setAlive(boolean x) {
        alive = x;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setDateOfBirth(LocalDate date) {
        dateOfBirth = date;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setYearOfBirth(String data) {
        yearOfBirth = data;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setEyeColour(String s) {
        eyeColour = s;
    }

    public String getEyeColour() {
        return eyeColour;
    }

    public void setGender(String s) {
        gender = s;
    }

    public String getGender() {
        return gender;
    }

    public void setHairColour(String s) {
        hairColour = s;
    }

    public String getHairColour() {
        return hairColour;
    }

    public void setWizard(boolean x) {
        wizard = x;
    }

    public boolean isWizard() {
        return wizard;
    }

    public void ler(String line) {
        String[] data = line.split(";");

        setId(data[0]);
        setName(data[1]);

        String[] alternateNames = data[2].replace("[", "").replace("]", "").split(",");
        for (int i = 0; i < alternateNames.length; i++) {
            String name = alternateNames[i].trim();
            name = name.replaceAll("^'|'$", "");
            if (!name.isEmpty()) {
                alternativeNames.adicionar(name);
            }
        }

        setHouse(data[3]);
        setAncestry(data[4]);
        setSpecies(data[5]);
        setPatronus(data[6]);
        setHogwartsStaff(data[7].equalsIgnoreCase("true"));
        setHogwartsStudent(data[8].equalsIgnoreCase("true"));
        setActorName(data[9]);
        setAlive(data[10].equalsIgnoreCase("true"));

        String[] alternateActors = data[11].replace("[", "").replace("]", "").split(",");
        for (int i = 0; i < alternateActors.length; i++) {
            String actor = alternateActors[i].trim();
            actor = actor.replaceAll("^'|'$", "");
            if (!actor.isEmpty()) {
                alternativeActors.adicionar(actor);
            }
        }

        if (!data[12].isEmpty()) {
            var formatter = DateTimeFormatter.ofPattern("dd-M-yyyy");
            LocalDate date = LocalDate.parse(data[12].trim(), formatter);
            setDateOfBirth(date);
        }

        setYearOfBirth(data[13]);
        setEyeColour(data[14]);
        setGender(data[15]);
        setHairColour(data[16]);
    }


    public void imprimir() {
        System.out.print("[" + getId() + " ## " + getName() + " ## " + "{");
        if (getAlternativeNames().tamanho() > 0) {
            for (int i = 0; i < getAlternativeNames().tamanho() - 1; i++) {
                System.out.print(getAlternativeNames().obter(i) + ", ");
            }
            System.out.print(getAlternativeNames().obter(getAlternativeNames().tamanho() - 1));
        }
        System.out.print("}");

        System.out.print(" ## " + getHouse() + " ## " + getAncestry() + " ## " + getSpecies() + " ## "
                + getPatronus() + " ## " + isHogwartsStaff() + " ## " + isHogwartsStudent() + " ## "
                + getActorName());

        System.out.print(" ## " + isAlive() + " ## "
                + (getDateOfBirth() != null
                        ? getDateOfBirth().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                        : "N/A")
                + " ## " + getYearOfBirth() + " ## " + getEyeColour() + " ## " + getGender() + " ## "
                + getHairColour() + " ## " + isWizard());
        System.out.println("]");
    }
}

public class Merge_Sort {
    private static List<Personagem> personagens = new ArrayList<>();
    private static List<Personagem> sortByID = new ArrayList<>();

    private static void readFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("/tmp/characters.csv", Charset.forName("UTF-8")))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                Personagem p = new Personagem();
                p.ler(line);
                personagens.add(p);
            }
        } catch (FileNotFoundException e) {
            System.out.println("NAO TEM ARQUIVO");
        } catch (IOException e) {
            System.out.println("LINHA NAO EXISTE");
        }
    }

   
    private static void addByID(String id) {
        for (Personagem p : personagens) {
            if (p.getId().equals(id)) {
                sortByID.add(p);
                break;
            }
        }
    }

    public static void insertionSort() {
        for (int i = 1; i < sortByID.size(); i++) {
            Personagem tmpPerson = sortByID.get(i);
            LocalDate tmpDate = tmpPerson.getDateOfBirth();

            int j = i - 1;
            while (j >= 0) {
                if (sortByID.get(j).getDateOfBirth().isEqual(tmpDate)) {
                    if (sortByID.get(j).getName().compareTo(tmpPerson.getName()) > 0) {
                        sortByID.set(j + 1, sortByID.get(j));
                        j--;
                    } else {
                        break;
                    }
                } else if (sortByID.get(j).getDateOfBirth().compareTo(tmpDate) > 0) {
                    sortByID.set(j + 1, sortByID.get(j));
                    j--;
                } else {
                    break;
                }
            }
            sortByID.set(j + 1, tmpPerson);
        }
    }

    private static void mergeSort(int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergeSort(esq, meio);
            mergeSort(meio + 1, dir);
            intercalar(esq, meio, dir);
        }
    }
    
    private static void intercalar(int esq, int meio, int dir) {
        int nEsq = (meio - esq + 1);
        int nDir = (dir - meio);
        List<Personagem> esqArray = new ArrayList<>(nEsq);
        List<Personagem> dirArray = new ArrayList<>(nDir);
    
        for (int i = 0; i < nEsq; i++) {
            esqArray.add(sortByID.get(esq + i));
        }
    
        for (int j = 0; j < nDir; j++) {
            dirArray.add(sortByID.get(meio + 1 + j));
        }
    
        int i = 0, j = 0;

        int k = esq;

        while (i < nEsq && j < nDir) {
            String actorNameEsq = esqArray.get(i).getActorName();
            String actorNameDir = dirArray.get(j).getActorName();

            if (actorNameEsq == null || actorNameEsq.isEmpty()) {
                sortByID.set(k, esqArray.get(i));
                i++;
            } else if (actorNameDir == null || actorNameDir.isEmpty()) {
                sortByID.set(k, dirArray.get(j));
                j++;
            } else if (actorNameEsq.compareTo(actorNameDir) <= 0) {
                sortByID.set(k, esqArray.get(i));
                i++;
            } else {
                sortByID.set(k, dirArray.get(j));
                j++;
            }
            k++;
        }

        while (i < nEsq) {
            sortByID.set(k, esqArray.get(i));
            i++;
            k++;
        }

        while (j < nDir) {
            sortByID.set(k, dirArray.get(j));
            j++;
            k++;
        }
    }
    
    public static void main(String[] args) {
        readFromFile();
        Scanner scanner = new Scanner(System.in);
        String line = "";

        while (!line.equalsIgnoreCase("FIM")) {
            line = scanner.nextLine().trim();
            addByID(line);
        }

        mergeSort(0, sortByID.size()-1);

        for (Personagem p : sortByID) {
            p.imprimir(); 
        }

        scanner.close();
    }
}
