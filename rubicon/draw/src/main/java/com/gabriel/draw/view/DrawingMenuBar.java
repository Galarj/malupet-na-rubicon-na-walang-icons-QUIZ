package com.gabriel.draw.view;

import com.gabriel.drawfx.ActionCommand;
import com.gabriel.drawfx.command.CommandService; // so we can check undo/redo state

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class DrawingMenuBar extends JMenuBar {

    private final JMenuItem lineMenuItem = new JMenuItem("Line");
    private final JMenuItem rectangleMenuItem = new JMenuItem("Rectangle");
    private final JMenuItem ellipseMenuItem = new JMenuItem("Ellipse");

    private final JMenuItem undoMenuItem = new JMenuItem("Undo");
    private final JMenuItem redoMenuItem = new JMenuItem("Redo");
    private final JMenuItem colorMenuItem = new JMenuItem("Color");
    private final JMenuItem fillMenuItem = new JMenuItem("Fill");

    public DrawingMenuBar(ActionListener actionListener) {
        super();

        // ==== EDIT MENU ====
        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        add(editMenu);

        undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        undoMenuItem.setActionCommand(ActionCommand.UNDO);
        undoMenuItem.addActionListener(actionListener);
        undoMenuItem.setEnabled(false); // disabled initially
        editMenu.add(undoMenuItem);

        redoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        redoMenuItem.setActionCommand(ActionCommand.REDO);
        redoMenuItem.addActionListener(actionListener);
        redoMenuItem.setEnabled(false); // disabled initially
        editMenu.add(redoMenuItem);

        // ==== DRAW MENU ====
        JMenu drawMenu = new JMenu("Draw");
        drawMenu.setMnemonic(KeyEvent.VK_D);
        add(drawMenu);

        lineMenuItem.setActionCommand(ActionCommand.LINE);
        lineMenuItem.addActionListener(actionListener);
        drawMenu.add(lineMenuItem);

        rectangleMenuItem.setActionCommand(ActionCommand.RECT);
        rectangleMenuItem.addActionListener(actionListener);
        drawMenu.add(rectangleMenuItem);

        ellipseMenuItem.setActionCommand(ActionCommand.ELLIPSE);
        ellipseMenuItem.addActionListener(actionListener);
        drawMenu.add(ellipseMenuItem);

        // ==== PROPERTIES MENU ====
        JMenu propMenu = new JMenu("Properties");
        propMenu.setMnemonic(KeyEvent.VK_P);
        add(propMenu);

        colorMenuItem.setActionCommand(ActionCommand.COLOR);
        colorMenuItem.addActionListener(actionListener);
        propMenu.add(colorMenuItem);

        fillMenuItem.setActionCommand(ActionCommand.FILL);
        fillMenuItem.addActionListener(actionListener);
        propMenu.add(fillMenuItem);

    }


    public void refreshUndoRedo() {
        undoMenuItem.setEnabled(CommandService.canUndo());
        redoMenuItem.setEnabled(CommandService.canRedo());
    }

    public void setActionListener(ActionListener listener) {
        for (MenuElement element : this.getSubElements()) {
            if (element instanceof JMenu) {
                JMenu menu = (JMenu) element;
                for (Component comp : menu.getMenuComponents()) {
                    if (comp instanceof JMenuItem) {
                        ((JMenuItem) comp).addActionListener(listener);
                    }
                }
            }
        }
    }

}
