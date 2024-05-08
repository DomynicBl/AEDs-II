#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <wchar.h>   
#include <locale.h>  

typedef struct s_Personagem
{
char*   id              ; 
char*   name            ; 
char*   alternateNames  ;
char*   house           ; 
char*   ancestry        ; 
char*   species         ; 
char*   patronus        ; 
bool    hogwartsStaff   ;  
bool    hogwartsStudent ;  
char*   actorName       ; 
bool    alive           ;
char*   alternateActors ; 
char*    dateOfBirth    ; 
int     yearOfBirth     ; 
char*   eyeColour       ; 
char*   gender          ; 
char*   hairColour      ; 
bool    wizard          ; 
} Personagem;

void newPersonagem ( Personagem* personagem, char* id , char*  name, char* alternateNames, char* house,char* ancestry, char*  species, char* patronus,bool  hogwartsStaff,bool  hogwartsStudent, char* actorName, bool alive, char* alternateActors, char*  dateOfBirth, int yearOfBirth, char*eyeColour, char* gender , char* hairColour, bool wizard ){
    if(personagem != NULL){
    personagem->id = id;                             
    personagem->name = name;                         
    personagem->alternateNames = alternateNames;                 
    personagem->house = house;                           
    personagem->ancestry = ancestry;                     
    personagem->species = species;      
    if(strcmp(patronus,"null") == 0){
        personagem -> patronus = "";
    }else{
        personagem->patronus= patronus;     
    }                           
    personagem->hogwartsStaff= hogwartsStaff;                   
    personagem->hogwartsStudent= hogwartsStudent ;     
    if(strcmp(actorName,"null") == 0){ 
        personagem -> actorName = "";
    }else{
        personagem->actorName= actorName;  
    }                         
    personagem->alive= alive  ;                           
    personagem->alternateActors= alternateActors;               
    personagem->dateOfBirth  = dateOfBirth  ;                   
    personagem->yearOfBirth = yearOfBirth ;                   
    personagem->eyeColour = eyeColour;                       
    personagem->gender = gender ;                         
    personagem->hairColour  = hairColour ;                     
    personagem->wizard  = wizard ;                         
    }
}

void setPersonagemId(Personagem *p,  char *id) {
    if (p->id != NULL) free(p->id);
    p->id = strdup(id);
}

void setPersonagemName(Personagem *p,  char *name) {
    if (p->name != NULL) free(p->name);
    p->name = strdup(name);
}

void setPersonagemAlternateNames(Personagem *p,  char *alternateNames) {
    if (p->alternateNames != NULL) free(p->alternateNames);
    p->alternateNames = strdup(alternateNames);
}

void setPersonagemHouse(Personagem *p,  char *house) {
    if (p->house != NULL) free(p->house);
    p->house = strdup(house);
}

void setPersonagemAncestry(Personagem *p,  char *ancestry) {
    if (p->ancestry != NULL) free(p->ancestry);
    p->ancestry = strdup(ancestry);
}

void setPersonagemSpecies(Personagem *p,  char *species) {
    if (p->species != NULL) free(p->species);
    p->species = strdup(species);
}

void setPersonagemPatronus(Personagem *p,  char *patronus) {
    if (p->patronus != NULL) free(p->patronus);
    p->patronus = strdup(patronus);
}

void setPersonagemHogwartsStaff(Personagem *p, bool hogwartsStaff) {
    p->hogwartsStaff = hogwartsStaff;
}

void setPersonagemHogwartsStudent(Personagem *p, bool hogwartsStudent) {
    p->hogwartsStudent = hogwartsStudent;
}

void setPersonagemActorName(Personagem *p,  char *actorName) {
    if (p->actorName != NULL) free(p->actorName);
    p->actorName = strdup(actorName);
}

void setPersonagemAlive(Personagem *p, bool alive) {
    p->alive = alive;
}

void setPersonagemAlternateActors(Personagem *p,  char *alternateActors) {
    if (p->alternateActors != NULL) free(p->alternateActors);
    p->alternateActors = strdup(alternateActors);
}

