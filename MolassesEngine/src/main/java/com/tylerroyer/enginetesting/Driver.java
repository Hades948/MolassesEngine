package com.tylerroyer.enginetesting;

import com.tylerroyer.molasses.*;

class Driver {
    public static void main(String[] args) {
        // TODO Maybe have a config file to handle all of this hard-coded stuff.
        Resources.init(1920.0, 1080.0);
        Game.init(1200, 800, "THE MOLASSES GAME ENGINE", new TestScreen());
    }
}