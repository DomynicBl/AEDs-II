#include <stdio.h>
#include <string.h>

int main(){
    char palavra[100];

    fgets(palavra, 100, stdin);
    palavra[strcspn(palavra, "\n")] = '\0';
    
    while(strcmp(palavra, "FIM") != 0){
        char palavraInvertida[100];
        int j = 0;

        for(int i= strlen(palavra) -1; i>=0; i--){
            palavraInvertida[j] = palavra[i];
            j++;
        }
        palavraInvertida[j] = '\0';

        if(strcmp(palavra, palavraInvertida) == 0){
            printf("SIM\n");
        }else{
            printf("NAO\n");
        }

        fgets(palavra, 100, stdin);
        palavra[strcspn(palavra, "\n")] = '\0';
    }

    return 0;
}