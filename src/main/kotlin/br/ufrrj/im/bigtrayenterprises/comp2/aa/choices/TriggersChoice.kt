package br.ufrrj.im.bigtrayenterprises.comp2.aa.choices

import br.ufrrj.im.bigtrayenterprises.comp2.aa.characters.Player
import br.ufrrj.im.bigtrayenterprises.comp2.aa.triggers.Trigger

class TriggersChoice(description: String, val triggers: Collection<Trigger>, val nextEv: String?)
    : Choice(description) {
    override fun executeChoice(player: Player) {
        for (trigger in triggers) {
            trigger.apply(player)
        }
    }

    override fun getNextEvent(): String? {
        return nextEv
    }
}