package br.ufrrj.im.bigtrayenterprises.comp2.aa.characters;

import br.ufrrj.im.bigtrayenterprises.comp2.aa.Attributes;
import br.ufrrj.im.bigtrayenterprises.comp2.aa.Engine;
import br.ufrrj.im.bigtrayenterprises.comp2.aa.Usable;
import br.ufrrj.im.bigtrayenterprises.comp2.aa.items.Item;
import br.ufrrj.im.bigtrayenterprises.comp2.aa.skills.AutoAttack;
import br.ufrrj.im.bigtrayenterprises.comp2.aa.skills.Skill;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by filipebraida on 31/05/16.
 */
public class Player extends Character {
    public Player(Attributes attributes) {
        super(attributes);

        this.skills = new ArrayList<>();
        this.inventory = new ArrayList<>();
        this.triggers = new HashMap<>();

        addSkill(new AutoAttack());
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public void addItem(Item item) {
        Attributes attr = new Attributes(getAttributes());

        int totalWeight = 0;
        for (Item i : inventory) {
            totalWeight += i.getWeight();
        }

        if (attr.getCarryCapacity() < item.getWeight() + totalWeight) {
            Engine.source.printString("\n\nInventário muito cheio!");
        } else {
            Engine.source.printString("\n\nItem adicionado ao inventário!");
            inventory.add(item);
        }
    }

    public void setTrigger(String name, boolean value) {
        this.triggers.put(name, value);
    }

    public void equipItem(Item item) {
        switch (item.getType()) {
            case AMULET:
                currentAmulet = item;
                break;
            case ARMOR:
                currentArmor = item;
                break;
            case WEAPON:
                currentArmor = item;
                break;
            default:
                throw new IllegalArgumentException("Attempted to equip an unequippable item");
        }
        Engine.source.printString("\n\nEquipou " + item.getName());
    }

    public Collection<Usable> getUsables() {
        ArrayList<Usable> retval = new ArrayList<>();

        for (Item i : inventory) {
            if (i instanceof Usable) {
                retval.add((Usable) i);
            }
        }

        retval.addAll(skills);

        return retval;
    }

    public boolean getGarilho1() {
        return gatilho1;
    }

    public boolean getGarilho2() {
        return gatilho2;
    }

    public void setGarilho1(boolean booleano) {
        this.gatilho1 = booleano;
    }

    public void setGarilho2(boolean booleano) {
        this.gatilho2 = booleano;
    }

    private Collection<Skill> skills;
    private Collection<Item> inventory;

    private HashMap<String, Boolean> triggers;
    private boolean gatilho1 = false;
    private boolean gatilho2 = false;
}
