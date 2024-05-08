#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <stdbool.h>
#include <string.h>
#include <ctype.h>
#include <time.h>

#define true 1
#define false 0
typedef char *String;
typedef bool Boolean;

int count = 0;


typedef struct Lista
{
    String *elemento;
    int tamanho;
} Lista;

void iniciar(Lista *lista, size_t tamanho)
{
    if (tamanho > 0)
    {
        lista->elemento = (String *)malloc(tamanho * sizeof(String));
        for (size_t i = 0; i < tamanho; i++)
        {
            lista->elemento[i] = (String)malloc(500 * sizeof(char));
        }
    }

    lista->tamanho = 0;
}

void inserirFim(String elemento, Lista *lista, int tamanho)
{
    if (lista->tamanho >= tamanho)
    {
        printf("Erro ao inserir!");
        exit(1);
    }

    strcpy(lista->elemento[lista->tamanho], elemento);
    lista->tamanho++;
}

void mostrar(Lista *lista)
{
    int i;

    for (i = 0; i < lista->tamanho; i++)
    {
        printf("%s ", lista->elemento[i]);
    }
}

void freeList(Lista *lista)
{
    free(lista->elemento);
    lista->tamanho = 0;
}


typedef struct Personagem
{
    char id[40];             
    char name[80];           
    Lista *alternate_names;  
    char house[50];          
    char ancestry[50];       
    char species[50];        
    char patronus[50];       
    Boolean hogwartsStaff;   
    char hogwartsStudent[50];
    char actorName[80];      
    Boolean alive;           
    Lista *alternate_actors; 
    char dateOfBirth[15];    
    int yearOfBirth;         
    char eyeColour[15];      
    char gender[15];         
    char hairColour[15];     
    Boolean wizard;          
} Personagem;

void reset(Personagem *personagem)
{
    personagem->hogwartsStaff = false;
    personagem->alive = false;
    personagem->yearOfBirth = 0;
    personagem->wizard = false;
    personagem->alternate_names = NULL;
    personagem->alternate_actors = NULL;

    personagem->alternate_names = (Lista *)malloc(sizeof(Lista));
    iniciar(personagem->alternate_names, 50);
    personagem->alternate_actors = (Lista *)malloc(sizeof(Lista));
    iniciar(personagem->alternate_actors, 50);
}

typedef struct Cell
{
    Personagem personagem;
    struct Cell *prox;
} Cell;

Cell *newCell(Personagem personagem)
{
    Cell *new = (Cell *)malloc(sizeof(Cell));
    new->personagem = personagem;
    new->prox = NULL;
    return new;
}

Cell *primeiro;
Cell *ultimo;

void start(Personagem personagem)
{
    primeiro = newCell(personagem);
    ultimo = primeiro;
}

void addEnd(Personagem personagem)
{
    ultimo->prox = newCell(personagem);
    ultimo = ultimo->prox;
}

int length()
{
    int length = 0;
    Cell *i;
    for (i = primeiro; i != ultimo; i = i->prox, length++)
        ;
    return length;
}

void printCell()
{
    printf("[ ");
    Cell *i;
    for (i = primeiro->prox; i != NULL; i = i->prox)
    {
        printf("%s ", i->personagem.id);
    }
    printf("] \n");
}

Personagem getElementByID(String id)
{
    Cell *i;

    for (i = primeiro->prox; i != NULL; i = i->prox)
    {
        if (strcmp(i->personagem.id, id) == 0)
        {
            return i->personagem;
        }
    }
    Personagem personagem;
    return personagem;
}

void freeCell()
{
    Cell *aux = primeiro;
    Cell *prox;

    while (aux != NULL)
    {
        primeiro = aux->prox;
        free(aux);
        aux = prox;
    }
    primeiro = NULL;
    ultimo = NULL;
}



size_t numOfSpaces(char regex, String str)
{
    size_t x = 0, y = 0;
    size_t size = strlen(str);
    for (x = 0; x < size; x++)
    {
        if (str[x] == regex)
        {
            y++;
        }
    }
    return y + 1;
}

String *split(char regex, String str)
{
    size_t size = numOfSpaces(regex, str);
    String *output = (String *)malloc(size * sizeof(String));
    for (size_t i = 0; i < size; i++)
    {
        output[i] = (String)malloc(200 * sizeof(char));
    }

    int x = 0, y = 0, z = 0;

    while (str[x] != '\0')
    {
        if (str[x] == regex)
        {
            output[y][z] = '\0';

            y++;
            z = 0;
            if ((str[x + 1] == regex) || (str[x + 1] == '\0') || (str[x + 1] == '\n'))
            {
                x++;
                y++;
            }
        }
        else
        {
            if (str[x] != '\n')
            {
                output[y][z] = str[x];
                z++;
            }
        }
        x++;
    }

    return output;
} 

