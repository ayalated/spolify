package com.spolify;

import com.formdev.flatlaf.FlatLightLaf;
import com.spolify.ui.SpolifyPlayer;

public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup();

        SpolifyPlayer player = new SpolifyPlayer();
        player.run();
    }
}
