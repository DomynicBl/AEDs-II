#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

#define FILE_PATH "/tmp/characters.csv"
#define MAXTAM    5
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

void free_string_array(char **arrayy, int length);
void free_character(Character *character);

char *read_string(int *i, const char *csvLine) {
    int maxSize = strlen(csvLine);
    if (*i >= maxSize) 
        return NULL; 

    int capacity = INITIAL_STRING_CAPACITY;
    char *str = (char *)malloc(capacity * sizeof(char));
    if (str == NULL) {
        perror("Memory allocation error in string");
        return NULL;
    }

    int pos = 0; 
    while (*i < maxSize && csvLine[*i] != ';') 
    {
        if (pos >= capacity-1) {
            capacity *= 2;
            char *temp = (char *)realloc(str, capacity * sizeof(char));
            if (temp == NULL) {
                perror("Error when resizing string");
                free(str); 
                return NULL;
            }
            str = temp;
        }
        str[pos++] = csvLine[*i];
        (*i)++;
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
    char **arrayy = (char **)malloc(arrayCapacity * sizeof(char *)); 
    if (arrayy == NULL) {
        perror("Memory allocation error in string array");
        return NULL;
    }

    int pos = 0;
    while (*i < maxSize && csvLine[*i] != ']')
    {
        if (pos >= arrayCapacity) {
            arrayCapacity *= 2; 
            char **temp = (char **)realloc(arrayy, arrayCapacity * sizeof(char *)); 
            if (temp == NULL) {
                perror("Error when resizing string array");
                free_string_array(arrayy, pos);
                return NULL;
            }
            arrayy = temp; 
        }

        int strCapacity = INITIAL_STRING_CAPACITY;
        char *tempStr = (char *)malloc(strCapacity * sizeof(char));
        if (tempStr == NULL) {
            perror("Memory allocation error in string");
            free_string_array(arrayy, pos);
            return NULL;
        }

        int j = 0;
        while (*i < maxSize && csvLine[*i] != ',' && csvLine[*i] != ']')
        {
            if (j >= strCapacity - 1) { 
                strCapacity *= 2;
                char *tempStr2 = (char *)realloc(tempStr, strCapacity * sizeof(char));
                if (tempStr2 == NULL) {
                    perror("Memory reallocation error in string");
                    free_string_array(arrayy, pos);
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
        arrayy[pos++] = tempStr;

        if (csvLine[*i] == ',') 
            (*i)++; 
    }
    (*i) += 2; 
    *arrayCount = pos; 
    return arrayy;
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
        perror("Memory allocation error in Character struct");
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

void print_string_array(char **arrayy, int count) {
    printf("{");
    for (int i = 0; i < count; i++) {
        printf("%s", arrayy[i]);
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

Character **array;   
int inicio, fim, n, arraySize;               

void start(){
    arraySize = MAXTAM;
    array = (Character **)malloc(arraySize * sizeof(Character *));
    inicio = fim = 0;
    n = 0;
}

void expand(){
    int newSize = arraySize * 2;
    if (newSize > MAXTAM) {
        newSize = MAXTAM;
    }

    Character **newArray = (Character **)malloc(newSize * sizeof(Character *));
    for (int i = 0; i < n; i++) {
        newArray[i] = array[(inicio + i) % arraySize];
    }
    free(array);

    array = newArray;
    arraySize = newSize;
    inicio = 0;
    fim = n;
}

void inserir(Character *character) {
    if (n == arraySize) {
        expand();
    }
    array[fim] = character;
    fim = (fim + 1) % arraySize;
    n++;
}

Character *remover() {
    if (n == 0) {
        printf("Queue is empty\n");
        return NULL;
    }
    Character *resp = array[inicio];
    inicio = (inicio + 1) % arraySize;
    n--;
    return resp;
}

void mostrar() {
   int i = inicio;
   for (int j = 0; j < n; j++) {
      print_character(array[i]);
      i = (i + 1) % arraySize;
   }
}

void free_string_array(char **arrayy, int length) {
    for (int i = 0; i < length; i++) {
        free(arrayy[i]);
    }
    free(arrayy);
}

void free_character(Character *character) {
    if (character == NULL) {
        return;
    }

    free(character->id);
    free(character->name);

    if (character->alternateNames) {
        free_string_array(character->alternateNames, character->alternateNamesCount);
    }

    free(character->house);
    free(character->ancestry);
    free(character->species);
    free(character->patronus);
    free(character->actorName);

    if (character->alternateActors) {
        free_string_array(character->alternateActors, character->alternateActorsCount);
    }

    free(character->dateOfBirth);
    free(character->eyeColour);
    free(character->gender);
    free(character->hairColour);

    free(character);
}

int main() {
    start();

    FILE *file = fopen(FILE_PATH, "r");
    if (file == NULL) {
        printf("Failed to open the file.\n");
        return 1;
    }

    char buffer[1024];
    while (fgets(buffer, sizeof(buffer), file)) {
        Character *character = csvLine_mapper(buffer);
        inserir(character);
        mostrar();
    }

    fclose(file);

    return 0;
}
