package org.durmiendo.minedit.mods;

import arc.graphics.Texture;
import arc.graphics.g2d.TextureRegion;
import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import mindustry.ctype.Content;

public class CMod {
    public String displayName;
    public String name;
    public String prefix;
    public String description;
    public String author;
    public String version = "any";
    public String repo;
    public String subtitle;

    public Texture icon;

    public boolean isLocal;
    public boolean isSendeble = false;
    public boolean isSaving = false;
    public boolean isMultiplayer = true;
    public boolean forcedLoading = false;

    public Table element;

    public Seq<CContent> content;

    public Seq<Texture> textures;

    public Seq<String> dependencies;

    public CMod(String name) {
        this.name = name;
    }

    public void loadContent(Content content, Seq<Texture> textures) {
        CContent ccont = new CContent(this, content, textures);
        this.content.add(ccont);
        if (textures.size > 0) this.textures.add(textures);
    }
}