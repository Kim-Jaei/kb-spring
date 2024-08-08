package org.scoula.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor
public class Restaurant {
//    @Autowired : 최근 사용 자제
    final private Chef chef;
    // @Autowired
    // Restaurant(Chef chef)
}
