package grimoire;

import arc.*;
import grimoire.pages.*;
import grimoire.parsers.*;
import mindustry.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;

public class Grimoire extends Mod{
    public Grimoire(){
        Parsers.load();

        // TODO: make a class to contain multiple pages, like a book.
        Events.on(ClientLoadEvent.class, e-> {
            new Book(Vars.tree.get("assets/pages")).show();
        });
    }
}
