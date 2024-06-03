#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>

#define FILE_PATH "/tmp/characters.csv"
#define INITIAL_STRING_CAPACITY 32
#define INITIAL_ARRAY_CAPACITY 5

typedef struct {
    char *id;
    char *name;
    char **alternateNames;
    int alternateNamesCount;
    char *house;
    char *ancestry;
    char *species;
    char *patronus;
    bool isHogwartsStaff;
    bool isHogwartsStudent;
    char *actorName;
    bool isAlive;
    char **alternateActors;
    int alternateActorsCount;
    char *dateOfBirth;
    int yearOfBirth;
    char *eyeColour;
    char *gender;
    char *hairColour;
    bool isWizard;
} Character;

void free_string_array(char **array, int length);
void free_character(Character *character);

char *read_string(int *i, const char *csvLine) {
    int maxSize = strlen(csvLine);
    if (*i >= maxSize) 
        return NULL; 

    int capacity = INITIAL_STRING_CAPACITY;
    char *str = (char *)malloc(capacity * sizeof(char));
    if (str == NULL) {
        perror("Erro de alocação de memória na leitura de string");
        return NULL;
    }

    int pos = 0; 
    while (*i < maxSize && csvLine[*i] != ';') 
    {
        if (pos >= capacity-1) {
            capacity *= 2;
            char *temp = (char *)realloc(str, capacity * sizeof(char));
            if (temp == NULL) {
                perror("Erro ao redimensionar cadeia de caracteres");
                free(str); 
                return NULL;
            }
            str = temp;
        }
        str[pos++] = csvLine[(*i)++];
    }

    str[pos] = '\0'; 
    (*i)++;
    return str;
}

char **read_multivalued(int *i, const char *csvLine, int *arrayCount) {
    int maxSize = strlen(csvLine);
    if (*i >= maxSize-1 || csvLine[*i] != '[') 
        return NULL;

    (*i)++;

    int arrayCapacity = INITIAL_ARRAY_CAPACITY;
    char **array = (char **)malloc(arrayCapacity * sizeof(char *)); 
    if (array == NULL) {
        perror("Erro de alocação de memória para array de strings");
        return NULL;
    }

    int pos = 0;
    while (*i < maxSize && csvLine[*i] != ']')
    {
        if (pos >= arrayCapacity) {
            arrayCapacity *= 2; 
            char **temp = (char **)realloc(array, arrayCapacity * sizeof(char *)); 
            if (temp == NULL) {
                perror("Erro de alocação de memória para array de strings");
                free_string_array(array, pos);
                return NULL;
            }
            array = temp; 
        }

        int strCapacity = INITIAL_STRING_CAPACITY;
        char *tempStr = (char *)malloc(strCapacity * sizeof(char));
        if (tempStr == NULL) {
            perror("Erro de alocação de memória para string");
            free_string_array(array, pos);
            return NULL;
        }

        int j = 0;
        while (*i < maxSize && csvLine[*i] != ',' && csvLine[*i] != ']')
        {
            if (j >= strCapacity - 1) { 
                strCapacity *= 2;
                char *tempStr2 = (char *)realloc(tempStr, strCapacity * sizeof(char));
                if (tempStr2 == NULL) {
                    perror("Erro de alocação de memória para string");
                    free_string_array(array, pos);
                    free(tempStr);
                    return NULL;
                }
                tempStr = tempStr2; 
            }
            if (csvLine[*i] != '\'') 
                tempStr[j++] = csvLine[*i];

            (*i)++;
        }

        tempStr[j] = '\0'; 
        array[pos++] = tempStr;

        if (csvLine[*i] == ',') 
            (*i)++; 
    }
    (*i) += 2;
    *arrayCount = pos; 
    return array;
}

bool read_boolean(int *i, const char *csvLine) {
    bool predicate = false;
    char* wordRead = read_string(i, csvLine);

    if (wordRead != NULL) {
        if (wordRead[0] == 'V')
            predicate = true;
        free(wordRead);
    }

    return predicate; 
}

int read_integer(int *i, const char *csvLine){
    char* wordRead = read_string(i, csvLine);
    if(wordRead == NULL)
        return -1;
    return atoi(wordRead);
}

Character* csvLine_mapper(char *csvLine) {
    Character* character = (Character*)malloc(sizeof(Character));
    if (character == NULL) {
        perror("Erro de alocação de memória para struct Character");
        return NULL;
    }

    int i = 0;

    character->id = read_string(&i, csvLine);
    character->name = read_string(&i, csvLine);

    int alternateNamesCount = 0;
    character->alternateNames = read_multivalued(&i, csvLine, &alternateNamesCount);
    character->alternateNamesCount = alternateNamesCount;

    character->house = read_string(&i, csvLine);
    character->ancestry = read_string(&i, csvLine);
    character->species = read_string(&i, csvLine);
    character->patronus = read_string(&i, csvLine);
    character->isHogwartsStaff = read_boolean(&i, csvLine);
    character->isHogwartsStudent = read_boolean(&i, csvLine);
    character->actorName = read_string(&i, csvLine);
    character->isAlive = read_boolean(&i, csvLine);

    int alternateActorsCount = 0;
    character->alternateActors = read_multivalued(&i, csvLine, &alternateActorsCount);
    character->alternateActorsCount = alternateActorsCount;

    character->dateOfBirth = read_string(&i, csvLine);
    character->yearOfBirth = read_integer(&i, csvLine);
    character->eyeColour = read_string(&i, csvLine);
    character->gender = read_string(&i, csvLine);
    character->hairColour = read_string(&i, csvLine);
    character->isWizard = read_boolean(&i, csvLine);

    return character; 
}

