package br.ufrrj.im.bigtrayenterprises.comp2.aa.events;

import br.ufrrj.im.bigtrayenterprises.comp2.aa.characters.Player;
import br.ufrrj.im.bigtrayenterprises.comp2.aa.choices.Choice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by filipebraida on 31/05/16.
 */
public abstract class Event {
    public Event(Collection<Choice> choices, String description) {
        this.choices = new ArrayList<>();
        this.description = description;

        addChoices(choices);
    }

    public String getDescription() {
        return description;
    }

    public boolean isEndEvent() {
        return choices.isEmpty();
    }

    protected void addChoices(Collection<Choice> choices) {
        int i = this.choices.size();

        for (Choice choice : choices) {
            choice.setNumber(i);
            i++;
        }

        this.choices.addAll(choices);
    }

    public Collection<Choice> getChoices() {
        return Collections.unmodifiableCollection(this.choices);
    }

    public Choice findChoice(int number) {
        for (Choice choice : this.choices) {
            if (choice.getNumber() == number) return choice;
        }

        return null;
    }

    public abstract void applyHistory(Player player);

    private String description;
    private Collection<Choice> choices;
}
