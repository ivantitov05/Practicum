package decorators;

import java.util.ArrayList;
import java.util.List;

public class Flamesouse extends StewDecorator {
    public Flamesouse(Nordian_stew nordian_stew) {
        super(nordian_stew);
    }

    @Override
    public int return_value() {
        return super.return_value() + 10;
    }

    @Override
    public int return_mods() {
        return super.return_mods() + 1;
    }

    @Override
    public List<String> return_modsList() {
        List<String> result = new ArrayList<>(super.return_modsList());
        result.add("Шрирача");
        return result;
    }
}
