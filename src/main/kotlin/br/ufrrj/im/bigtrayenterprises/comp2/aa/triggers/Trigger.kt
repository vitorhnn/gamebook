package br.ufrrj.im.bigtrayenterprises.comp2.aa.triggers

import br.ufrrj.im.bigtrayenterprises.comp2.aa.characters.Player

/**
 * Created by vitorhnn on 20/02/17.
 */
interface Trigger {
    fun apply(player: Player)
}