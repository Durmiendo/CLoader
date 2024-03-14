package org.durmiendo.minedit.ui.dialogs;

import arc.Core;
import arc.func.Boolp;
import arc.func.Prov;
import arc.scene.event.Touchable;
import arc.scene.ui.Button;
import arc.scene.ui.ScrollPane;
import arc.scene.ui.TextField;
import arc.scene.ui.layout.Cell;
import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import arc.util.Log;
import mindustry.Vars;
import mindustry.gen.Icon;
import mindustry.ui.dialogs.BaseDialog;
import org.durmiendo.minedit.core.MVars;
import org.durmiendo.minedit.mods.CMod;

public class ChoiceDialog extends BaseDialog {
    Table t1 = new Table(){{
    }};

    public void setLi(Table p) {
        float w = p.getWidth();
        float s = 5f;
        Log.info("ms: " + MVars.cc.mods.size);
        for (CMod mod : MVars.cc.mods) {
            float m = mod.element.getWidth();
//            if (s + m > w) {
//                p.row();
//                s = 5f;
//            }
            s += m;
            mod.element.clicked(() -> {
                Log.info("clicked");
            });
            p.add(mod.element);
        }
    }
    Table t2 = new Table(){{
        label(() -> "2");
    }};
    Table t3 = new Table(){{
        label(() -> "3");
    }};

    Cell<ScrollPane> sp;
    public void setT1() {
        t1 = new Table(){{
            t1.table(t -> {
                t.label(() -> "Моды");
                t.row();
                t.pane(p -> {
                    setLi(p);
                }).minSize(800, Core.graphics.getHeight()).maxSize(800, Core.graphics.getHeight());
            });
        }};
    }

    public ChoiceDialog(String title) {
        super(title);
        cont.left().table(t -> {
            t.row();
            t.buttonRow("Моды", Icon.upload, () -> {
                sp.get().setScrollY(0f);
            }).minSize(130, 20);
            t.row();
            t.buttonRow("Ресурсы", Icon.image, () -> {
                sp.get().setScrollY(Core.graphics.getHeight());
            }).minSize(130, 20);
            t.row();
            t.buttonRow("Настройки", Icon.settings, () -> {
                sp.get().setScrollY(Core.graphics.getHeight()*2f);
            }).minSize(130, 20);
            t.row();
        });

        cont.left().table(t -> {
            sp = t.pane(p -> {
                p.add(t1).minSize(800, Core.graphics.getHeight());
                p.row();
                p.add(t2).minSize(800, Core.graphics.getHeight());
                p.row();
                p.add(t3).minSize(800, Core.graphics.getHeight());
                p.row();

            }).minWidth(Core.graphics.getWidth()-135f).scrollX(false).scrollY(true).disabled(true);
        });
        addCloseButton();

    }
}
