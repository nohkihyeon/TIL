# 단일 쓰레드 행렬 프로그램

## 중복된 코드를 정리
- 간단한 행렬곱 프로그램을 c
```c
#include<stdio.h>
#include<stdlib.h>
#define MAX_LEN 4

int main()
{
    int a[MAX_LEN][MAX_LEN], b[MAX_LEN][MAX_LEN], c[MAX_LEN][MAX_LEN];
    int i, j, k;

    for(i=0; i<MAX_LEN; i++){
        for(j=0; j<MAX_LEN; j++){
            a[i][j] = random() % 10;
            b[i][j] = random() % 10;
        }
    }


    for(i=0; i<MAX_LEN; i++){
        for(j=0; j<MAX_LEN; j++){
            // c[i][j] = a[i][j] + b[i][j];
            c[i][j] = 0;
            for(k=0; k<MAX_LEN; k++){
                c[i][j] += a[i][k] *b[i][j];
            }
        }
    }

    printf("==a Matrix==============\n");
    for(i=0; i<MAX_LEN; i++){
        for(j=0; j<MAX_LEN; j++){
            printf("%d", a[i][j]);
        }
        printf("\n");
    }
    printf("==b Matrix==============\n");
    for(i=0; i<MAX_LEN; i++){
        for(j=0; j<MAX_LEN; j++){
            printf("%d", b[i][j]);
        }
        printf("\n");
    }
    printf("==c Matrix==============\n");
    for(i=0; i<MAX_LEN; i++){
            for(j=0; j<MAX_LEN; j++){
            printf("%d", c[i][j]);
        }
        printf("\n");
    }

    return 0;
}


```
- 행렬을 출력하는 부분이 중복 -> 함수화
```c
#include<stdio.h>
#include<stdlib.h>
#define MAX_LEN 4

void print_matrix(int [][MAX_LEN], char* name);

int main()
{
    int a[MAX_LEN][MAX_LEN];
    int b[MAX_LEN][MAX_LEN];
    int c[MAX_LEN][MAX_LEN];
    int i, j, k;

    for(i=0; i<MAX_LEN; i++){
        for(j=0; j<MAX_LEN; j++){
            a[i][j] = random() % 10;
            b[i][j] = random() % 10;
        }
    }


    for(i=0; i<MAX_LEN; i++){
        for(j=0; j<MAX_LEN; j++){
            // c[i][j] = a[i][j] + b[i][j];
            c[i][j] = 0;
            for(k=0; k<MAX_LEN; k++){
                c[i][j] += a[i][k] *b[i][j];
            }
        }
    }
    print_matrix(a, "a");
    print_matrix(b, "b");
    print_matrix(c, "c");
    return 0;
}
void print_matrix(int matrix[][MAX_LEN], char* name)
{
    int i,j;
    printf("==%s Matrix==============\n", name);
    for(i=0; i<MAX_LEN; i++){
        for(j=0; j<MAX_LEN; j++){
            printf("%d ", matrix[i][j]);
            }
        printf("\n");
    }
}


```
- random은 정해진 수를 리턴한다. 따라서 한번 정해진 값은 계속 똑같이 할당 <br>
![image](https://user-images.githubusercontent.com/65120581/127947790-8d598a04-db29-425a-8fe7-447fe3868451.png)

- `man random` <br>
![image](https://user-images.githubusercontent.com/65120581/127947874-0211b03b-2cd6-4a84-a04f-d84522cefdbc.png)
- void srandom(unsigned int seed);를 이용하면 SEED를 주어서 시작점을 정해준다.
- `man time` <br>
![image](https://user-images.githubusercontent.com/65120581/127948134-3044f850-9b3e-442a-a17e-ff511088c2e6.png)

- 결과값이 random하게 출력한다. ` srandom((unsigned int)time(NULL));` 추가 <br>
![image](https://user-images.githubusercontent.com/65120581/127969035-03f7f59a-bec6-4507-a299-d955040cc2db.png)

![image](https://user-images.githubusercontent.com/65120581/127948327-7ea6255b-c9fa-43bf-a2e6-735674174646.png)

- MAX_LEN이 1000인 경우 <br>
![image](https://user-images.githubusercontent.com/65120581/127948612-8eefa9dd-7853-4acf-9eaa-5e15477a269f.png)

- 세그멘테이션 오류플 예방하기 위해서 동적할당(malloc) 필요 <br>
![image](https://user-images.githubusercontent.com/65120581/127968953-e6dda834-99b6-48f0-9151-27d0716fdd69.png)


- `time ./main` 입력 화면 <br>
![image](https://user-images.githubusercontent.com/65120581/127949180-082fef5c-aa95-45c6-ac01-3aefab9311ca.png)

## file 분리하기
- 파일 분리를 위해 수평분할(:sp)를 이용 [[Vi]화면 분할 하기](https://github.com/nohkihyeon/TIL/blob/main/Linux/seperate.md)
![refactoring](https://user-images.githubusercontent.com/65120581/127965466-985b381e-6f3a-44fd-b909-f915388c2111.gif)

![moveFunction](https://user-images.githubusercontent.com/65120581/127965775-1deb2182-071c-4c8d-b8b0-7748113d2685.gif)

![moveFunc](https://user-images.githubusercontent.com/65120581/127965936-918f796c-6c51-4663-86c4-68be1a8f7d8a.gif)

- 분리 후 main.c
```c
#include<stdio.h>
#include<stdlib.h>
#define MAX_LEN 5
#include<time.h>
#include "matrix.h"

int main()
{
    int **a;
    int **b;
    int **c;
    int i, j, k;
    clock_t start, stop;

    init_matrix(&a, &b, &c, MAX_LEN);

    start = clock();
    matrix_mul(a, b, c, MAX_LEN);
    stop = clock();
    print_matrix(a, "a", MAX_LEN);
    print_matrix(b, "b", MAX_LEN);
    print_matrix(c, "c", MAX_LEN);

    printf("Processing time : %f\n", ((double)stop-start)/CLOCKS_PER_SEC);
    return 0;
}

```
- 분리 후 matrix.h
```C
#include<time.h>
#include<stdio.h>
void matrix_mul(int** a, int** b, int** c, int len)
{
    int i,j,k;
    for(i=0; i<len; i++){
        for(j=0; j<len; j++){
            // c[i][j] = a[i][j] + b[i][j];
            c[i][j] = 0;
            for(k=0; k<len; k++){
                c[i][j] += a[i][k] *b[i][j];
            }
        }
    }
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
- 분리 후 matrix.h <br>
```c
void matrix_add(int **, int**, int**, int len);
void matrix_sub(int **, int**, int**, int len);
void matrix_mul(int **, int**, int**, int len);
void matrix_inv(int **, int**,int len);
void init_matrix(int***, int***, int***, int len);
void print_matrix(int **, char*, int len);
```
- 결과를 얻기 위해 많은 과정이 필요 <br>
![image](https://user-images.githubusercontent.com/65120581/127962489-0ad7cc7a-bfcd-4ff9-b0f3-822295b0cb44.png)
  - make를 사용하는 이유 [make와 Makefile](https://github.com/nohkihyeon/TIL/blob/main/Linux/whyMake.md)

```Makefile
Target=matrix
obj = main.o matrix.o

all : $(obj)
    gcc -o $(Target) $(obj)

main.o: main.c
    gcc -c main.c

matrix.o: matrix.c
    gcc -c matrix.c

clean:
    rm -r *.o $(Target)

```
- make를 이용해 간단히 컴파일하는 모습  <br>
![result](https://user-images.githubusercontent.com/65120581/127968058-fce631f7-09ce-48fb-87ae-85f102e3043d.gif)

> ## 출처

### [리눅스 시스템 프로그래밍](https://www.youtube.com/watch?v=6lYG3w0I4uY&list=PL_Whxrj87Gsi3wvvVqOes4daHw1A9VFoD&index=8)