void setPersonagemDateOfBirth(Personagem *p,  char *dateOfBirth) {
    if (p->dateOfBirth != NULL) free(p->dateOfBirth);
    p->dateOfBirth = strdup(dateOfBirth);
}

void setPersonagemYearOfBirth(Personagem *p, int yearOfBirth) {
    p->yearOfBirth = yearOfBirth;
}

void setPersonagemEyeColour(Personagem *p,  char *eyeColour) {
    if (p->eyeColour != NULL) free(p->eyeColour);
    p->eyeColour = strdup(eyeColour);
}

void setPersonagemGender(Personagem *p,  char *gender) {
    if (p->gender != NULL) free(p->gender);
    p->gender = strdup(gender);
}

void setPersonagemHairColour(Personagem *p,  char *hairColour) {
    if (p->hairColour != NULL) free(p->hairColour);
    p->hairColour = strdup(hairColour);
}

void setPersonagemWizard(Personagem *p, bool wizard) {
    p->wizard = wizard;
}

// getters
 char* getPersonagemId( Personagem *p) {
    return p->id;
}

 char* getPersonagemName( Personagem *p) {
    return p->name;
}

 char* getPersonagemAlternateNames( Personagem *p) {
    return p->alternateNames;
}

 char* getPersonagemHouse( Personagem *p) {
    return p->house;
}

 char* getPersonagemAncestry( Personagem *p) {
    return p->ancestry;
}

 char* getPersonagemSpecies( Personagem *p) {
    return p->species;
}

 char* getPersonagemPatronus( Personagem *p) {
    return p->patronus;
}

bool getPersonagemHogwartsStaff( Personagem *p) {
    return p->hogwartsStaff;
}

bool getPersonagemHogwartsStudent( Personagem *p) {
    return p->hogwartsStudent;
}

 char* getPersonagemActorName( Personagem *p) {
    return p->actorName;
}

bool getPersonagemAlive( Personagem *p) {
    return p->alive;
}

 char* getPersonagemAlternateActors( Personagem *p) {
    return p->alternateActors;
}

 char* getPersonagemDateOfBirth( Personagem *p) {
    return p->dateOfBirth;
}

int getPersonagemYearOfBirth( Personagem *p) {
    return p->yearOfBirth;
}
 char* getPersonagemEyeColour( Personagem *p) {
    return p->eyeColour;
}

 char* getPersonagemGender( Personagem *p) {
    return p->gender;
}

 char* getPersonagemHairColour( Personagem *p) {
    return p->hairColour;
}

bool getPersonagemWizard( Personagem *p) {
    return p->wizard;
}

void imprimir ( Personagem* personagem ){
    if (personagem->dateOfBirth && strlen(personagem->dateOfBirth) < 10) {
        char *aux = calloc(11, sizeof(char));
        int dia, mes, ano;

        if (sscanf(personagem->dateOfBirth, "%d-%d-%d", &dia, &mes, &ano) == 3) {
            sprintf(aux, "%02d-%02d-%d", dia, mes, ano);
            free(personagem->dateOfBirth);
            personagem->dateOfBirth = aux;
        } else {
            free(aux);
        }
    }
    printf( "[%s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %d ## %s ## %s ## %s ## %s]\n",personagem->id,personagem->name,personagem->alternateNames,personagem->house,personagem->ancestry,personagem->species,personagem->patronus,personagem->hogwartsStaff ? "true" : "false",personagem->hogwartsStudent ? "true" : "false",personagem->actorName,personagem->alive ? "true" : "false",personagem->dateOfBirth,personagem->yearOfBirth,personagem->eyeColour,personagem->gender,personagem->hairColour,personagem->wizard ? "true" : "false" ); 
}
bool checardata(char data[]){
    bool edata = "false";
    char molde[] = {"00-0-0000"};
    int count = 0;
    for(int i = 0; i<strlen(molde);i++){
        if(data[i] == molde[i]){
            count++;
        }
    }
    if(count == 2){
        edata = "true";
    }
    return edata;
}

