package org.durmiendo.minedit.mods;

import arc.Core;
import arc.files.Fi;
import arc.graphics.Texture;
import arc.graphics.TextureData;
import arc.graphics.g2d.TextureRegion;
import arc.graphics.gl.FileTextureData;
import arc.graphics.gl.GLOnlyTextureData;
import arc.graphics.gl.PixmapTextureData;
import arc.scene.ui.Dialog;
import arc.scene.ui.Image;
import arc.scene.ui.Label;
import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import mindustry.Vars;
import mindustry.annotations.util.Svar;
import mindustry.ctype.Content;
import mindustry.gen.Icon;
import org.durmiendo.minedit.core.MVars;

public class CMod {
    public String displayName;
    public String name;
    public String prefix;
    public String description;
    public String author;
    public String version = "any";
    public String repo;
    public String subtitle;

    public TextureRegion icon;

    public boolean isLocal;
    public boolean isSendeble = false;
    public boolean isSaving = false;
    public boolean isMultiplayer = true;
    public boolean forcedLoading = false;

    public Table element;

    public Seq<CContent> content;

    public Seq<TextureRegion> textures;

    public Seq<String> dependencies;

    public CMod(String name) {
        this.name = name;
        content = new Seq<>();
        textures = new Seq<>();
        dependencies = new Seq<>();
    }
    public void initDisplayData(Fi icon) {
        if (icon != null) {
            this.icon = new TextureRegion(new Texture(icon));
        } else {
            this.icon = Core.atlas.find("ohno");
        }
        element = new Table();

        if (displayName == null) displayName = name;

        Dialog.DialogStyle style = (Dialog.DialogStyle) Core.scene.getStyle(Dialog.DialogStyle.class);
        Label title = new Label(displayName, new Label.LabelStyle(style.titleFont, style.titleFontColor));
        title.setEllipsis(true);

        Table titleTable = new Table();
        titleTable.add(title).expandX().fillX().minWidth(0.0F);

        Image iconImage = new Image(Core.atlas.find("minedit-icon"));

        element.add(titleTable);
        element.row();
        element.add(iconImage);
        element.clicked(() -> {
            MVars.ui.modEditorDialog.edit();
        });
    }

    public void loadContent(Content content, Seq<TextureRegion> textures) {
        CContent ccont = new CContent(this, content, textures);
        this.content.add(ccont);
        if (textures.size > 0) this.textures.add(textures);
    }
}