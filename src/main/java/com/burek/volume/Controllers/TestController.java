package com.burek.volume.Controllers;

import com.burek.volume.Requests.OrmRequest;
import com.burek.volume.Requests.PlanCreationRequest;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/orm")
    @ResponseBody
    public String getEmployeesById(@RequestParam float w, String u, int r) {
        int orm = ormCalc(w, r);
        return "Estimated 1 rep max: "+orm+" "+u;
    }

    private int ormCalc(float weight, int reps){
        float multiplier = 37-reps;
        multiplier=36/multiplier;
        return Math.round(weight*multiplier);
    }

}
