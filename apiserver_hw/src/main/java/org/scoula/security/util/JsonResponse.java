package org.scoula.security.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class JsonResponse { // JSON 형식(직렬화)의 응답을 클라이언트에게 보내는 작업 수행

    // JSON 형식으로 변환 후 HTTP 응답으로 전송
    public static <T> void send(HttpServletResponse response, T result) throws IOException { // <T> 어떤 타입의 객체든 JSON 변환
        ObjectMapper om = new ObjectMapper(); // Jackson
        response.setContentType("application/json;charset=UTF-8"); // 응답 콘텐츠 타입: JSON, UTF-8

        // Writer : 응답 본문에 데이터를 작성하고, 클라이언트에게 전송
        // 텍스트 데이터를 전송할 수 있도록 도와주는 Writer 객체를 반환
        Writer out = response.getWriter();
        out.write(om.writeValueAsString(result)); // out.write() : 텍스트 응답
        out.flush(); // 버퍼를 비워 실제로! 응답이 전송되도록 함
    }

    // HTTP 상태 코드 & 에러 메시지를 JSON 형식으로 응답
    public static void sendError(HttpServletResponse response, HttpStatus status, String message) throws IOException {
        response.setStatus(status.value()); // 응답의 상태 코드 설정
        response.setContentType("application/json;charset=UTF-8"); // 응답 type
        Writer out = response.getWriter();
        out.write(message); // 에러 메시지를 JSON 형식으로 응답에 저장
        out.flush(); // 전송
    }

}
