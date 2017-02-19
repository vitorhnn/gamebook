package br.ufrrj.im.bigtrayenterprises.comp2.aa.Events;

import br.ufrrj.im.bigtrayenterprises.comp2.aa.Characters.Player;
import br.ufrrj.im.bigtrayenterprises.comp2.aa.Choices.Choice;
import br.ufrrj.im.bigtrayenterprises.comp2.aa.Engine;

import java.util.Collection;

/**
 * Created by vitorhnn on 18/02/17.
 */
public class DamageEvent extends Event {
    public DamageEvent(Collection<Choice> choices, String description, int damage) {
        super(choices);

        this.description = description;
        this.damage = damage;
    }

    @Override
    public void applyHistory(Player player) {
        player.changeHealth(-damage);
        Engine.source.printString(String.format("Você perdeu %d de vida!", damage));
    }

    @Override
    public String getDescription() {
        return description;
    }

    private int damage;
    private String description;
}

