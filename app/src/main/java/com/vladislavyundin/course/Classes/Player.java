package com.vladislavyundin.course.Classes;

/**
 * Created by vladislavyundin on 01.03.17.
 */
public class Player {

    private String name;

    public Player(String name) {
        this.name = name;
    }

    public CharSequence getName() {
        return (CharSequence) name;
    }
}