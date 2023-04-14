package eu.kingconquest;

public class Sokoban implements Game{
    private SokobanBoard board;
    private GameController controller;
    private GameState state;
    private GUI ui;

    @Override
    public void initiate() {
        this.state = GameState.INITIATING;
        board = new SokobanBoard(20,20);
        controller = new GameController(this);
        ui = new GUI(this);

    }
    @Override
    public void start() {
        this.state = GameState.RUNNING;
    }
    @Override
    public void reset() {
        this.state = GameState.RESETTING;
    }

    @Override
    public boolean isFinished() {
        return state != GameState.GAME_OVER;
    }

    public GameState getState(){
        return state;
    }


    public void setState(GameState state){
        this.state = state;
    }


    @Override
    public void registerObserver(GameObserver observer) {
        controller.addObserver(observer);
    }

    @Override
    public void removeObserver(GameObserver observer) {
        controller.removeObserver(observer);
    }
}