void padronizarData(char data[]) {
    int dia, mes, ano;
    for(int i = 0; i<strlen(data); i++){
        printf("%c", data[i]);
    }
    printf("\n");
}

Personagem clone(Personagem* p1){

    Personagem* aux = (Personagem*)malloc(1*sizeof(Personagem));
    newPersonagem ( aux,getPersonagemId(p1),getPersonagemName(p1),getPersonagemAlternateNames(p1),getPersonagemHouse(p1),getPersonagemAncestry(p1),getPersonagemSpecies(p1),getPersonagemPatronus(p1),getPersonagemHogwartsStaff(p1),getPersonagemHogwartsStudent(p1),getPersonagemActorName(p1),getPersonagemAlive(p1),getPersonagemAlternateActors(p1),getPersonagemDateOfBirth(p1),getPersonagemYearOfBirth(p1),getPersonagemEyeColour(p1),getPersonagemGender(p1),getPersonagemHairColour(p1),getPersonagemWizard(p1) );
}

Personagem* ler(Personagem* personagem, char* filename, char* id_procurado) {
    FILE* file = fopen(filename, "r");
    if (file == NULL) {
        printf("\nERROR: File Not Found.\n");
    } else {
        char line[500];
        bool idFound = false;

        fgets(line, sizeof(line), file);
        int na = 0;
        while (fgets(line, sizeof(line), file) != NULL && !idFound) {
            char *atributos[18];
            char auxline[500] = {0};
            int col = 0;
            int t = 0;
            int k = 0;
            int index = 0;
            char *token = strtok(line, ";");
            int execvar = 0;
            na++;
            int debug= 0;
            while (token != NULL) {
                debug++;
                execvar++;
                atributos[index++] = strdup(token);
                token = strtok(NULL, ";");
            }
        char aux[500] = {0};
        int j = 0;
        for (int i = 0; i < strlen(atributos[2]); i++) {
            if (atributos[2][i] == '[') {
                aux[j] = '{';
            } else if (atributos[2][i] == ']') {
                aux[j] = '}';
            } else if (atributos[2][i] == '\'') {
                continue; 
            } else {
                aux[j] = atributos[2][i];
            }
            j++; 
        }
        aux[j] = '\0'; 

        free(atributos[2]); 
        atributos[2] = strdup(aux);
            if (strcmp(atributos[0], id_procurado) == 0) {
                bool aux = "false";

                if(execvar < 18){
                    if(execvar>16){
                    if(strlen(atributos[16]) >= 11){
                        aux = "true";
                    }else{
                        aux = 0;
                    }
                    newPersonagem(personagem,atributos[0], atributos[1], atributos[2], atributos[3],atributos[4], atributos[5], "null" ,strcmp(atributos[6], "VERDADEIRO") == 0,strcmp(atributos[7], "VERDADEIRO") == 0,atributos[8], strcmp(atributos[9], "VERDADEIRO") == 0,atributos[10], atributos[11],atoi(atributos[12]), atributos[13], atributos[14],atributos[15], aux);
                    } else{
                    if(strlen(atributos[15]) >= 11){
                        aux = "true";
                    } else{
                        aux = 0;
                    }
                    newPersonagem(personagem,atributos[0], atributos[1], atributos[2], atributos[3],atributos[4], atributos[5], "null" ,strcmp(atributos[6], "VERDADEIRO") == 0,strcmp(atributos[7], "VERDADEIRO") == 0,"null", strcmp(atributos[8], "VERDADEIRO") == 0,atributos[9], atributos[10],atoi(atributos[11]), atributos[12], atributos[13],atributos[14], aux);
                    }
                }else{
                    if(strlen(atributos[17]) >= 11){
                        aux = "true";
                    }else{
                        aux = 0;
                    }
                    newPersonagem(personagem,atributos[0], atributos[1], atributos[2], atributos[3],atributos[4], atributos[5], atributos[6],strcmp(atributos[7], "VERDADEIRO") == 0,strcmp(atributos[8], "VERDADEIRO") == 0,atributos[9], strcmp(atributos[10], "VERDADEIRO") == 0,atributos[11], atributos[12],atoi(atributos[13]), atributos[14], atributos[15],atributos[16], aux);
                }
                idFound = true;
                return personagem;
            }

            for (int x = 0; x < index; x++) {
                free(atributos[x]);
            }
        }
        fclose(file);
    }
}

