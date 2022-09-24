package grimoire.pages;

import arc.*;
import arc.files.*;
import arc.scene.*;
import arc.scene.ui.layout.*;
import arc.util.*;
import grimoire.parsers.*;
import mindustry.*;
import mindustry.gen.*;
import mindustry.ui.*;
import mindustry.ui.dialogs.*;

public class Page{
    protected Element[] elements;
    @Nullable
    public Page nextPage, previousPage;

    private static final BaseDialog dialog = new BaseDialog("");
    private static Table cont;
    private static Page currentPage;

    static {
        dialog.titleTable.clear();
        dialog.cont.margin(5f);
        dialog.cont.table(Styles.grayPanel, t -> {
            t.margin(15f);
            t.pane(p -> {
                p.top().left();
                cont = p;
            }).grow().padBottom(15f);
            t.row();
            t.table(b -> {
                b.button(Icon.left, () -> currentPage.previousPage.show()).disabled(b1 -> currentPage.previousPage == null).size(30f);
                b.button(Icon.right, () -> currentPage.nextPage.show()).disabled(b1 -> currentPage.nextPage == null).size(30f);
            });
        }).growY().maxWidth(Core.graphics.getWidth() / (Vars.mobile ? 1f : 2f)).growX();
    }

    public Page(Fi file){
        this(PageParser.parse(file), null, null);
    }

    public Page(Fi file, Page nextPage, Page previousPage){
        this(PageParser.parse(file), nextPage, previousPage);
    }

    public Page(Element[] elements, Page nextPage, Page previousPage){
        this.elements = elements;
        this.nextPage = nextPage;
        this.previousPage = previousPage;
    }

    public void build(Table table){
        for(Element element : elements){
            table.add(element).growX().top().left().padBottom(5f);
            table.row();
        }
    }

    // "Neighbors" doesnt seem like the correct word for this.
    public void setNeighbors(Page previousPage, Page nextPage){
        this.nextPage = nextPage;
        this.previousPage = previousPage;
    }

    public void show(){
        cont.clearChildren();
        build(cont);
        currentPage = this;
        if(!dialog.isShown()) dialog.show();
    }
}