package br.ufrrj.im.bigtrayenterprises.comp2.aa.events;

import br.ufrrj.im.bigtrayenterprises.comp2.aa.Engine;
import br.ufrrj.im.bigtrayenterprises.comp2.aa.characters.Player;
import br.ufrrj.im.bigtrayenterprises.comp2.aa.choices.Choice;

import java.util.Collection;

/**
 * Created by vitorhnn on 18/02/17.
 */
public class DamageEvent extends Event {
    public DamageEvent(Collection<Choice> choices, String description, int damage) {
        super(choices, description);

        this.damage = damage;
    }

    @Override
    public void applyHistory(Player player) {
        player.changeHealth(-damage);
        Engine.source.printString(String.format("Você perdeu %d de vida!", damage));
    }

    private int damage;
}

