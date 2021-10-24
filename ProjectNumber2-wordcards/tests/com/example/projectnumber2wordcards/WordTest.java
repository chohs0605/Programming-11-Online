package com.example.projectnumber2wordcards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordTest {
    @Test
    void word() {
        Word word = new Word ("word", "meaning", "example");
        assertEquals(word.getWord(), "word");
        assertEquals(word.getExample(), "example");
        assertEquals(word.getMeaning(), "meaning");
    }

    @Test
    void setWord() {
        Word word = new Word ();
        word.setWord("word");
        assertEquals(word.getWord(), "word");
    }

    @Test
    void setMeaning() {
        Word word = new Word ();
        word.setMeaning("meaning");
        assertEquals(word.getMeaning(), "meaning");
    }

    @Test
    void setExample() {
        Word word = new Word ();
        word.setExample("example");
        assertEquals(word.getExample(), "example");
    }
}