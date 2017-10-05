package commands;

import model.Network;

/**
 * Created by user on 9/30/2017.
 */
public class Receiver {
    private final Network network;

    public Receiver(Network network) {
        this.network = network;
    }

    public Network getNetwork() {
        return network;
    }
}
