package com.tylerroyer.enginetesting;

import com.tylerroyer.molasses.*;

class Driver {
    public static void main(String[] args) {
        Config.windowWidth = 1200;  Config.windowHeight = 800;
        Config.firstScreen = new MainScreen();
        Config.projectResourcePath = "MolassesEngine/src/main/java/res/";

        Game.start();
    }
}
