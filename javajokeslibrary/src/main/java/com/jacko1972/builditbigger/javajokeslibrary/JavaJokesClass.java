package com.jacko1972.builditbigger.javajokeslibrary;

import java.util.Random;

public class JavaJokesClass {

    private static final String[] someJokes = {
            "A pregnant fragment walks into a bar. The bartender says, 'Whoa! Whoa! We don't support nested fragments here!'",
            "An Android app walks into a bar. Bartender asks, 'Can I get you a drink?' The app looks disappointed and says, 'That wasn't my intent.'",
            "How many programmers does it take to change a light bulb? None, that's a hardware problem!",
            "What do computers and air conditioners have in common? They both become useless when you open windows!",
            "Why do Java programmers have to wear glasses? Because they don't C#"
    };

    public String getJoke() {
        int idx = new Random().nextInt(someJokes.length);
        return someJokes[idx];
    }
}
