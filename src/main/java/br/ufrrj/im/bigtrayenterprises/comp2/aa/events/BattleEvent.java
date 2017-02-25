package br.ufrrj.im.bigtrayenterprises.comp2.aa.events;

import br.ufrrj.im.bigtrayenterprises.comp2.aa.characters.AICharacter;
import br.ufrrj.im.bigtrayenterprises.comp2.aa.characters.Player;
import br.ufrrj.im.bigtrayenterprises.comp2.aa.choices.Choice;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by vitorhnn on 28/10/16.
 */
public class BattleEvent extends Event {
    public BattleEvent(String postBattleEvent, AICharacter enemy, Player player) {
        super(new ArrayList<>(), "");

        this.postBattleEvent = postBattleEvent;
        this.enemy = enemy;
        this.player = player;
    }

    @Override
    public String getDescription() {
        return String.format("Seu HP: %d, HP do inimigo: %d", player.getAttributes().health, enemy.getAttributes().health);
    }

    @Override
    public Collection<Choice> getChoices() {
        // this is kind of a hack, and I should be ashamed of it
        // but it is needed to load the player's usables when the event triggers, not when it's built
        ArrayList<Choice> retval = new ArrayList<>();

        int i = 0;
        /*for (Usable usable : player.getUsables()) {
            Choice bChoice = new BattleChoice(postBattleEvent, enemy, usable);
            bChoice.setNumber(i);

            retval.add(bChoice);

            i++;
        }*/

        this.addChoices(retval);

        return retval;
    }

    @Override
    public boolean isEndEvent() {
        return false;
    }

    @Override
    public void applyHistory(Player player) {
        // nothing!
    }

    public void setPlayer(Player p) {
        player = p;
    }

    public Player getPlayer() {
        return player;
    }

    private String postBattleEvent;
    private AICharacter enemy;
    private Player player;
}
