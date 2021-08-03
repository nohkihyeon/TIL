# Threads Program

## 
- 
```c
void matrix_mul_th_kernel(int i, int** a, int** b, int** c, int len)
{
    int j,k;
    for(j=0; j<len; j++){
        c[i][j] = 0;
        for(k=0; k<len; k++){
            c[i][j] += a[i][k] *b[k][j];
        }
    }
}
int matrix_mul_th(int** src1, int** src2, int** dst, int len)
{
    int i,k,j;
    for(i=0; i<len; i++){
        matrix_mul_th_kernel(i, src1, src2, dst, len);
    }
    return 0;
}

```
- thread Syntax in c
```c
int pthread_create(pthread_t * thread, 
                   const pthread_attr_t * attr, 
                   void * (*start_routine)(void *), 
                   void *arg);
```

- ??
```C
#include<unistd.h>
#include<pthread.h>

...
typedef struct{
    int i;
    int** src1;
    int** src2;
    int** dst;
    int len;
} matmul_arg_t;
void matrix_mul_th_kernel(void *arg)
{
    int j,k;
    matmul_arg_t *parg = (matmul_arg_t*)arg;
    int i = parg->i;
    int **src1 = parg->src1;
    int **src2 = parg->src2;
    int **dst = parg->dst;
    int len = parg->len;
    for(j=0; j<len; j++){
        dst[i][j] = 0;
        for(k=0; k<len; k++){
            dst[i][j] += src1[i][k] *src2[k][j];
        }
    }
}
int matrix_mul_th(int** src1, int** src2, int** dst, int len)
{
    int              i,k,j, res;
    matmul_arg_t     arg;
    pthread_t        a_thread;
    void             *thread_result;

    for(i=0; i<len; i++){
        arg.i = i;
        arg.src1 = src1;
        arg.src2 = src2;
        arg.dst = dst;
        arg.len = len;
        //matrix_mul_th_kernel((void*)&arg);
        res = pthread_create(&a_thread, NULL, matrix_mul_th_kernel, (void*)&arg);
        if(res !=0){
            perror("Thread creation failed\n");
            exit(EXIT_FAILURE);
        }
    }
    for(i=0; i<len; i++){
        res = pthread_join(a_thread, &thread_result);
        if(res !=0){
            perror("Thread join failed");
            exit(EXIT_FAILURE);
        }
    }
    return 0;
}


```
- Makefile
```Makefile

Target=matrix
obj = main.o matrix.o
LFLAGS = -pthread -lpthread
CFLAGS = -D_REENTRANT

all : $(obj)
    gcc -o $(Target) $(LFLAGS) $(obj)

main.o: main.c
    gcc -c $(CFLAGS) main.c

matrix.o: matrix.c
    gcc -c $(CFLAGS) matrix.c

clean:
    rm -r *.o $(Target)

```
