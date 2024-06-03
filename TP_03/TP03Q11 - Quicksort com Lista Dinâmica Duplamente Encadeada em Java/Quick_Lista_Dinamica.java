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

class CelulaDupla {

	public Personagem personagem;
	public CelulaDupla ant;
	public CelulaDupla prox;

	public CelulaDupla() {
		this(null);
	}

	public CelulaDupla(Personagem personagem) {
		this.personagem = personagem;
		this.ant = this.prox = null;
	}
}

class ListaDupla {
	private CelulaDupla primeiro;
	private CelulaDupla ultimo;

	public ListaDupla() {
		primeiro = new CelulaDupla();
		ultimo = primeiro;
	}
    
    public void inserirFim(Personagem personagem) {
        ultimo.prox = new CelulaDupla(personagem);
      ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
    }
	
    public void inserirInicio(Personagem personagem) {
		CelulaDupla tmp = new CelulaDupla(personagem);

      tmp.ant = primeiro;
      tmp.prox = primeiro.prox;
      primeiro.prox = tmp;
      if(primeiro == ultimo){
         ultimo = tmp;
      }else{
         tmp.prox.ant = tmp;
      }
      tmp = null;
	}
	
	public void mostrar() {
		for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
			i.personagem.imprime();
		}
	}

    public CelulaDupla getPrimeiro() {
        return primeiro;
    }

    public CelulaDupla getUltimo() {
        return ultimo;
    }
}
    
class QuickSort {
    public static void quickSort(ListaDupla lista) {
        CelulaDupla primeiro = lista.getPrimeiro().prox;
        CelulaDupla ultimo = lista.getUltimo();
        quickSortRecursivo(primeiro, ultimo);
    }

    private static void quickSortRecursivo(CelulaDupla inicio, CelulaDupla fim) {
        if (inicio != null && fim != null && inicio != fim && inicio != fim.prox) {
            CelulaDupla pivot = partition(inicio, fim);
            quickSortRecursivo(inicio, pivot.ant);
            quickSortRecursivo(pivot.prox, fim);
        }
    }

    private static CelulaDupla partition(CelulaDupla inicio, CelulaDupla fim) {
        Personagem pivot = fim.personagem;
        CelulaDupla i = inicio.ant;

        for (CelulaDupla j = inicio; j != fim; j = j.prox) {
            if (compare(j.personagem, pivot) < 0) {
                i = (i == null) ? inicio : i.prox;
                swap(i, j);
            }
        }
        i = (i == null) ? inicio : i.prox;
        swap(i, fim);
        return i;
    }

    private static int compare(Personagem p1, Personagem p2) {
        int houseComparison = p1.getHouse().compareTo(p2.getHouse());
        if (houseComparison != 0) {
            return houseComparison;
        } else {
            return p1.getName().compareTo(p2.getName());
        }
    }

    private static void swap(CelulaDupla a, CelulaDupla b) {
        Personagem temp = a.personagem;
        a.personagem = b.personagem;
        b.personagem = temp;
    }
}


public class Quick_Lista_Dinamica {
    

    public static void main(String[] args) {
        
        ListaDupla listapersonagens = new ListaDupla();
        int j = 0;
        FileReader filereader;
        BufferedReader reader;
        Scanner entrada = new Scanner(System.in);
        String codigo;
        String nomeArquivo = "/tmp/characters.csv";
        
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
                            listapersonagens.inserirFim(personagem);
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
            QuickSort.quickSort(listapersonagens);
            listapersonagens.mostrar();

        } catch (IOException e) {
            e.printStackTrace();
        }
        entrada.close();
    }

}