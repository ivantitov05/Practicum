package decorators;

import java.util.ArrayList;
import java.util.List;

public class Nordianlepeha implements Nordian_stew {
    private Nordian_stew nordian_stew;

    public Nordianlepeha(Nordian_stew nordian_stew){
        this.nordian_stew = nordian_stew;
    }

    @Override
    public int return_value() {
        return nordian_stew.return_value()+7;
    }
    @Override
    public int return_mods(){return nordian_stew.return_mods()+1;}

    @Override
    public List<String> return_modsList() {
        List<String> result = new ArrayList<>(nordian_stew.return_modsList());
        result.add("Нордская лепёшка");
        return result;
    }
}
