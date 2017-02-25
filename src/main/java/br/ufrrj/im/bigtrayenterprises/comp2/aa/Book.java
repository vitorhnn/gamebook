package br.ufrrj.im.bigtrayenterprises.comp2.aa;

import br.ufrrj.im.bigtrayenterprises.comp2.aa.characters.Player;
import br.ufrrj.im.bigtrayenterprises.comp2.aa.choices.Choice;
import br.ufrrj.im.bigtrayenterprises.comp2.aa.events.BlankEvent;
import br.ufrrj.im.bigtrayenterprises.comp2.aa.events.Event;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by filipebraida on 31/05/16.
 */
public class Book {
    private Event currentEvent;
    private String initialEvent;
    private String description;
    private Player player;

    public Book(String description, String initialEvent, Player player) {
        this.initialEvent = initialEvent;
        this.description = description;
        this.player = player;

        this.resetHistory();

//        this.currentEvent.applyHistory(player);
    }

    public void resetHistory() {
        //  this.currentEvent = this.initialEvent;
    }

    public String showHistory() {
        return this.currentEvent.getDescription();
    }

    public boolean isTheEnd() {
        return this.currentEvent.isEndEvent();
    }

    public String showHistoryBook() {
        return this.description;
    }

    public boolean nextEvent(int number) {
        Choice choice = this.selectChoice(number);

        if (choice != null) {
            choice.executeChoice(player);

            if (player.isAlive()) {
                this.currentEvent = JsonManager.INSTANCE.deserializeEvent(choice.getNextEvent(), player);
                this.currentEvent.applyHistory(player);
            } else {
                Event gameOver = new BlankEvent(new ArrayList<Choice>(), "Game Over");
                this.currentEvent = gameOver;
            }

            return true;
        }

        return false;
    }

    public Choice selectChoice(int number) {
        return this.currentEvent.findChoice(number);
    }

    public Collection<Choice> nextEvents() {
        return this.currentEvent.getChoices();
    }

}
