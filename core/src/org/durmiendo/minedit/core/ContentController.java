package org.durmiendo.minedit.core;

import arc.struct.Seq;
import arc.util.Log;
import mindustry.Vars;
import mindustry.content.Items;
import mindustry.ctype.Content;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import org.durmiendo.minedit.mods.CMod;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import static mindustry.type.ItemStack.with;

public class ContentController {
    public Field contentMap;
    public Seq<Content>[] newContent;
    public Seq<CMod> mods;

    public void addCont(int ct, Content c) {
        //newContent[ct].add(c);
        try {
            contentMap.setAccessible(true);
            ((Seq<Content>[]) contentMap.get(Vars.content))[ct].add(c);
            contentMap.setAccessible(false);
        } catch (IllegalAccessException e) {
            Log.warn("add content error: " + e);
        }
    }

    public void removeCont(int ct, Content c) {
        newContent[ct].add(c);
        try {
            contentMap.setAccessible(true);
            ((Seq<Content>[]) contentMap.get(Vars.content))[ct].remove(c);
            contentMap.setAccessible(false);
        } catch (IllegalAccessException e) {
            Log.warn("add remove error: " + e);
        }
    }

    public ContentController() {
        mods = new Seq<>();
        try {
            contentMap = Vars.content.getClass().getDeclaredField("contentMap");
        } catch (NoSuchFieldException ex) {
            return;
        }
        newContent = new Seq[16];
        for (int i = 0; i < newContent.length; i++) {
            newContent[i] = new Seq<>();
        }
    }

    public void loadCotent(Content c){
        if (c instanceof Block){
            loadBlock((Block) c);
        }
    }

    private void loadBlock(Block b){
        if (Arrays.equals(b.requirements, ItemStack.empty)) {
            b.requirements(b.category, with(Items.sporePod, 69));
        }
        b.load();
        b.loadIcon();
        Log.info("loadBlock " + b);
    }


    public Content generate(Class clazz, String name, String modName) {
        try {
            Constructor constructor = clazz.getDeclaredConstructor(String.class);
            Content b = (Content) constructor.newInstance(modName + "-" + name);
            b.init();
            return b;
        } catch ( NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            Log.warn("generate content " + clazz.getSimpleName() + " " + modName + "-" + name + " error: " + e);
        }
        return null;
    }
}
