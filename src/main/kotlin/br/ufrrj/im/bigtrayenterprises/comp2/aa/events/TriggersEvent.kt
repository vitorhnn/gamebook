package br.ufrrj.im.bigtrayenterprises.comp2.aa.events

import br.ufrrj.im.bigtrayenterprises.comp2.aa.characters.Player
import br.ufrrj.im.bigtrayenterprises.comp2.aa.choices.Choice

import br.ufrrj.im.bigtrayenterprises.comp2.aa.triggers.Trigger

class TriggersEvent(
        choices: MutableCollection<Choice>,
        description: String,
        val triggers: Collection<Trigger>
) : Event(choices, description) {
    override fun applyHistory(player: Player) {
        for (trigger in triggers) {
            trigger.apply(player)
        }
    }
}