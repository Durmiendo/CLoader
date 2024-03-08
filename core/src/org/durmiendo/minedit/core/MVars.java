package org.durmiendo.minedit.core;

import org.durmiendo.minedit.ui.MUI;

public class MVars {
    public static MUI ui;
    public static ContentController cc;

    public static void init() {
        ui = new MUI();
        cc = new ContentController();
    }
}
