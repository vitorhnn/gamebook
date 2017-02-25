package br.ufrrj.im.bigtrayenterprises.comp2.aa.triggers

import br.ufrrj.im.bigtrayenterprises.comp2.aa.characters.Player
import br.ufrrj.im.bigtrayenterprises.comp2.aa.items.Item


class AddItemTrigger(val item: Item) : Trigger {
    override fun apply(player: Player) {
        player.addItem(item)
        player.equipItem(item)
    }
}

