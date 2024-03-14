package org.durmiendo.minedit.ui;

import arc.struct.Seq;
import org.durmiendo.minedit.ui.dialogs.*;

import java.awt.*;

public class MUI {

    public ChoiceDialog choiceDialog;
    public SeqEditorDialog seqEditorDialog;
    public ContentEditorDialog contentEditorDialog;
    public ClassGetterDialog classGetterDialog;
    public ModEditorDialog modEditorDialog;


    public void build() {
        choiceDialog = new ChoiceDialog("Изменение");
        seqEditorDialog = new SeqEditorDialog("Редактор последовательностей");
        contentEditorDialog = new ContentEditorDialog("Редактор контента");
        classGetterDialog = new ClassGetterDialog();
        modEditorDialog = new ModEditorDialog("Редактор мода");
    }
}
