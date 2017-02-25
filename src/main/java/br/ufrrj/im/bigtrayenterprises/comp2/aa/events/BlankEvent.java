package br.ufrrj.im.bigtrayenterprises.comp2.aa.events;

import br.ufrrj.im.bigtrayenterprises.comp2.aa.characters.Player;
import br.ufrrj.im.bigtrayenterprises.comp2.aa.choices.Choice;

import java.util.Collection;

/**
 * Created by filipebraida on 31/05/16.
 */
public class BlankEvent extends Event {
    public BlankEvent(Collection<Choice> choices, String description) {
        super(choices, description);
    }

    @Override
    public void applyHistory(Player player) {
    }
}
