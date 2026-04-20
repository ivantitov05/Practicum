package decorators;

import java.util.ArrayList;
import java.util.List;

public class BaseNordianStew implements Nordian_stew {
    private int cost = 50;
    private int mods = 0;
    private List<String> modsList = new ArrayList<>();

    @Override
    public int return_value() {
        return cost;
    }

    @Override
    public int return_mods() {
        return mods;
    }

    @Override
    public List<String> return_modsList() {
        return modsList;
    }
}
