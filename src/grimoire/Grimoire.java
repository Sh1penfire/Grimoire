package grimoire;

import arc.*;
import arc.scene.*;
import grimoire.pages.*;
import mindustry.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.*;
import mindustry.ui.dialogs.*;

public class Grimoire extends Mod{
    public static Element[] testElements;

    public Grimoire(){
        Parsers.load();

        // TODO: make a class to wrap around the page creation process.
        Events.on(ClientLoadEvent.class, e-> {
            testElements = PageParser.parse(Vars.tree.get("pages/test.txt"));

            BaseDialog testDialog = new BaseDialog("test");
            testDialog.cont.pane(t -> {
                t.top().left();
                t.defaults().padBottom(15f);
                t.margin(15f);
                t.setBackground(Styles.grayPanel);
                for(Element element : testElements){
                    t.add(element).growX().left();
                    t.row();
                }
            }).growY().width(Core.graphics.getWidth() / 2f);

            testDialog.show();
        });
    }
}
