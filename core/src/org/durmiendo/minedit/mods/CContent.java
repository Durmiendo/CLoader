package org.durmiendo.minedit.mods;

import arc.graphics.Texture;
import arc.struct.Seq;
import mindustry.ctype.Content;

public class CContent {
    public Content content;
    public Seq<Texture> textures;
    public CMod mod;
    public CContent(CMod mod, Content content, Seq<Texture> textures) {
        this.mod = mod;
        this.content = content;
        this.textures = textures;
    }
}
