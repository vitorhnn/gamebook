package br.ufrrj.im.bigtrayenterprises.comp2.aa.choices;

import br.ufrrj.im.bigtrayenterprises.comp2.aa.Usable;
import br.ufrrj.im.bigtrayenterprises.comp2.aa.characters.AICharacter;
import br.ufrrj.im.bigtrayenterprises.comp2.aa.characters.Player;

/**
 * Created by filipebraida on 31/05/16.
 */
public class BattleChoice extends Choice {
    public BattleChoice(String postBattleEvent, AICharacter enemy, Usable usable) {
        super(usable.getDescription());

        this.enemy = enemy;
        this.postBattleEvent = postBattleEvent;
        this.usable = usable;
    }

    @Override
    public String getNextEvent() {
        return nextEvent;
    }

    @Override
    public void executeChoice(Player player) {
        usable.use(player, enemy);
        enemy.chooseUsable(player).use(enemy, player);

        if (enemy.isAlive()) {
            nextEvent = "BattleEvent";
        } else {
            nextEvent = postBattleEvent;
        }
    }

    private AICharacter enemy;
    private String postBattleEvent;
    private String nextEvent;
    private Usable usable;
}
