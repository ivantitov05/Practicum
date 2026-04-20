package decorators;

import java.util.List;

public abstract class StewDecorator implements Nordian_stew {
    private Nordian_stew nordian_stew;

    public StewDecorator(Nordian_stew nordian_stew) {
        this.nordian_stew = nordian_stew;
    }

    @Override
    public int return_value() {
        return nordian_stew.return_value();
    }

    @Override
    public int return_mods() {
        return nordian_stew.return_mods();
    }

    @Override
    public List<String> return_modsList() {
        return nordian_stew.return_modsList();
    }
}