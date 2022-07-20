package com.example.demo.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {

    private String name;
    private String email;

    @Override
    public String toString() {
        return String.format("멤버 (이름 = %s, 이메일 = %s)", name, email);
    }
}

