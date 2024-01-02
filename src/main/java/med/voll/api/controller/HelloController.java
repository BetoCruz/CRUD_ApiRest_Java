package med.voll.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RestController // anotação usada para Apis rest
@RequestMapping("/hello") //Diz qual é o mapeamento , qual a URL que esse controler vai resposnder.
public class HelloController {

    @GetMapping
    public String olaMundo(){
        return "Hello World Spring";
    }




}
