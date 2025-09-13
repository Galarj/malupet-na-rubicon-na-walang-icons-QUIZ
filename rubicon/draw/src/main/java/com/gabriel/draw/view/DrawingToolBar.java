package com.gabriel.draw.view;

import com.gabriel.drawfx.ActionCommand;
import com.gabriel.drawfx.command.CommandService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;



public class DrawingToolBar extends JToolBar {
    JButton undoButton;
    JButton redoButton;
    public DrawingToolBar(ActionListener actionListener){
        undoButton = new JButton("Undo");
        undoButton.setActionCommand(ActionCommand.UNDO);
        undoButton.setToolTipText("Undo last action");
        undoButton.addActionListener(actionListener);

         // disable if undo stack is null

        undoButton.setEnabled(false); // initially disabled
        this.add(undoButton);

        redoButton = new JButton("Redo");
        redoButton.setActionCommand(ActionCommand.REDO);
        redoButton.setToolTipText("Redo last undone action");
        redoButton.addActionListener(actionListener);
        this.add(redoButton);
        redoButton.setEnabled(false);
        this.addSeparator(); // visually separate shapes and actions

        // shapes
        JButton lineButton = new JButton("Line"); // label so user can see what the button does
        lineButton.setActionCommand(ActionCommand.LINE); // action command so we can identify which button was pressed in the action listener
        lineButton.setToolTipText("Draw a line"); // tooltip so user can see what the button does when they hover over it
        lineButton.addActionListener(actionListener); // action listner
        this.add(lineButton); // add button to toolbar

        JButton rectButton = new JButton("Rectangle");
        rectButton.setActionCommand(ActionCommand.RECT);
        rectButton.setToolTipText("Draw a rectangle");
        rectButton.addActionListener(actionListener);
        this.add(rectButton);

        JButton ellipseButton = new JButton("Ellipse");
        ellipseButton.setActionCommand(ActionCommand.ELLIPSE);
        ellipseButton.setToolTipText("Draw an ellipse");
        ellipseButton.addActionListener(actionListener);
        this.add(ellipseButton);

        JButton colorButton = new JButton("Color");
        colorButton.setActionCommand(ActionCommand.COLOR);
        colorButton.setToolTipText("Set drawing color");
        colorButton.addActionListener(actionListener);
        this.add(colorButton);

        JButton fillButton = new JButton("Fill");
        fillButton.setActionCommand(ActionCommand.FILL);
        fillButton.setToolTipText("Fill shape");
        fillButton.addActionListener(actionListener);
        this.add(fillButton);




    }

    public void refreshUndoRedo() {
        undoButton.setEnabled(CommandService.canUndo());
        redoButton.setEnabled(CommandService.canRedo());
    }

    public void setActionListener(java.awt.event.ActionListener listener) {
        for (Component comp : this.getComponents()) {
            if (comp instanceof JButton) {
                ((JButton) comp).addActionListener(listener);
            }
        }
    }


}
