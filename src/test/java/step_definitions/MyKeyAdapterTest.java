package step_definitions;
import Game.MyKeyAdapter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyKeyAdapterTest {

    @Test
    void downKeyPressed() {
        MyKeyAdapter mka = new MyKeyAdapter();
        String direction = "right";
        String action = "down";
        String result = mka.keyPressed(direction, action);
        assertEquals("Going down!", result);
    }

    @Test
    void upKeyPressed() {
        MyKeyAdapter mka = new MyKeyAdapter();
        String direction = "right";
        String action = "up";
        String result = mka.keyPressed(direction, action);
        assertEquals("Going up!", result);
    }

    @Test
    void leftKeyPressed() {
        MyKeyAdapter mka = new MyKeyAdapter();
        String direction = "down";
        String action = "left";
        String result = mka.keyPressed(direction, action);
        assertEquals("Going left!", result);
    }

    @Test
    void rightKeyPressed() {
        MyKeyAdapter mka = new MyKeyAdapter();
        String direction = "up";
        String action = "right";
        String result = mka.keyPressed(direction, action);
        assertEquals("Going right!", result);
    }
}
