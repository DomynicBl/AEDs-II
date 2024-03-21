#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

int main() {
    srand(4);

    char palavra[1000];

    while(fgets(palavra, 1000, stdin)){
        palavra[strcspn(palavra, "\n")] = '\0';

        if (strcmp(palavra, "FIM") == 0) {
            break;
        }
        Alteracao(palavra);
    }
    return 0;
}

void Alteracao(char *palavra) {
    char letra1 = 'a' + (rand() % 26);
    char letra2 = 'a' + (rand() % 26);

    for (int i = 0; palavra[i] != '\0'; i++) {
        if (palavra[i] == letra1) {
            palavra[i] = letra2;
        }
    }

    printf("%s\n", palavra);
}