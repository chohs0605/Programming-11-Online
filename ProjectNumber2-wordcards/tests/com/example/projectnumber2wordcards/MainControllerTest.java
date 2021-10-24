package com.example.projectnumber2wordcards;

import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import jdk.jfr.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainControllerTest {
    @Test
    void save() {
        // Set username to junit
        MainController.loginId = "junit";

        // insert data into knowList
        MainController main = new MainController();
        main.knowList.add(new Word("word", "meaning", "example"));
        assertEquals(main.knowList.size(), 1);

        // insert data into junit_words.txt
        main.saveUser();

        // clear knowList
        main.knowList.clear();
        assertEquals(main.knowList.size(), 0);

        // load data from junit_words.txt
        main.loadWords_know();
        assertEquals(main.knowList.size(), 1);

        // check if the data exist in knowList
        assertEquals(main.knowList.get(0).getWord(), "word");
    }

    @Test
    void loadWords() {
        MainController main = new MainController();

        // load all data from words.txt into the words list. It's 11
        main.loadWords();

        // It's 11
        assertEquals(main.words.size(), 11);

        // the first data is "alpha"
        assertEquals(main.words.get(0).getWord(), "alpha");
    }
}