char *replaceAll(const char *oldString, const char *newString, const char *str)
{
    size_t oldLen = strlen(oldString);
    size_t newLen = strlen(newString);
    size_t strLen = strlen(str);
    size_t count = 0;

    const char *tmp = str;
    while ((tmp = strstr(tmp, oldString)))
    {
        count++;
        tmp += oldLen;
    }

    size_t resultLen = strLen + count * (newLen - oldLen) + 1;
    char *result = (char *)malloc(resultLen);

    char *out = result;
    const char *in = str;
    while (*in)
    {
        if (strncmp(in, oldString, oldLen) == 0)
        {
            strcpy(out, newString);
            in += oldLen;
            out += newLen;
        }
        else
        {
            *out++ = *in++;
        }
    }
    *out = '\0';

    return result;
}

char *trimWhiteSpace(char *str)
{
    char *end;

    while (isspace((unsigned char)*str))
        str++;

    if (*str == 0)
        return str;

    end = str + strlen(str) - 1;
    while (end > str && isspace((unsigned char)*end))
        end--;

    end[1] = '\0';

    return str;
}

void read(String line, Personagem *personagem)
{

    String *info = split(';', line);

    strcpy(personagem->id, info[0]);
    strcpy(personagem->name, info[1]);
    strcpy(personagem->house, info[3]);
    strcpy(personagem->ancestry, info[4]);
    strcpy(personagem->species, info[5]);
    strcpy(personagem->patronus, info[6]);
    if(strcmp(info[8], "FALSO") == 0){
        strcpy(personagem->hogwartsStudent, "false");
    }else{
        strcpy(personagem->hogwartsStudent, "true");
    }
    strcpy(personagem->actorName, info[9]);
    personagem->yearOfBirth = atoi(info[13]);
    strcpy(personagem->eyeColour, info[14]);
    strcpy(personagem->gender, info[15]);
    strcpy(personagem->hairColour, info[16]);

    strcpy(personagem->dateOfBirth, info[12]);
    int day, mouth, year;
    sscanf(personagem->dateOfBirth, "%d-%d-%d", &day, &mouth, &year);
    sprintf(personagem->dateOfBirth, "%02d-%02d-%d", day, mouth, year);

    personagem->hogwartsStaff = (info[7][0] == 'F') ? false : true;
    personagem->alive = (info[10][0] == 'F') ? false : true;
    personagem->wizard = (info[17][0] == 'F') ? false : true;

    personagem->alternate_names = (Lista *)malloc(sizeof(Lista));
    iniciar(personagem->alternate_names, 15);

    String tmp = replaceAll("[", "", info[2]);
    tmp = replaceAll("]", "", tmp);
    size_t sizeAltNames = numOfSpaces(',', tmp);
    String *alternateNamesArray = split(',', tmp);
    for (size_t i = 0; i < sizeAltNames; i++)
    {
        String tmpAlt = trimWhiteSpace(alternateNamesArray[i]);
        tmpAlt = replaceAll("'", "", tmpAlt);
        if (tmpAlt != NULL)
        {
            inserirFim(tmpAlt, personagem->alternate_names, sizeAltNames);
        }
        free(tmpAlt);
    }

    personagem->alternate_actors = (Lista *)malloc(sizeof(Lista));
    iniciar(personagem->alternate_actors, 15);

    String tmp2 = replaceAll("[", "", info[11]);
    tmp2 = replaceAll("]", "", tmp2);
    size_t sizeAltActors = numOfSpaces(',', tmp2);
    String *alternateActorsArray = split(',', tmp2);

    for (size_t i = 0; i < sizeAltActors; i++)
    {
        String tmp2Alt = (String)malloc(300 * sizeof(char));
        alternateActorsArray[i] = trimWhiteSpace(alternateActorsArray[i]);
        strcpy(tmp2Alt, alternateActorsArray[i]);

        tmp2Alt = replaceAll("'", "", tmp2Alt);
        if (tmp2Alt != NULL)
        {
            inserirFim(tmp2Alt, personagem->alternate_actors, sizeAltActors);
        }
        free(tmp2Alt);
    }

    free(tmp);
    free(tmp2);
}

void print(Personagem personagem)
{
    printf("[%s ## %s ## {", personagem.id, personagem.name);
    for (int i = 0; i < personagem.alternate_names->tamanho; i++)
    {
        if (i == (personagem.alternate_names->tamanho - 1))
        {
            printf("%s", personagem.alternate_names->elemento[i]);
        }
        else
        {
            printf("%s, ", personagem.alternate_names->elemento[i]);
        }
    }

    printf("} ## %s ## %s ## %s ## %s ## ",
           personagem.house, personagem.ancestry, personagem.species, personagem.patronus);
    if (personagem.hogwartsStaff)
    {
        printf("true ## ");
    }
    else
    {
        printf("false ## ");
    }
    printf("%s ## %s ## ", personagem.hogwartsStudent, personagem.actorName);
    if (personagem.alive)
    {
        printf("true ## ");
    }
    else
    {
        printf("false ## ");
    }
    printf("%s ## %d ## %s ## %s ## %s ## ", personagem.dateOfBirth, personagem.yearOfBirth, personagem.eyeColour,
    personagem.gender, personagem.hairColour);

    if (personagem.wizard)
    {
        printf("true]\n");
    }
    else
    {
        printf("false]\n");
    }
}

