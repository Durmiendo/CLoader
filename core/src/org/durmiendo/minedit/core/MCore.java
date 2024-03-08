package org.durmiendo.minedit.core;

import mindustry.Vars;
import mindustry.gen.Icon;
import mindustry.mod.Mod;


public class MCore extends Mod {
    public MCore() {}

    @Override
    public void init() {
        MVars.init();
        MVars.ui.build();
        Vars.ui.menufrag.addButton("Изменить", Icon.rightOpen, () -> {
            MVars.ui.choiceDialog.show();
        });
    }
}