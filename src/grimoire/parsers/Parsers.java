package grimoire.parsers;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.util.*;
import arc.util.serialization.*;
import mindustry.gen.*;
import mindustry.graphics.*;

public class Parsers{
    public static void load(){
        // Fallback for JSON sections with no type.
        PageParser.addParser("grimoire-text", (data) -> {
            return new Label(data.asString()){{
                setWrap(true);
            }};
        });

        PageParser.addParser("grimoire-header", (data) -> {
            return new Table(t -> {
                t.table(h -> {
                    if(data.has("icon")){
                        JsonValue icon = data.get("icon");
                        TextureRegion texture = Core.atlas.find(icon.getString("texture"));
                        h.image(texture).size(
                            icon.getFloat("width", texture.width * 8f),
                            icon.getFloat("height", texture.height * 8f)
                        ).padRight(15f).top().left();
                    }
                    h.labelWrap(data.getString("text"))
                        .left().growX()
                        .get().setFontScale(data.getFloat("scale", 1f));
                }).padBottom(15f).growX();
                t.row();
                t.image(Tex.whiteui)
                    .growX()
                    .color(data.has("separatorColor") ? Color.valueOf(Tmp.c1, data.getString("separatorColor")) : Pal.accent);
            });
        });
    }
}
