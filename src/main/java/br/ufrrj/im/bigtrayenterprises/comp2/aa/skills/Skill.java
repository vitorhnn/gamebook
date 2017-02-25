package br.ufrrj.im.bigtrayenterprises.comp2.aa.skills;

import br.ufrrj.im.bigtrayenterprises.comp2.aa.Usable;

/**
 * Created by vitorhnn on 31/01/17.
 */
public abstract class Skill implements Usable {
    @Override
    public String getDescription() {
        return "Skill genérica";
    }
}
