package org.durmiendo.minedit.ui;

import arc.struct.Seq;
import org.durmiendo.minedit.ui.dialogs.ChoiceDialog;
import org.durmiendo.minedit.ui.dialogs.ClassGetterDialog;
import org.durmiendo.minedit.ui.dialogs.ContentEditorDialog;
import org.durmiendo.minedit.ui.dialogs.SeqEditorDialog;

import java.awt.*;

public class MUI {

    public ChoiceDialog choiceDialog;
    public SeqEditorDialog seqEditorDialog;
    public ContentEditorDialog contentEditorDialog;
    public ClassGetterDialog classGetterDialog;


    public void build() {
        choiceDialog = new ChoiceDialog("Изменение");
        seqEditorDialog = new SeqEditorDialog("Редактор последовательностей");
        contentEditorDialog = new ContentEditorDialog("Редактор контента");
        classGetterDialog = new ClassGetterDialog();
    }
}
