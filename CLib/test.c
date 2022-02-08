#include <stdio.h>
void helloWorld();
void helloWorld(){
    printf("Hello World");
}
void PTTest(char* a,float *b);
void PTTest(char* a,float *b){
    printf("%s : %f",a , *b);
}
int *p;
void PIntTest(int *a);
void PIntTest(int *a){
    p=a;
}
void PIntChange(int a);
void PIntChange(int a){
    *p=a;
}
