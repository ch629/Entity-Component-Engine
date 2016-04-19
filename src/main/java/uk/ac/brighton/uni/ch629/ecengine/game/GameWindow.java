package uk.ac.brighton.uni.ch629.ecengine.game;

import uk.ac.brighton.uni.ch629.ecengine.TestListener;
import uk.ac.brighton.uni.ch629.ecengine.event.MouseClickEvent;
import uk.ac.brighton.uni.ch629.ecengine.event.EventBus;
import uk.ac.brighton.uni.ch629.ecengine.event.MouseScrollEvent;
import uk.ac.brighton.uni.ch629.ecengine.misc.Keyboard;
import uk.ac.brighton.uni.ch629.ecengine.misc.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The Window for the game to be contained within.
 */
public class GameWindow extends JFrame {
    /**
     * The Keyboard Handler for this Window
     */
    public final Keyboard KEYBOARD;
    /**
     * The Mouse Handler for this Window
     */
    public final Mouse MOUSE;

    /**
     *
     * @param title - The Title of the Window
     * @param width - The Width of the Window
     * @param height - The Height of the Window
     */
    public GameWindow(String title, int width, int height) {
        super(title);
        setContentPane(new DrawPane());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(width, height);
        setVisible(true);

        final EventBus eventBus = new EventBus();
        KEYBOARD = new Keyboard(eventBus);
        MOUSE = new Mouse();

        TestListener tl = new TestListener(eventBus);


        //TODO: Add the other Listener events & Maybe find a more elegant way to do this.
        addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                KEYBOARD.pressKey(e.getKeyCode());
            }

            public void keyReleased(KeyEvent e) {
                KEYBOARD.releaseKey(e.getKeyCode());
            }
        });

        addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                eventBus.send(new MouseClickEvent(getMousePosition()));
            }

            public void mousePressed(MouseEvent e) {

            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }
        });

        addMouseWheelListener(new MouseWheelListener() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                eventBus.send(new MouseScrollEvent(e.getPoint(), e.getWheelRotation()));
            }
        });
    }
}

/**
 * The Pane within the JFrame to handle rendering objects
 */
class DrawPane extends JPanel {
    @Override
    public void paintComponent(Graphics graphics) {
        graphics.fillRect(20, 20, 100, 200);
    }
}