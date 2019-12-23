package com.tylerroyer.enginetesting;

import com.tylerroyer.molasses.*;

class Driver {
    public static void main(String[] args) {
        Resources.init(1920.0, 1080.0);
        Game.init(1920.0, 1080.0, 500, 500, "THE MOLASSES GAME ENGINE", new TestScreen());
    }
}