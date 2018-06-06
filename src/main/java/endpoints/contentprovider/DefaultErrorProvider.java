package endpoints.contentprovider;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultErrorProvider implements ErrorController {

    @RequestMapping("/error")
    public String error(){
        return "lol, not found";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
