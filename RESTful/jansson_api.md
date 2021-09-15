# String
Jansson은 UTF-8을 인코딩으로 사용

## 1. json_t *json_string(const char * value)
- JSON 문자열을 반환하거나 오류 발생 시 NULL 반환

## 2. json_t *json_stringn(const char * value, size_t len)
- JSON 문자열을 반환하거나 오류 발생 시 NULL 반환


# Array

## 1. json_t *json_array(void)
- 반환 값 : JSON 배열 / NULL


## 2. int json_array_append(json_t *array, json_t *value)
- array 끝에 value 추가
- 반환 값
  - 성공 : 0
  - 실패 : -1  

## 3. size_t json_array_size(const json_t *array)
- 반환 값
  - NULL : array가 JSON array가 아닐 시
  - array size

# Encoding
## 1. int json_dump_file(const json_t *json, const char *path, size_t flags)
- 반환 값
  - 성공 : 0
  - 실패 : -1
# output

## 1. char *json_dumps(const json_t *json, size_t flags)

# Reference Count

## 1. json_t *json_incref(json_t *json)
- json 참조 횟수 증가 NULL이 아니면 json return
## 2. void json_decref(json_t *json)
- json 참조 횟수 감소

# Type
## 1. enum json_type
## 2. int json_typeof(const json_t *json)


# 소스코드
- simple_parse.c
```c
#include <jansson.h>
#include <stdio.h>
#include <stdlib.h>

/* forward refs */
void print_json(json_t *root);
void print_json_aux(json_t *element, int indent);
void print_json_indent(int indent);
const char *json_plural(size_t count);
void print_json_object(json_t *element, int indent);
void print_json_array(json_t *element, int indent);
void print_json_string(json_t *element, int indent);
void print_json_integer(json_t *element, int indent);
void print_json_real(json_t *element, int indent);
void print_json_true(json_t *element, int indent);
void print_json_false(json_t *element, int indent);
void print_json_null(json_t *element, int indent);

void print_json(json_t *root) { print_json_aux(root, 0); }

void print_json_aux(json_t *element, int indent) {
        switch (json_typeof(element)) {
                case JSON_OBJECT:
                        print_json_object(element, indent);
                        break;
                case JSON_ARRAY:
                        print_json_array(element, indent);
                        break;
                case JSON_STRING:
                        print_json_string(element, indent);
                        break;
                case JSON_INTEGER:
                        print_json_integer(element, indent);
                        break;
                case JSON_REAL:
                        print_json_real(element, indent);
                        break;
                case JSON_TRUE:
                        print_json_true(element, indent);
                        break;
                case JSON_FALSE:
                        print_json_false(element, indent);
                        break;
                case JSON_NULL:
                        print_json_null(element, indent);
                        break;
                default:
                        fprintf(stderr, "unrecognized JSON type %d\n", json_typeof(element));
        }
}

void print_json_indent(int indent) {
        int i;
        for (i = 0; i < indent; i++) {
                putchar(' ');
        }
}

const char *json_plural(size_t count) { return count == 1 ? "" : "s"; }

void print_json_object(json_t *element, int indent) {
        size_t size;
        const char *key;
        json_t *value;

        print_json_indent(indent);
        size = json_object_size(element);

        printf("JSON Object of %lld pair%s:\n", (long long)size, json_plural(size));
        json_object_foreach(element, key, value) {
                print_json_indent(indent + 2);
                printf("JSON Key: \"%s\"\n", key);
                print_json_aux(value, indent + 2);
        }
}

void print_json_array(json_t *element, int indent) {
        size_t i;
        size_t size = json_array_size(element);
        print_json_indent(indent);

        printf("JSON Array of %lld element%s:\n", (long long)size, json_plural(size));
        for (i = 0; i < size; i++) {
                print_json_aux(json_array_get(element, i), indent + 2);
        }
}

void print_json_string(json_t *element, int indent) {
        print_json_indent(indent);
        printf("JSON String: \"%s\"\n", json_string_value(element));
}

void print_json_integer(json_t *element, int indent) {
        print_json_indent(indent);
        printf("JSON Integer: \"%" JSON_INTEGER_FORMAT "\"\n", json_integer_value(element));
}

void print_json_real(json_t *element, int indent) {
        print_json_indent(indent);
        printf("JSON Real: %f\n", json_real_value(element));
}

void print_json_true(json_t *element, int indent) {
        (void)element;
        print_json_indent(indent);
        printf("JSON True\n");
}

void print_json_false(json_t *element, int indent) {
        (void)element;
        print_json_indent(indent);
        printf("JSON False\n");
}

void print_json_null(json_t *element, int indent) {
        (void)element;
        print_json_indent(indent);
        printf("JSON Null\n");
}

/*
 *  * Parse text into a JSON object. If text is valid JSON, returns a
 *   * json_t structure, otherwise prints and error and returns null.
 *    */
json_t *load_json(const char *text) {
        json_t *root;
        json_error_t error;

        root = json_loads(text, 0, &error);

        if (root) {
                return root;
        } else {
                fprintf(stderr, "json error on line %d: %s\n", error.line, error.text);
                return (json_t *)0;
        }
}

/*
 *  * Print a prompt and return (by reference) a null-terminated line of
 *   * text.  Returns NULL on eof or some error.
 *    */
char *read_line(char *line, int max_chars) {
        printf("Type some JSON > ");
        fflush(stdout);
        return fgets(line, max_chars, stdin);
}

/* ================================================================
 *  * main
 *   */

#define MAX_CHARS (1024 * 8)

int main(int argc, char *argv[]) {
        char line[MAX_CHARS];

        if (argc != 1) {
                fprintf(stderr, "Usage: %s\n", argv[0]);
                exit(-1);
        }

        fprintf ( stdout, "example : [true, false, null, 1, 0.0, -0.0, \"\", {\"name\": \"barney\"}]\n\n" ) ;
        fprintf ( stdout, "Press \'q\' to exit.\n\n" ) ;

        while (read_line(line, MAX_CHARS) != (char *)NULL) {
                if ( !strncmp(line, "q", 1) )
                        break ;

                /* parse text into JSON structure */
                json_t *root = load_json(line);

                if (root) {
                        /* print and release the JSON structure */
                        print_json(root);
                        json_decref(root);
                }
        }

        return 0;
}

```

## 결과화면
![image](https://user-images.githubusercontent.com/65120581/133383515-bddf64f0-9e8f-4c00-a203-867916c299f5.png)
<br>
![image](https://user-images.githubusercontent.com/65120581/133383556-23e57cc0-6d90-4e1d-a93b-c321e69e3e5f.png)




<br>
<br><br><br><br><br><br>
<br><br><br><br><br><br>

## 참조
[jansson 2.8](https://jansson.readthedocs.io/en/2.8/apiref.html)
