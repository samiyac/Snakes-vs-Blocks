package application;

import java.io.Serializable;
import java.util.ArrayList;

class SerializableBlock implements Serializable {
    private int LOCATION_X;
    private int LOCATION_Y;
    private final double Translate_Y;
    private final int width;
    private final int height;
    private int value;
    private final String color;
    private boolean eaten;

    public SerializableBlock(Block block) {
        // TODO Auto-generated constructor stub
        LOCATION_X = block.getLocationX();
        LOCATION_Y = block.getLocationY();
        this.width = block.getWidth();
        this.height = block.getHeight();
        this.color = block.getColor().toString();
        this.eaten = block.isEaten();
        this.value = block.getValue();
        this.Translate_Y = block.getStack().getTranslateY();
    }

    public Block constructBlock(Main main) {
        Block block = new Block(width, height, color, value, eaten, LOCATION_X, LOCATION_Y, Translate_Y);
        main.getRoot().getChildren().add(block.getStack());
        return block;
    }

}

class SerializableWall implements Serializable {
    private float LOCATION_X;
    private float LOCATION_Y;
    private final double Translate_Y;
    private final float height;

    public SerializableWall(Wall wall) {
        // TODO Auto-generated constructor stub
        LOCATION_X = wall.getLocationX();
        LOCATION_Y = wall.getLoactionY();
        Translate_Y = wall.getRect().getTranslateY();
        height = wall.getHeight();
    }

    public Wall constructWall(Main main) {
        Wall wall = new Wall(LOCATION_X, LOCATION_Y, Translate_Y, height);
        main.getRoot().getChildren().add(wall.getRect());
        return wall;
    }
}

class SerializableSnake implements Serializable {
    private int length;
    private int LOCATION_X;
    private int LOCATION_Y;
    private double snakeHeadPosX;
    private final double radius;
    private String color;

    public SerializableSnake(Snake snake) {
        length = snake.getLength();
        LOCATION_X = snake.getLOCATION_X();
        LOCATION_Y = snake.getLOCATION_Y();
        snakeHeadPosX = snake.getSnakeLength().get(0).getCenterX();
        radius = snake.getSnakeLength().get(0).getRadius();
        color = snake.getColor().toString();
    }

    public Snake constructSnake(Main main) {
        Snake snake = new Snake(length, LOCATION_X, LOCATION_Y, snakeHeadPosX, radius, color, main);
        return snake;
    }
}

class SerializableBall extends Token implements Serializable {
    private final float radius;
    private final String color;
    private final int value;
    private final double Translate_Y;
    private boolean eaten = false;

    public SerializableBall(Ball ball) {
        // TODO Auto-generated constructor stub
        super(ball.getLOCATION_X(), ball.getLOCATION_Y());
        this.radius = ball.getRadius();
        this.color = ball.getColor().toString();
        this.eaten = ball.isEaten();
        this.value = ball.getValue();
        this.Translate_Y = ball.getPane().getTranslateY();
    }

    public Ball constructBall(Main main) {
        Ball ball = new Ball(LOCATION_X, LOCATION_Y, Translate_Y, radius, color, eaten, value);
        main.getRoot().getChildren().add(ball.getPane());
        return ball;
    }
}

class SerializableShield extends Token implements Serializable {

    private double Translate_Y;

    public SerializableShield(float X, float Y, double translateY) {
        // TODO Auto-generated constructor stub
        super(X, Y);
        Translate_Y=translateY;
    }

    public Shield constructShield(Main main) {
        Shield shield = new Shield(LOCATION_X, LOCATION_Y, Translate_Y);
        main.getRoot().getChildren().add(shield.getStack());
        return shield;
    }
}

class SerializableMagnet extends Token implements Serializable {

    private double Translate_Y;

    public SerializableMagnet(float X, float Y, double translateY) {
        // TODO Auto-generated constructor stub
        super(X, Y);
        Translate_Y=translateY;
    }

    public Magnet constructMagnet(Main main) {
        Magnet magnet = new Magnet(LOCATION_X, LOCATION_Y, Translate_Y);
//		magnet.getStack().setTranslateY(Translate_Y);
        main.getRoot().getChildren().add(magnet.getStack());
        return magnet;
    }
}

class SerializableDB extends Token implements Serializable {

    private double Translate_Y;

    public SerializableDB(float X, float Y, double translateY) {
        // TODO Auto-generated constructor stub
        super(X, Y);
        Translate_Y=translateY;
    }

    public DestroyBlock constructDB(Main main) {
        DestroyBlock DB = new DestroyBlock(LOCATION_X, LOCATION_Y, Translate_Y);
//		DB.getStack().setTranslateY(Translate_Y);
        main.getRoot().getChildren().add(DB.getStack());
        return DB;
    }
}

class SerializableCoin extends Token implements Serializable {

    private boolean eaten;
    private final double Translate_Y;

    public SerializableCoin(Coin coin) {
        super(coin.getLOCATION_X(), coin.getLOCATION_Y());
        Translate_Y = coin.getStack().getTranslateY();
        this.eaten = coin.isEaten();
    }

