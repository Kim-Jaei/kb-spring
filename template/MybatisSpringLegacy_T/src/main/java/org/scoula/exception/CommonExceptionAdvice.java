package org.scoula.exception;

import lombok.extern.log4j.Log4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
@Log4j
public class CommonExceptionAdvice {


    @ExceptionHandler(Exception.class)
    public String except(Exception ex, Model model){

        log.error("Exception ........."+ex.getMessage());
        model.addAttribute("exception", ex);
        log.error(model);
        return "error_page";
    }


    //03.3 39p
    public String handle404(NoHandlerFoundException ex){
        return "cumstom404";
    }



}
