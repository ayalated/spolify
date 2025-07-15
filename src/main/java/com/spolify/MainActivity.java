package com.spolify;

import com.formdev.flatlaf.FlatLightLaf;

public class MainActivity {
    public static void main(String[] args) {
        FlatLightLaf.setup();

        SpolifyPlayer player = new SpolifyPlayer();
        player.run();
    }
}
