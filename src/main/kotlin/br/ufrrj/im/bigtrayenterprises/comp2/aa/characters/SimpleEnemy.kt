package br.ufrrj.im.bigtrayenterprises.comp2.aa.characters

import br.ufrrj.im.bigtrayenterprises.comp2.aa.AttributeBuilder
import br.ufrrj.im.bigtrayenterprises.comp2.aa.Usable
import br.ufrrj.im.bigtrayenterprises.comp2.aa.skills.AutoAttack

class SimpleEnemy : AICharacter(AttributeBuilder().createAttributes()) {
    private val attack = AutoAttack()

    override fun chooseUsable(enemy: Character?): Usable {
        return attack
    }
}