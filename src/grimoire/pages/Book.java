package grimoire.pages;

import arc.*;
import arc.files.*;
import arc.func.*;
import arc.graphics.g2d.*;
import arc.struct.*;
import arc.util.*;
import arc.util.serialization.*;

import java.util.*;

public class Book {
    private static final JsonReader jsonReader = new JsonReader();

    public String name;
    public TextureRegion icon;

    protected Seq<Page> pages = new Seq<>();

    @Nullable
    protected Boolp unlocked;
    public boolean alwaysUnlocked = true;

    public Book(Fi directory){
        Fi data = directory.child("book.json");
        if(data.exists()){
            try{
                JsonValue json = jsonReader.parse(data.readString());

                name = json.getString("name");
                alwaysUnlocked = json.getBoolean("alwaysUnlocked", true);

                if(json.has("icon")){
                    icon = Core.atlas.find(json.getString("icon"), "error");
                }

                // TODO: Research parsing
            }catch(Exception ignored){

                Log.err(ignored);
            }

            for(Fi page : directory.seq().filter(f -> !f.isDirectory() && !f.name().equals("book.json"))){
                Page p = new Page(page);

                if(pages.size - 1 >= 0){
                    Page previous = pages.get(pages.size - 1);
                    previous.nextPage = p;
                    p.previousPage = previous;
                }

                pages.add(p);

                p.index = pages.size;
                p.parent = this;
            }
        }
    }

    public void show(){
        pages.get(0).show();
    }
}
