package br.ufrrj.im.bigtrayenterprises.comp2.aa.choices;

import br.ufrrj.im.bigtrayenterprises.comp2.aa.characters.Player;

/**
 * Created by filipebraida on 31/05/16.
 */
public abstract class Choice {
    public Choice(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    public abstract String getNextEvent();

    public abstract void executeChoice(Player player);

    private String description;
    private int number;
    private String type;
}
