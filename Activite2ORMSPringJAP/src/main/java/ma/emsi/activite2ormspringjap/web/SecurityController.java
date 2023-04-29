package ma.emsi.activite2ormspringjap.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class SecurityController {

    @GetMapping("/notAuthorized")
    public  String notAuthorized(){
        return  "notAuthorized";
    }
    @GetMapping("/login")
    public  String loginPage(){
        return  "login";
    }
}
