package decorators;

import java.util.ArrayList;
import java.util.List;

public class Frozeberries extends StewDecorator {
    public Frozeberries(Nordian_stew nordian_stew) {
        super(nordian_stew);
    }

    @Override
    public int return_value() {
        return super.return_value() + 6;
    }

    @Override
    public int return_mods() {
        return super.return_mods() + 1;
    }

    @Override
    public List<String> return_modsList() {
        List<String> result = new ArrayList<>(super.return_modsList());
        result.add("Охлаждённые ягоды");
        return result;
    }
}