package grimoire;

import arc.*;
import arc.files.ZipFi;
import grimoire.pages.*;
import grimoire.parsers.*;
import mindustry.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;

public class Grimoire extends Mod{
    public Grimoire(){
        Parsers.load();

        Events.on(ClientLoadEvent.class, e -> {
            new Book(new ZipFi(Vars.mods.getMod("grimoire").file).child("pages")).show();
        });
    }
}
