package br.com.devdojo.javaclient;

import br.com.devdojo.model.PageableResponse;
import br.com.devdojo.model.Student;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class JavaSpringClientTest {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri("http://localhost:8090/v1/protected/students")
                .basicAuthorization("lucas","teste123")
                .build();

        Student student = restTemplate.getForObject("/{id}", Student.class, "9");
        ResponseEntity<Student> forEntity = restTemplate.getForEntity("/{id}", Student.class, "9");
        //Student[] studentArray = restTemplate.getForObject("/", Student[].class);

        ResponseEntity<PageableResponse<Student>> exchange =  restTemplate.exchange("/", HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<Student>>()
        {});


        //System.out.println(student);
        //System.out.println(forEntity);
        //System.out.println(Arrays.toString(studentArray));
        System.out.println(exchange);
    }
}
