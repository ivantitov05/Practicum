package core;

import decorators.BaseNordianStew;
import decorators.Nordian_stew;

public class Client {
    public static void main(String[] args){
        Nordian_stew nordian_stew = new BaseNordianStew();
        ClientGUI clientGUI = new ClientGUI(nordian_stew);
    }
}
