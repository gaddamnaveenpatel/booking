package com.example.BookMyShow.Service;

import com.example.BookMyShow.Repostiory.TheatreRepo;
import com.example.BookMyShow.model.Screen;
import com.example.BookMyShow.model.Theatre;

public interface ScreenService {


    Screen createScreen(Screen screen, Integer the_id);

}
