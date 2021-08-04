# Threads Program
- 행렬의 곱을 출력하는 함수를 다중 쓰레드를 이용하는 예제


# thread Syntax in c
```c
int pthread_create(pthread_t * thread, 
                   const pthread_attr_t * attr, 
                   void * (*start_routine)(void *), 
                   void *arg);
```
## 매개변수
### 1. thread
- 쓰레드가 성공적으로 생성되었을 때 생성된 쓰레드를 식별하기 위해서 사용되는 쓰레드 식별자이다.
### 2. attr
- 쓰레드 특성을 지정하기 위해서 사용하며, 기본 쓰레드 특성을 이용하고자 할 경우에는 NULL 사용
### 3. start_routine
- 분기시켜서 실행할 쓰레드 함수
### 4. arg
- start_routine 쓰레드 함수의 매개변수로 넘겨진다.

### 성공적으로 생성될 경우 0을 return 
### main.c
```C
#include<stdio.h>
#include<stdlib.h>
#define MAX_LEN 30
#include<time.h>
#include "matrix.h"
#include<sys/time.h>

int main()
{
    int **a;
    int **b;
    int **c;
    int i, j, k;
    double start, stop;

    init_matrix(&a, &b, &c, MAX_LEN);

    start = clock();
    matrix_mul(a, b, c, MAX_LEN);
    stop = clock();
    start = wtime();

    if(matrix_mul(a, b, c, MAX_LEN)!=0){
        fprintf(stderr, "Failed to Matrix multiplecation\n");
        exit(1);
    }
    stop = wtime();
    printf("single-thread-Proccessing time : %lf\n", stop-start);

    start = wtime();
    if(matrix_mul_th(a,b,c, MAX_LEN) !=0){
        fprintf(stderr, "Failed to Matrix Multiplecation\n");
        exit(1);
    }
    stop = wtime();
    printf("multi-thread-Processing time : %lf\n", stop - start);
    return 0;
}

double wtime(){
    static int sec = -1;
    struct timeval tv;
    gettimeofday(&tv, NULL);
    if(sec < 0) sec = tv.tv_sec;

    return (tv.tv_sec - sec) + 1.0e-6 * tv.tv_usec;
}

```
### matrix.h
```c
void matrix_add(int **, int**, int**, int len);
void matrix_sub(int **, int**, int**, int len);
int matrix_mul(int **, int**, int**, int len);
int matrix_mul_th(int **, int**, int**, int len);
void matrix_mul_th_kernel(int, int **, int**, int**, int len);
void matrix_inv(int **, int**,int len);
void init_matrix(int***, int***, int***, int len);
void print_matrix(int **, char*, int len);
double wtime();

```

