package decorators;

import java.util.ArrayList;
import java.util.List;

public class BaseNordianStew implements Nordian_stew {
    private int cost;
    private int mods;
    private List<String> modsList;

    public BaseNordianStew() {
        this.cost = 50;
        this.mods = 0;
        this.modsList = new ArrayList<>();
    }

    @Override
    public int return_value() {
        return cost;
    }
    @Override
    public int return_mods(){return mods;}
    @Override
    public List<String> return_modsList( ){
        return modsList;
    }
}