void print_string_array(char **array, int count) {
    printf("{");
    for (int i = 0; i < count; i++) {
        printf("%s", array[i]);
        if (i < count - 1) {
            printf(",");
        }
    }
    printf("}");
}

void print_character(Character *character) {
    if (character == NULL) {
        printf("Character struct is NULL.\n");
        return;
    }

    printf("[");
    printf("%s", character->id ? character->id : " "); 
    printf(" ## %s", character->name ? character->name : " ");

    printf(" ## ");
    if (character->alternateNames) 
        print_string_array(character->alternateNames, character->alternateNamesCount);
    else 
        printf(" {}");

    printf(" ## %s", character->house ? character->house : " "); 
    printf(" ## %s", character->ancestry ? character->ancestry : " "); 
    printf(" ## %s", character->species ? character->species : " "); 
    printf(" ## %s", character->patronus ? character->patronus : " "); 
    printf(" ## %s", character->isHogwartsStaff ? "true" : "false"); 
    printf(" ## %s", character->isHogwartsStudent ? "true" : "false"); 
    printf(" ## %s", character->actorName ? character->actorName : " ");
    printf(" ## %s", character->isAlive ? "true" : "false"); 
    printf(" ## %s", character->dateOfBirth ? character->dateOfBirth : " "); 
    printf(" ## %d", character->yearOfBirth); 
    printf(" ## %s", character->eyeColour ? character->eyeColour : " ");
    printf(" ## %s", character->gender ? character->gender : " ");
    printf(" ## %s", character->hairColour ? character->hairColour : " "); 
    printf(" ## %s", character->isWizard ? "true" : "false");

    printf("]\n");
}

typedef struct CelulaDupla {
    Character *personagem;              
    struct CelulaDupla* prox;  
    struct CelulaDupla* ant;   
} CelulaDupla;

CelulaDupla* novaCelulaDupla(Character *personagem) {
    CelulaDupla* nova = (CelulaDupla*) malloc(sizeof(CelulaDupla));
    nova->personagem = personagem;
    nova->ant = nova->prox = NULL;
    return nova;
}

CelulaDupla* primeiro;
CelulaDupla* ultimo;

void start() {
    Character* p = (Character*) malloc(sizeof(Character));
    primeiro = novaCelulaDupla(p);
    ultimo = primeiro;
}

void inserirFim(Character *personagem) {
    ultimo->prox = novaCelulaDupla(personagem);
    ultimo->prox->ant = ultimo;
    ultimo = ultimo->prox;
}

void mostrar() {
    for (CelulaDupla* i = primeiro->prox; i != NULL; i = i->prox) {
        print_character(i->personagem);
    }
}

CelulaDupla* getCelulaByIndex(int index) {
    CelulaDupla* i = primeiro->prox;
    for (int j = 0; j < index && i != NULL; j++, i = i->prox);
    return i;
}

int numeroComparacoes = 0;

void swap(CelulaDupla* a, CelulaDupla* b) {
    Character* tmp = a->personagem;
    a->personagem = b->personagem;
    b->personagem = tmp;
}

CelulaDupla* partition(CelulaDupla* low, CelulaDupla* high) {
    char* pivot = high->personagem->name;
    CelulaDupla* i = low->ant;

    for (CelulaDupla* j = low; j != high; j = j->prox) {
        numeroComparacoes++;
        if (strcmp(j->personagem->name, pivot) <= 0) {
            i = (i == NULL) ? low : i->prox;
            swap(i, j);
        }
    }
    i = (i == NULL) ? low : i->prox;
    swap(i, high);
    return i;
}

void _quickSort(CelulaDupla* low, CelulaDupla* high) {
    if (high != NULL && low != high && low != high->prox) {
        CelulaDupla* p = partition(low, high);
        _quickSort(low, p->ant);
        _quickSort(p->prox, high);
    }
}

void quickSort() {
    CelulaDupla* high = ultimo;
    CelulaDupla* low = primeiro->prox;
    _quickSort(low, high);
}

void lerArquivo() {
    FILE *file = fopen(FILE_PATH, "r");
    if (file == NULL) {
        perror("Erro ao abrir arquivo");
        return;
    }

    char line[1024];
    while (fgets(line, sizeof(line), file)) {
        Character* character = csvLine_mapper(line);
        if (character != NULL) {
            inserirFim(character);
        }
    }

    fclose(file);
}

void free_string_array(char **array, int length) {
    for (int i = 0; i < length; i++) {
        free(array[i]);
    }
    free(array);
}

void free_character(Character *character) {
    if (character != NULL) {
        free(character->id);
        free(character->name);
        free_string_array(character->alternateNames, character->alternateNamesCount);
        free(character->house);
        free(character->ancestry);
        free(character->species);
        free(character->patronus);
        free(character->actorName);
        free_string_array(character->alternateActors, character->alternateActorsCount);
        free(character->dateOfBirth);
        free(character->eyeColour);
        free(character->gender);
        free(character->hairColour);
        free(character);
    }
}

void free_lista() {
    CelulaDupla* current = primeiro;
    while (current != NULL) {
        CelulaDupla* next = current->prox;
        free_character(current->personagem);
        free(current);
        current = next;
    }
}

int main() {
    start();
    lerArquivo();

    clock_t start_time = clock();
    quickSort();
    clock_t end_time = clock();

    double time_spent = (double)(end_time - start_time) / CLOCKS_PER_SEC;

    mostrar();

    FILE *log_file = fopen("822331_quicksort2.txt", "w");
    if (log_file != NULL) {
        fprintf(log_file, "Matricula: 822331, Comparacoes: %d, Tempo: %.6f segundos\n", numeroComparacoes, time_spent);
        fclose(log_file);
    } else {
        perror("Error opening log file");
    }

    free_lista();

    return 0;
}