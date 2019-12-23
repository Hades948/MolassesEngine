package com.tylerroyer.enginetesting;

import com.tylerroyer.molasses.*;

class Driver {
    public static void main(String[] args) {
        Resources.init();
        Game.init(500, 500, "THE MOLASSES GAME ENGINE", new TestScreen());
    }
}