bool pesquisaBinaria(Personagem* personagens, int n, char* nomeprocurado) {
    bool encontrou = false;
    int esq = 0, dir = n - 1, meio;
    while (esq <= dir && !encontrou) {
        meio = (esq + dir) / 2;
        int res = strcmp(personagens[meio].name, nomeprocurado);
        if (strcmp(personagens[meio].name, nomeprocurado) == 0) {
            encontrou=true;
        }
        if (strcmp(personagens[meio].name, nomeprocurado) < 0)  {
            esq = meio + 1;
        }
        if (strcmp(personagens[meio].name, nomeprocurado) > 0)  {
            dir = meio - 1;
        }
    }
    return encontrou;
}

void shellSort(Personagem personagens[], int n) {
    int intervalo = 1;
    while (intervalo < n / 3) {
        intervalo = intervalo * 3 + 1;
    }

    while (intervalo >= 1) {
        for (int i = intervalo; i < n; i++) {
            Personagem chave = personagens[i];
            int j = i - intervalo;
            while (j >= 0 && strcmp(personagens[j].eyeColour,chave.eyeColour) > 0) {
                personagens[j + intervalo] = personagens[j];
                j -= intervalo;
            }
            personagens[j + intervalo] = chave;
        }
        intervalo /= 3;
    }
}


void swap(int i, int j, Personagem *personagens) {
   Personagem temp = personagens[i];
   personagens[i] = personagens[j];
   personagens[j] = temp;
}

void quicksortRec(Personagem* perso, int esq, int dir) {
    int i = esq, j = dir;
    char* pivo = perso[(dir+esq)/2].house;
    while (i <= j) {
        while (strcmp(perso[i].house, pivo) < 0) i++;
        while (strcmp(perso[j].house, pivo) > 0) j--;
        if (i <= j) {
            swap(i, j, perso);
            i++;
            j--;
        }
    }
    if (esq < j) quicksortRec(perso, esq, j);
    if (i < dir) quicksortRec(perso, i, dir);
}

void sortByName(Personagem* perso, int length) {
    for (int i = 0; i < length; i++) {
        int menor = i;
        for (int j = i + 1; j < length; j++) {
            if (strcmp(perso[menor].house, perso[j].house) == 0 &&
                strcmp(perso[menor].name, perso[j].name) > 0) {
                menor = j;
            }
        }
        swap(menor, i, perso);
    }
}

void quicksort(Personagem* perso, int tam) {
    quicksortRec(perso, 0, tam-1);  
    sortByName(perso, tam);
}

int main() {
    Personagem* personagens = NULL;
    int numPersonagens = 0;
    char id[81];

    fgets(id, 81, stdin);
    id[strcspn(id, "\n")] = '\0';
    id[strcspn(id, "\r")] = '\0';

    while (strcmp(id, "FIM") != 0) {
        Personagem* temp = realloc(personagens, (numPersonagens + 1) * sizeof(Personagem));
        if (temp == NULL) {
            fprintf(stderr, "Erro ao alocar memória\n");
            free(personagens);
            return 1;
        }
        personagens = temp;
        ler(&personagens[numPersonagens], "/tmp/characters.csv", id);
        numPersonagens++;
        fgets(id, 81, stdin);
        id[strcspn(id, "\n")] = '\0';
        id[strcspn(id, "\r")] = '\0';
    }

    quicksort(personagens, numPersonagens);

    for (int i = 0; i < numPersonagens; i++) {
        imprimir(&personagens[i]);
    }

    free(personagens);
    return 0;
}