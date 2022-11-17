package grimoire;

import arc.*;
import arc.files.ZipFi;
import grimoire.pages.*;
import grimoire.parsers.*;
import mindustry.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;

public class Grimoire extends Mod{

    public Book book;
    public Grimoire(){
        Parsers.load();

        // TODO: make a class to contain multiple pages, like a book.
        Events.on(FileTreeInitEvent.class, e-> {
        });
        Events.on(ClientLoadEvent.class, e -> {
            loadBooks();
            book.show();
        });
    }

    @Override
    public void loadContent() {
        //new Book(new ZipFi(Vars.mods.getMod("grimoire").file).child("pages")).show();
    }

    public void loadBooks(){
        book = new Book(new ZipFi(Vars.mods.getMod("grimoire").file).child("pages"));
    }
}
