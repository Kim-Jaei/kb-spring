package org.scoula.security.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class JsonResponse {
    public static <T> void send(HttpServletResponse response, T result) throws IOException {
        ObjectMapper om = new ObjectMapper();

        response.setContentType("application/json;charset=UTF-8");
        Writer out = response.getWriter();
        out.write(om.writeValueAsString(result)); // json 직렬화
        out.flush();
    }

    // 가장 많이 만나는 에러 : 401
    // HttpStatus : enum
    public static void sendError(HttpServletResponse response, HttpStatus status, String message) throws IOException {
        response.setStatus(status.value()); // 에러 출력
        response.setContentType("application/json;charset=UTF-8");
        Writer out = response.getWriter();
        out.write(message);
        out.flush();
    }
}