    public Coin constructCoin(Main main) {
        Coin coin = new Coin(LOCATION_X, LOCATION_Y, Translate_Y, eaten);
        main.getRoot().getChildren().add(coin.getStack());
        return coin;
    }

}

public class SerializableClasses {
    public ArrayList<ArrayList<Block>> constructBlockList(ArrayList<ArrayList<SerializableBlock>> serializableBlocks,
                                                          Main main) {
        ArrayList<ArrayList<Block>> blocks = new ArrayList<>();
        for (int i = 0; i < serializableBlocks.size(); i++) {
            blocks.add(new ArrayList<Block>());
            for (SerializableBlock sBlock : serializableBlocks.get(i)) {
                Block temp = sBlock.constructBlock(main);
                blocks.get(i).add(temp);
                if(temp.isEaten()) {
                    temp.getStack().setVisible(false);
                }
            }
        }
        return blocks;
    }

    public ArrayList<ArrayList<Ball>> constructBallList(ArrayList<ArrayList<SerializableBall>> serializableBalls,
                                                        Main main) {
        ArrayList<ArrayList<Ball>> balls = new ArrayList<>();
        for (int i = 0; i < serializableBalls.size(); i++) {
            balls.add(new ArrayList<Ball>());
            for (SerializableBall sBall : serializableBalls.get(i)) {
                Ball temp = sBall.constructBall(main);
                balls.get(i).add(temp);
                if(temp.isEaten()) {
                    temp.getPane().setVisible(false);
                }
            }
        }
        return balls;
    }

    public ArrayList<ArrayList<Wall>> constructWallList(ArrayList<ArrayList<SerializableWall>> serializableWalls,
                                                        Main main) {
        ArrayList<ArrayList<Wall>> walls = new ArrayList<>();
        for (int i = 0; i < serializableWalls.size(); i++) {
            walls.add(new ArrayList<Wall>());
            for (SerializableWall sWall : serializableWalls.get(i)) {
                Wall temp = sWall.constructWall(main);
                walls.get(i).add(temp);
            }
        }
        return walls;
    }

    public ArrayList<ArrayList<Coin>> constructCoinList(ArrayList<ArrayList<SerializableCoin>> serializableCoins,
                                                        Main main) {
        ArrayList<ArrayList<Coin>> coins = new ArrayList<>();
        for (int i = 0; i < serializableCoins.size(); i++) {
            coins.add(new ArrayList<Coin>());
            for (SerializableCoin sCoin : serializableCoins.get(i)) {
                Coin temp = sCoin.constructCoin(main);
                coins.get(i).add(temp);
                if(temp.isEaten()) {
                    temp.getStack().setVisible(false);
                }
            }
        }
        return coins;
    }

    public ArrayList<ArrayList<SerializableBlock>> constructSerializableBlockList(
            ArrayList<ArrayList<Block>> blockOnScreen) {
        ArrayList<ArrayList<SerializableBlock>> Sblocks = new ArrayList<>();
        for (int i = 0; i < blockOnScreen.size(); i++) {
            Sblocks.add(new ArrayList<SerializableBlock>());
            for (Block block : blockOnScreen.get(i)) {
                Sblocks.get(i).add(new SerializableBlock(block));
            }
        }
        return Sblocks;
    }

    public ArrayList<ArrayList<SerializableBall>> constructSerializableBallList(
            ArrayList<ArrayList<Ball>> ballsOnScreen) {
        ArrayList<ArrayList<SerializableBall>> Sballs = new ArrayList<>();
        for (int i = 0; i < ballsOnScreen.size(); i++) {
            Sballs.add(new ArrayList<SerializableBall>());
            for (Ball ball : ballsOnScreen.get(i)) {
                Sballs.get(i).add(new SerializableBall(ball));
            }
        }
        return Sballs;
    }

    public ArrayList<ArrayList<SerializableWall>> constructSerializableWallList(
            ArrayList<ArrayList<Wall>> wallsOnScreen) {
        ArrayList<ArrayList<SerializableWall>> Swalls = new ArrayList<>();
        for (int i = 0; i < wallsOnScreen.size(); i++) {
            Swalls.add(new ArrayList<SerializableWall>());
            for (Wall wall : wallsOnScreen.get(i)) {
                Swalls.get(i).add(new SerializableWall(wall));
            }
        }
        return Swalls;
    }

    public ArrayList<ArrayList<SerializableCoin>> constructSerializableCoinList(
            ArrayList<ArrayList<Coin>> coinsOnScreen) {
        ArrayList<ArrayList<SerializableCoin>> Scoins = new ArrayList<>();
        for (int i = 0; i < coinsOnScreen.size(); i++) {
            Scoins.add(new ArrayList<SerializableCoin>());
            for (Coin coin : coinsOnScreen.get(i)) {
                Scoins.get(i).add(new SerializableCoin(coin));
            }
        }
        return Scoins;
    }
}