### matrix.c
```c
#include<time.h>
#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<pthread.h>

int matrix_mul(int** a, int** b, int** c, int len)
{
    int i,j,k;
    int result =0;
    for(i=0; i<len; i++){
        for(j=0; j<len; j++){
            // c[i][j] = a[i][j] + b[i][j];
            result = 0;
            for(k=0; k<len; k++){
                result += a[i][k] *b[k][j];
            }
            c[i][j] = result;
        }
    }
    return 0;
}

typedef struct{
    int i;
    int** src1;
    int** src2;
    int** dst;
    int len;
} matmul_arg_t;

void *matrix_mul_th_kernel(void *arg)
{
    int j,k, result;
    matmul_arg_t *parg = (matmul_arg_t*)arg;
    int i = parg->i;
    int **src1 = parg->src1;
    int **src2 = parg->src2;
    int **dst = parg->dst;
    int len = parg->len;
    for(j=0; j<len; j++){
        result = 0;
        for(k=0; k<len; k++){
            result += src1[i][k] *src2[k][j];
        }
        dst[i][j] = result;
    }
}

int matrix_mul_th(int** src1, int** src2, int** dst, int len)
{
    int              i,k,j, res;
    matmul_arg_t     *arg;
    pthread_t        *a_thread;
    void             *thread_result;

    a_thread = (pthread_t *)malloc(sizeof(pthread_t) * len);

    for(i=0; i<len; i++){
        arg = (matmul_arg_t*)malloc(sizeof(matmul_arg_t));
        arg->i = i;
        arg->src1 = src1;
        arg->src2 = src2;
        arg->dst = dst;
        arg->len = len;
        //matrix_mul_th_kernel((void*)&arg);
        res = pthread_create(a_thread+i, NULL, matrix_mul_th_kernel, (void*)arg);
        if(res !=0){
            perror("Thread creation failed\n");
            exit(EXIT_FAILURE);
        }
    }
    for(i=0; i<len; i++){
        res = pthread_join(a_thread[i], &thread_result);
        if(res !=0){
            perror("Thread join failed");
            exit(EXIT_FAILURE);
        }
    }
    return 0;
}
int matrix_add(int** a, int** b, int** c, int len)
{
}
int matrix_sub(int** a, int** b, int** c, int len)
{
}
int matrix_inv(int** src, int** dst, int len)
{

}
void print_matrix(int** matrix, char* name, int len)
{
    int i,j;
    printf("==%s Matrix==============\n", name);
    for(i=0; i<len; i++){
        for(j=0; j<len; j++){
            printf("%d ", matrix[i][j]);
        }
        printf("\n");
    }
}
void init_matrix(int*** p_a, int*** p_b, int*** p_c, int len)
{
    int **a;
    int **b;
    int **c;
    int i, j;
    a = (int**)malloc(len * sizeof(int*));
    b = (int**)malloc(len * sizeof(int*));
    c = (int**)malloc(len * sizeof(int*));

    for(i=0; i< len; i++){
        a[i] = (int*)malloc(len * sizeof(int));
        b[i] = (int*)malloc(len * sizeof(int));
        c[i] = (int*)malloc(len * sizeof(int));
    }
    srandom((unsigned int)time(NULL));
    for(i=0; i<len; i++){
        for(j=0; j<len; j++){
            a[i][j] = random() % 10;
            b[i][j] = random() % 10;
        }
    }
    *p_a = a;
    *p_b = b;
    *p_c = c;
}

```
### 컴파일 명령어
- gcc matrix.c -o matrix -pthread -lpthread

### Makefile
```Makefile

Target = matrix
obj = main.o matrix.o
LFLAGS = -pthread -lpthread             // 1.
CFLAGS = -D_REENTRANT                   // 2.

all : $(obj)
    gcc -o $(Target) $(LFLAGS) $(obj)

main.o: main.c
    gcc -c $(CFLAGS) main.c

matrix.o: matrix.c
    gcc -c $(CFLAGS) matrix.c

clean:
    rm -r *.o $(Target)

```
#### 1. 스레드 관련 라이브러리를 링크하는 옵션
#### 2. 스레드에서 동작할 때 성능상 문제가 발생할 수 있는 스레드 불안전 함수가 존재한다. 컴파일러가 알아서 불안전 함수를 대체할 수 있게 지원해 주는 링커 옵션 

#### 정리
- CC : 컴파일러
- CFLAGS : 
- OBJS : 중간 산물 Object 파일 목록
- TARGET : 빌드 대상(실행 파일) 이름
- LDFLAGS : 링크 옵션
- LDLIBS : 링크 라이브러리
- $@ : 현재 Target 이름
- $^ : 현재 Target이 의존하는 대상들의 전체 목록
- $? : 현재 Target이 의존하는 대상들 중 변경된 것들의 목록

### 파일 생성된 모습
![image](https://user-images.githubusercontent.com/65120581/128149772-89655075-3f30-44f0-b081-c11f0f429c0e.png)

### `$ make` 

![image](https://user-images.githubusercontent.com/65120581/128149887-9777675f-9aa8-43e2-9731-111f53d706c4.png)

### `$ make all`
![image](https://user-images.githubusercontent.com/65120581/128150013-3a42b839-650d-435e-b53b-500c91116a82.png)


### ` $ make clean`
![image](https://user-images.githubusercontent.com/65120581/128149715-000532f8-06d8-4fb1-91c7-cd3c3fe79fc2.png)

<br>
<br>
<br>
<br>

> ## 참조

> ### [[Make 튜토리얼] Makefile 예제와 작성 방법 및 기본 패턴](https://www.tuwlab.com/ece/27193)
> ### [리눅스 시스템 프로그래밍 - 쓰레드](https://www.youtube.com/watch?v=YoBcutp4wl0&t=2845s)
