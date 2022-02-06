# @RequestBody로 넘어오는 객체에는 기본 생성자가 필요하다. (Setter는 불필요)


## Post 요청

- Setter함수 없이 POST 요청

```jsx
@Getter
@ToString
@NoArgsConstructor
public class RequestSetterDto {
    private String name;
    private Long amount;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate date;

    private RequestType requestType;

    @Builder
    public RequestSetterDto(@Nonnull String name, @Nonnull Long amount) {
        this.name = name;
        this.amount = amount;
    }

    public RequestSetterDto(String name, Long amount, LocalDate date, RequestType requestType) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.requestType = requestType;
    }

    @AllArgsConstructor
    public enum RequestType {
        GET ("get"),
        POST ("post");

        private String method;
    }

}
```

- DTO를 수신할 Controller

```jsx
@Slf4j
@RestController
public class RequestDtoSetterController {

    @PostMapping("/request/setter")
    public RequestSetterDto postRequestSetter (@RequestBody RequestSetterDto requestSetterDto) {
        log.info(requestSetterDto.getName() + " : " + requestSetterDto.getAmount());

        return requestSetterDto;
    }
}
```

- Post로 Request DTO 값을 채워 보낸다.

```jsx
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RequestDtoSetterController.class)
public class RequestDtoSetterControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void RequestBody에서는_setter가_없어도된다() throws Exception {
        String content = objectMapper.writeValueAsString(new RequestSetterDto("LOKI", 1000L));
        mvc
                .perform(post("/request/setter")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(content));
    }
}
```

> 참고
> 

**[@Request Body에서는 Setter가 필요없다?](https://jojoldu.tistory.com/407)**