bool strCompare(String str1, String str2)
{
    for (size_t i = 0; i < strlen(str1) && i < strlen(str2); i++)
    {
        if (str1[i] != str2[i])
        {
            return false;
        }
    }
    return true;
}

bool draw(String name, String name2){
    for (size_t i = 0; i < strlen(name) && i < strlen(name2); i++)
    {
        if (name[i] > name2[i])
        {
            return true;
        }
        else if (name[i] < name2[i])
        {
            return false;
        }
    }
    return false;
}

bool isBigger(String str1, String str2, Personagem pivo, Personagem p)
{
    for (size_t i = 0; i < strlen(str1) && i < strlen(str2); i++)
    {
        if (str1[i] > str2[i])
        {
            return true;
        }
        else if (str1[i] < str2[i])
        {
            return false;
        }
    }
    return draw(pivo.name, p.name);
}

bool isBigger2(String str1, String str2, Personagem pivo, Personagem p)
{
    return strcmp(pivo.name, p.name) > 0;
}

void swap(String *idArray, int i, int j)
{
    char tmp[300];
    strcpy(tmp, idArray[i]);
    strcpy(idArray[i], idArray[j]);
    strcpy(idArray[j], tmp);
}

void quicksort(String *idArray, int esq, int dir)
{
    int i = esq, j = dir;
    Personagem personagemPivo, personagem;
    personagemPivo = getElementByID(idArray[(i+j)/2]);

    while (i <= j)
    {
        personagem = getElementByID(idArray[i]);
        while (isBigger(personagemPivo.house, personagem.house, personagemPivo, personagem))
        {
            i++;
            personagem = getElementByID(idArray[i]);
        }

        personagem = getElementByID(idArray[j]);
        while (isBigger(personagem.house, personagemPivo.house, personagem, personagemPivo))
        {
            j--;
            personagem = getElementByID(idArray[j]);
        }
        if (i <= j)
        {
            swap(idArray, i, j);
            i++;
            j--;
        }
    }
    if (esq < j)
    {
        quicksort(idArray, esq, j);
    }
    if (i < dir)
    {
        quicksort(idArray, i, dir);
    }
}

bool isBigger3(String str1, String str2, Personagem pivo, Personagem p)
{
    int eyeColorComparison = strcmp(pivo.eyeColour, p.eyeColour);
    if (eyeColorComparison != 0) {
        return eyeColorComparison > 0;
    } else {
        return strcmp(pivo.name, p.name) > 0;
    }
}

void shellSort(String *idArray, int n) {
    int gap = n / 2;
    while (gap > 0) {

        for (int i = gap; i < n; i++) {

            String temp = idArray[i];
            int j = i;

            while (j >= gap && isBigger3(getElementByID(idArray[j - gap]).eyeColour, getElementByID(temp).eyeColour, getElementByID(idArray[j - gap]), getElementByID(temp))) {
                idArray[j] = idArray[j - gap];
                j -= gap;
            }
            idArray[j] = temp;
        }

        gap /= 2;
    }
}

int main(int argc, char const *argv[])
{
    FILE *arq = fopen("/tmp/characters.csv", "r");
    if (arq == NULL)
    {
        printf("Erro ao abrir o arquivo.");
    }

    char line[1000];
    fgets(line, sizeof(line), arq);
    Personagem personagem;
    reset(&personagem);

    start(personagem);

    while (fgets(line, sizeof(line), arq) != NULL)
    {
        reset(&personagem);      
        read(line, &personagem);
        addEnd(personagem);      
    }
    fclose(arq);

    char input[300];
    scanf("%99s", input);

    String *idArray = (String *)malloc(28 * sizeof(String));
    for (size_t i = 0; i < 28; i++)
    {
        idArray[i] = (char *)malloc(300 * sizeof(char));
    }

    int x = 0;
    while (strcmp(input, "FIM") != 0 && x < 28)
    {
        if (strcmp(input, "FIM") != 0 && x < 28)
        {
            strcpy(idArray[x], input);
            x++;
        }
        scanf("%s", input);
    }

    clock_t startTime = clock();
        shellSort(idArray, x);

        for(int i = 0; i < x; i++){
            personagem = getElementByID(idArray[i]); 
            print(personagem);
        }


    clock_t endTime = clock();
    double execTime = (double)(endTime - startTime) / CLOCKS_PER_SEC;
    execTime *= 1000;

    arq = fopen("822331.txt", "wt");
    fprintf(arq, "822331\t %d \t%fms", count, execTime);

    for (size_t i = 0; i < 28; i++)
    {
        free(idArray[i]);
    }
    free(idArray);
    return 0;
}