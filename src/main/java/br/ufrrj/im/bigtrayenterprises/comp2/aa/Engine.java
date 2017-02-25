package br.ufrrj.im.bigtrayenterprises.comp2.aa;

import br.ufrrj.im.bigtrayenterprises.comp2.aa.characters.AICharacter;
import br.ufrrj.im.bigtrayenterprises.comp2.aa.characters.Character;
import br.ufrrj.im.bigtrayenterprises.comp2.aa.characters.Player;
import br.ufrrj.im.bigtrayenterprises.comp2.aa.choices.Choice;
import br.ufrrj.im.bigtrayenterprises.comp2.aa.events.BlankEvent;
import br.ufrrj.im.bigtrayenterprises.comp2.aa.events.Event;
import br.ufrrj.im.bigtrayenterprises.comp2.aa.items.Item;
import br.ufrrj.im.bigtrayenterprises.comp2.aa.items.ItemType;
import br.ufrrj.im.bigtrayenterprises.comp2.aa.skills.AutoAttack;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by filipebraida on 31/05/16.
 */
public class Engine {
    public static Random random = new Random();

    public static IOSource source;

    public void run() {
        Book book = Engine.testBook();

        source.printString(book.showHistoryBook());

        Scanner in = new Scanner(System.in);

        do {
            source.printString(book.showHistory());

            for (Choice choice : book.nextEvents()) {
                source.addChoice(choice);
            }

            int i;
            do {
                i = source.getChoice();
            } while (!book.nextEvent(i));
        } while (!book.isTheEnd());

        source.printString(book.showHistory());
    }


    public static Book testBook() {
        Attributes attr = new AttributeBuilder()
                .setAgility(2)
                .setStrength(2)
                .setResistance(2)
                .setArmor(2)
                .setFirepower(2)
                .createAttributes();

        Player player = new Player(attr);
        Item branch =
                new Item(
                        "Ironwood Branch",
                        new AttributeBuilder()
                                .setAgility(1)
                                .setStrength(1)
                                .setResistance(1)
                                .setArmor(1)
                                .setFirepower(1)
                                .createAttributes(),
                        10,
                        ItemType.AMULET
                );

        player.addItem(branch);
        player.equipItem(branch);

        Event eventoFinal = new BlankEvent(new ArrayList<>(), "IS DEAD");

        ArrayList<Choice> start = new ArrayList<>();

        Attributes monstroAttr = new AttributeBuilder()
                .setAgility(2)
                .setStrength(1)
                .setResistance(3)
                .setArmor(2)
                .setFirepower(0)
                .createAttributes();

        AICharacter monstro = new AICharacter(monstroAttr) {
            AutoAttack ataque = new AutoAttack();
            @Override
            public Usable chooseUsable(Character enemy) {
                return ataque;
            }
        };
        //BattleEvent battle = new BattleEvent(eventoFinal, monstro, player);
        //Choice fite = new BlankChoice("bater", battle);
        //start.add(fite);

        Event inicial = new BlankEvent(start, "Você encontrou um bodybuilder!");

        return new Book("aaa", "aaa", player);
    }
}
