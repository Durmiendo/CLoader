package org.durmiendo.minedit.mods;

import arc.graphics.Texture;
import arc.graphics.g2d.TextureRegion;
import arc.struct.Seq;
import mindustry.ctype.Content;

public class CContent {
    public Content content;
    public Seq<TextureRegion> textures;
    public CMod mod;
    public CContent(CMod mod, Content content, Seq<TextureRegion> textures) {
        this.mod = mod;
        this.content = content;
        this.textures = textures;
    }
}
