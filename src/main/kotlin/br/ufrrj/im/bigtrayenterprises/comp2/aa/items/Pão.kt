package br.ufrrj.im.bigtrayenterprises.comp2.aa.items

import br.ufrrj.im.bigtrayenterprises.comp2.aa.AttributeBuilder
import br.ufrrj.im.bigtrayenterprises.comp2.aa.Usable
import br.ufrrj.im.bigtrayenterprises.comp2.aa.characters.Character

class Pão :
        Item("Um pedaço de pão", AttributeBuilder().createAttributes(), 1, ItemType.OTHER),
        Usable {
    override fun getDescription(): String {
        return "Atirar o pão no oponente"
    }

    override fun use(caster: Character?, target: Character?) {
        target?.changeHealth(-5)
    }
}
