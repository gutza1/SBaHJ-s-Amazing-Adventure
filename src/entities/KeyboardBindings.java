package entities;

import java.awt.event.ActionEvent;
import java.util.TreeSet;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import com.sun.glass.events.KeyEvent;

import core.Main;

public class KeyboardBindings extends JComponent{
	private Main main;
	private InputMap im;
	private ActionMap am;
	private TreeSet<Integer> currentKeys;
	
	public KeyboardBindings(Main main) {
		this.main = main;
		this.currentKeys = new TreeSet<Integer>();
		InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "goRight");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "goLeft");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "goUp");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "goDown");
        im.put(KeyStroke.getKeyStroke("released RIGHT"), "releasedRight");
        im.put(KeyStroke.getKeyStroke("released LEFT"), "releasedLeft");
        im.put(KeyStroke.getKeyStroke("released UP"), "releasedUp");
        im.put(KeyStroke.getKeyStroke("released Down"), "releasedDown");
        am.put("goRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentKeys.add(KeyEvent.VK_RIGHT);
            }
        });
        am.put("goLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	currentKeys.add(KeyEvent.VK_LEFT);
            }
        });

        am.put("goUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	currentKeys.add(KeyEvent.VK_UP);
            }
        });
        am.put("goDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	currentKeys.add(KeyEvent.VK_DOWN);
            }
        });
        am.put("releasedRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	currentKeys.remove(KeyEvent.VK_RIGHT);
            }
        });
        am.put("releasedLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	currentKeys.remove(KeyEvent.VK_LEFT);
            }
        });

        am.put("releasedUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	currentKeys.remove(KeyEvent.VK_DOWN);
            }
        });
        am.put("releasedDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	currentKeys.remove(KeyEvent.VK_UP);
            }
        });
	}
	
	public TreeSet<Integer> getCurrentKeys() {
		return this.currentKeys;
	}
}
