package io.codecrafts;

public class ToyRobot {
    private int currentX;
    private int currentY;
    private Direction currentDirection;

    public ToyRobot(int x, int y, Direction direction) {
        this.currentX = x;
        this.currentY = y;
        this.currentDirection = direction;
    }

    public void move() {
        switch(getCurrentDirection()) {
            case NORTH:
                currentY += 1;
                break;
            case WEST:
                currentX -= 1;
                break;
            case SOUTH:
                currentY -= 1;
                break;
            case EAST:
                currentX += 1;
                break;
            default:
                break;
        }
    }

    // rotate clockwise
    public void rotateClockwise() {
        switch(getCurrentDirection()) {
            case NORTH:
                setCurrentDirection(Direction.EAST);
                break;
            case EAST:
                setCurrentDirection(Direction.SOUTH);
                break;
            case SOUTH:
                setCurrentDirection(Direction.WEST);
                break;
            case WEST:
                setCurrentDirection(Direction.NORTH);
                break;
            default:
                break;
        }
    }

    // rotate anti-clockwise
    public void rotateAntiClockwise() {
        switch(getCurrentDirection()) {
            case NORTH:
                setCurrentDirection(Direction.WEST);
                break;
            case WEST:
                setCurrentDirection(Direction.SOUTH);
                break;
            case SOUTH:
                setCurrentDirection(Direction.EAST);
                break;
            case EAST:
                setCurrentDirection(Direction.NORTH);
                break;
            default:
                break;
        }
    }

    public void report() {
        System.out.println(String.format("Output: " + getCurrentX() + "," + getCurrentY() + "," + getCurrentDirection()));
    }

    public int getCurrentX() {
        return currentX;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }
}
