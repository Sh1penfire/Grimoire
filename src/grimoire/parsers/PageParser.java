package grimoire.parsers;

import arc.files.*;
import arc.scene.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.*;
import arc.util.serialization.*;
import mindustry.graphics.*;
import mindustry.ui.*;

public class PageParser{
    private static final JsonReader jsonReader = new JsonReader();
    protected static ObjectMap<String, SectionParser> parsers = new ObjectMap<>();

    public static Element[] parse(Fi file){
        return parse(file.readString());
    }

    public static Element[] parse(String data){
        // Evil regex because using "\\n\\n" does not work.
        // Reused the regex from Esoterum's manual parser, i don't know what this does anymore.
        String[] sections = data.split("\\r?\\n\\r?\\n");
        Element[] results = new Element[sections.length];

        for(String key : parsers.keys().toSeq()){
            Log.info(key);
        }

        for(int i = 0; i < sections.length; i++){
            String section = sections[i].trim();

            if(section.startsWith("${") && section.endsWith("}")){

                JsonValue json = jsonReader.parse(section.substring(1));

                try{
                    results[i] = getParser(json.getString("type")).parse(json);
                }catch(Exception e){
                    results[i] = new Table(t -> {
                        t.left();
                        t.setBackground(Styles.grayPanel);
                        t.label(() -> "Error parsing element").color(Pal.remove).growX().left().get().setAlignment(Align.left);
                        t.row();
                        t.labelWrap(() -> "> " + e.getMessage()).left().growX().get().setAlignment(Align.left);
                    });
                }
            }else{
                results[i] = new Label(section){{
                    setWrap(true);
                }};
            }
        }

        return results;
    }

    public static SectionParser getParser(String name){
        return parsers.containsKey(name) ? parsers.get(name) : parsers.get("grimoire-text");
    }

    // I can make this automatically prefix the name with the source mod name
    // but the game doesn't set currentMod for hidden mods.
    public static void addParser(String name, SectionParser parser){
        parsers.put(name, parser);
    }

    public interface SectionParser{
        Element parse(JsonValue data) throws Exception;
    }

}
