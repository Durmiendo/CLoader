package org.durmiendo.minedit.ui.dialogs;

import arc.Core;
import arc.scene.ui.TextField;
import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import mindustry.ui.dialogs.BaseDialog;
import org.durmiendo.minedit.R;

public class ClassGetterDialog extends BaseDialog {

    public ClassGetterDialog() {
        super("Выберите класс");
        cont.table(t -> {
            searchClass = new TextField();
            searchPackage = new TextField();
            t.label(() -> "Класс:  ");
            t.add(searchClass).left();
            t.label(() -> "Пакет:  ");
            t.add(searchPackage).left();
        });
        cont.pane(p -> {
            p.table(t -> {
                packages = new Table();
                classes = new Table();
                packages.setSize(Core.graphics.getWidth()/2f, packages.getHeight());
                classes.setSize(Core.graphics.getWidth()/2f, classes.getHeight());
                t.add(packages);
                t.add(classes);
            });
        });
        searchClass = new TextField();
        searchPackage = new TextField();
        row();
    }

    TextField searchPackage;
    TextField searchClass;
    Table packages;
    Table classes;
    Seq<Class<?>> classesSeq;
    Seq<Package> packagesSeq;
    Class<?> filter;

    public void start(Class<?> filter, Class returnType) {
        this.filter = filter;
//        classesSeq = R.getClasses(searchPackage.getText(), filter);
//        packagesSeq = R.getPackages(searchPackage.getText());
    }
}
