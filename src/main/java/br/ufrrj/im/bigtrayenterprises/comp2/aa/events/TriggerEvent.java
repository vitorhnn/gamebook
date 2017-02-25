package br.ufrrj.im.bigtrayenterprises.comp2.aa.events;

import br.ufrrj.im.bigtrayenterprises.comp2.aa.characters.Player;
import br.ufrrj.im.bigtrayenterprises.comp2.aa.choices.Choice;

import java.util.Collection;

/**
 * Created by vitorhnn on 18/02/17.
 */
public class TriggerEvent extends Event {
    public TriggerEvent(Collection<Choice> choices, String description, boolean b) {
        super(choices, description);
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

    private boolean b;
}
