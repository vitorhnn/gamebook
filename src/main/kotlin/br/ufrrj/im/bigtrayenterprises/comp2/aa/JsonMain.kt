package br.ufrrj.im.bigtrayenterprises.comp2.aa

import br.ufrrj.im.bigtrayenterprises.comp2.aa.characters.Player

fun main(args: Array<String>) {
    val ev = JsonManager.deserializeEvent("Inicial.json", Player(AttributeBuilder().createAttributes()))

    print(ev.description)
}