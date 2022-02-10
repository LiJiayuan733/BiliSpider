#include <iostream>
#include <cstring>
#include <sys/types.h>
#include <sys/shm.h>
#include <sys/ipc.h>
#include "SharedMemory.hpp"

#define BUFSZ 4096
#define SUCCESS 1
#define FAILURE 0
#define GETFALIURE -1
#define NOTINIT_KEY -2
#define NOTINIT_MEMORY -3
int memory_size=-1;
key_t key=0;
int shmid=0;
char* shmadd=nullptr;
/*
1.获取key
2.GetMemory 获取共享内存 shmid
3.GetSharedMemory 获取内存指针 shmadd
*/
int GetKey(int skey){
    key=ftok("/",skey);
    if(key != GETFALIURE){
        return SUCCESS;
    }else{
        return FAILURE;
    }
}
int GetMemory(){
    return GetMemoryOfSize(BUFSZ);
}
int GetMemoryOfSize(int size){
   if(key==0){
        return NOTINIT_KEY;
    }else{
        shmid = shmget(key,size,IPC_CREAT|0777);
        if(shmid < 0){
            memory_size=size;
            return FAILURE;
        }else{
            return SUCCESS;
        }
    }
}
int GetSharedMemory(){
    shmadd=static_cast<char*>(shmat(shmid, NULL, 0));
    if(shmadd < (char*)0){
        return FAILURE;
    }else{
        return SUCCESS;
    }
}
char* GetByte(){
    return shmadd;
}
int GetSize(){
    return memory_size;
}
void ClearMemory(int index,int size){
    memset(shmadd+index,0,size);
}
void ClearMemoryAll(){
    memset(shmadd,0,memory_size);
}
void SetMemory(int index,const char* object){
    strcpy(shmadd+index,object);
}
void Close(){
    shmdt(shmadd);
}
void RemoveMemory(){
    shmctl(shmid, IPC_RMID, NULL);
}