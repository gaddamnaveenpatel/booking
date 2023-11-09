package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Service.ScreenService;
import com.example.BookMyShow.model.Screen;
import com.example.BookMyShow.model.Theatre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/screens")
public class ScreenController {

    @Autowired
 private ScreenService screenService;



    @PostMapping("/create/{the_id}")
    public ResponseEntity<Screen> saveScreen(@RequestBody Screen screen , @PathVariable Theatre the_id){
Theatre savetheatre = new Theatre();

savetheatre.setThe_id(the_id.getThe_id());

screen.setTheatre(savetheatre);


Screen screen1 = screenService.createScreen(screen, savetheatre.getThe_id());
        return ResponseEntity.status(HttpStatus.CREATED).body(screen1);


    }

}
