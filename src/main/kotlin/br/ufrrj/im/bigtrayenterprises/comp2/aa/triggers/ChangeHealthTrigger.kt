package br.ufrrj.im.bigtrayenterprises.comp2.aa.triggers

import br.ufrrj.im.bigtrayenterprises.comp2.aa.characters.Player

class ChangeHealthTrigger(val delta: Int) : Trigger {
    override fun apply(player: Player) {
        player.changeHealth(delta)
    }
}