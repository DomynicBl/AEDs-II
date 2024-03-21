#include <stdio.h>
#include <stdlib.h>

int main() {
    FILE *file;
    int n;
    double value;

    printf("Digite o numero de valores: ");
    scanf("%d", &n);

    file = fopen("valores.txt", "w");
    if (file == NULL) {
        printf("Erro ao abrir o arquivo.");
        return 1;
    }

    for (int i = 0; i < n; i++) {
        printf("Digite o valor %d: ", i + 1);
        scanf("%lf", &value);
        fprintf(file, "%lf\n", value);
    }

    fclose(file);


    file = fopen("valores.txt", "r");
    if (file == NULL) {
        printf("Erro ao abrir o arquivo.");
        return 1;
    }

    fseek(file, 0, SEEK_END);
    long length = ftell(file);
    long position = length - 1;
    fseek(file, position, SEEK_SET);

    char c;
    while (position >= 0) {
        fseek(file, position, SEEK_SET);
        c = fgetc(file);
        if (c == '\n') {
            position--;
            fseek(file, position, SEEK_SET);
            c = fgetc(file);
        }
        printf("%c", c);
        position--;
    }

    fclose(file);
    return 0;
}
