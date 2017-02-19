package br.ufrrj.im.bigtrayenterprises.comp2.aa.Events;

import br.ufrrj.im.bigtrayenterprises.comp2.aa.Characters.Player;
import br.ufrrj.im.bigtrayenterprises.comp2.aa.Choices.Choice;

import java.util.Collection;

/**
 * Created by vitorhnn on 18/02/17.
 */
public class TriggerEvent extends Event {
    public TriggerEvent(Collection<Choice> choices, String description, boolean b) {
        super(choices);

        this.description = description;

    }

    @Override
    public void applyHistory(Player player) {
        // TODO (victor): convert to player's "triggers" hashmap
        if (b) {
            player.setGarilho1(true);
        } else {
            player.setGarilho2(true);
        }
    }

    @Override
    public String getDescription() {
        return description;
    }

    private String description;
    private boolean b;
}
