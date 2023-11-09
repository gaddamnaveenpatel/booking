package com.example.BookMyShow.ServiceImp;

import com.example.BookMyShow.Excpetion.TheatreNotfoundException;
import com.example.BookMyShow.Repostiory.ScreenRepo;
import com.example.BookMyShow.Repostiory.TheatreRepo;
import com.example.BookMyShow.Service.ScreenService;
import com.example.BookMyShow.model.Screen;
import com.example.BookMyShow.model.Theatre;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScreenServicimp implements ScreenService {

    @Autowired
    private TheatreRepo theatreRepo;

    @Autowired
    private ScreenRepo screenRepo;

    @Override
    public Screen createScreen(Screen screen, Integer the_id) {

//        Theatre theatre = theatreRepo.findById(the_id).
//                orElseThrow(()-> new TheatreNotfoundException("Theatre not found with ID: " + the_id))
//
//                screen.setTheatre(theatre);
//        return screenRepo.save(screen);


        Optional<Theatre>optionalTheatre = theatreRepo.findById(the_id);

        if(optionalTheatre.isPresent()){

            Theatre theatre = optionalTheatre.get();

            screen.setTheatre(theatre);
            return screenRepo.save(screen);
        } else {

            throw new TheatreNotfoundException("Theatre not found with ID: " + the_id);
        }
    }
}
