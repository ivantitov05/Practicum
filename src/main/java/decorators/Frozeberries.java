package decorators;

import java.util.ArrayList;
import java.util.List;

public class Frozeberries implements Nordian_stew {
    private Nordian_stew nordian_stew;

    public Frozeberries(Nordian_stew nordian_stew){
        this.nordian_stew = nordian_stew;
    }

    @Override
    public int return_value() {
        return nordian_stew.return_value()+6;
    }
    @Override
    public int return_mods(){return nordian_stew.return_mods()+1;}
    @Override
    public List<String> return_modsList() {
        List<String> result = new ArrayList<>(nordian_stew.return_modsList());
        result.add("Охлаждённые ягоды");
        return result;
    }
}
