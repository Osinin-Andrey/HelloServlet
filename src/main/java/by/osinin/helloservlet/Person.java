package by.osinin.helloservlet;

import lombok.*;

@AllArgsConstructor
@Data //что бы вставить все конструкторы (геттеры, сеттеры и т.д.)
@NoArgsConstructor
public class Person {
    private int age;
    private String name;
    private String login;
    private String password;


}
