package org.durmiendo.minedit.core;

import mindustry.Vars;
import mindustry.gen.Icon;
import mindustry.mod.Mod;
import org.durmiendo.minedit.mods.CMod;


public class MCore extends Mod {
    public MCore() {}

    @Override
    public void init() {
        MVars.init();

        MVars.ui.build();
        Vars.ui.menufrag.addButton("Изменить", Icon.rightOpen, () -> {
            MVars.ui.choiceDialog.setT1();
            MVars.ui.choiceDialog.show();
        });
        CMod mod = new CMod("test");
        mod.initDisplayData(null);
        MVars.cc.mods.add(mod);
    }
}