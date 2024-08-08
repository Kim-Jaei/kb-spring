package org.scoula.dto;

import lombok.Data;

@Data // 보일러플레이트 코드를 생략할 수 있음
public class SampleDTO {
    private String name;
    private int age;

    // toSting() 오버라이딩 메서드
    // return "Sample1(a=" + this.getA() + ", b=" + this.getB() + ")";
}
