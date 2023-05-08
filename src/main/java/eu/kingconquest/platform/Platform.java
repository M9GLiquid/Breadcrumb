package eu.kingconquest.platform;

import eu.kingconquest.framework.views.GameFrame;

public class Platform {

    public Platform(){
        GameFrame gameFrame = new GameFrame();
        gameFrame.addView(new PlatformMenu(gameFrame), 960, 640);
    }
}
