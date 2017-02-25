package br.ufrrj.im.bigtrayenterprises.comp2.aa.triggers

import br.ufrrj.im.bigtrayenterprises.comp2.aa.characters.Player

class ModifierTrigger(val modifierName: String, val value: Boolean) : Trigger {
    override fun apply(player: Player) {
        player.setTrigger(modifierName, value)
    }
}

