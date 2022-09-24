package grimoire;

import arc.*;
import arc.scene.*;
import grimoire.pages.*;
import grimoire.parsers.*;
import mindustry.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.*;
import mindustry.ui.dialogs.*;

public class Grimoire extends Mod{
    public Grimoire(){
        Parsers.load();

        // TODO: make a class to contain multiple pages, like a book.
        Events.on(ClientLoadEvent.class, e-> {
            Page page1, page2, page3;

            page1 = new Page(Vars.tree.get("pages/test1.txt"));
            page2 = new Page(Vars.tree.get("pages/test2.txt"));
            page3 = new Page(Vars.tree.get("pages/test3.txt"));

            page1.setNeighbors(null, page2);
            page2.setNeighbors(page1, page3);
            page3.setNeighbors(page2, null);

            page2.show();
        });
    }
}
