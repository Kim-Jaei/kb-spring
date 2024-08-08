package org.scoula.exception;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice // 컨트롤러에서 예외가 발생하면 여기서 받겠다
@Log4j
// 사실상 컨트롤러
public class CommonExceptionAdvice {

    // catch할 예외 제시 -> 여기서는 모든 예외(500)
    @ExceptionHandler(Exception.class)
    public String expect(Exception ex, Model model) { // 컨트롤러이기 때문에 return값 동일 == view 이름
        log.error("Exception", ex);
        model.addAttribute("exception", ex);
        log.error(model);
        return "error_page";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404 에러 처리
    public String handl404(NoHandlerFoundException ex){
        return "custom404";
    }
}
