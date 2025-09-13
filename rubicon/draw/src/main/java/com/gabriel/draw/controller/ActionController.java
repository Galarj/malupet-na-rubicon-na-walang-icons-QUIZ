package com.gabriel.draw.controller;

import com.gabriel.draw.view.DrawingMenuBar;
import com.gabriel.draw.view.DrawingToolBar;
import com.gabriel.draw.view.StatusBar;
import com.gabriel.drawfx.ActionCommand;
import com.gabriel.drawfx.ShapeMode;
import com.gabriel.drawfx.service.AppService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionController implements ActionListener {
    AppService appService;
    DrawingToolBar toolBar;
    private final StatusBar statusBar;
    private final DrawingMenuBar menuBar;


    public  ActionController(AppService appService, DrawingToolBar toolBar, StatusBar statusBar,
                             DrawingMenuBar menuBar){
        this.toolBar = toolBar;
        this.appService = appService;
        this.statusBar = statusBar;
        this.menuBar = menuBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String  cmd = e.getActionCommand();
        if(ActionCommand.UNDO.equals(cmd) ){
            appService.undo();
            toolBar.refreshUndoRedo();
            menuBar.refreshUndoRedo();
            statusBar.setStatus("Undo performed");
        }
        else if(ActionCommand.REDO.equals(cmd)) {
            appService.redo();
            toolBar.refreshUndoRedo();
            menuBar.refreshUndoRedo();
            statusBar.setStatus("Redo performed");
        }
        else if (ActionCommand.LINE.equals(cmd)) {
            appService.setShapeMode(ShapeMode.Line);
            statusBar.setStatus("Line mode selected");

        } else if (ActionCommand.RECT.equals(cmd)) {
            appService.setShapeMode(ShapeMode.Rectangle);
            statusBar.setStatus("Rectangle mode selected");
        } else if (ActionCommand.ELLIPSE.equals(cmd)) {
            appService.setShapeMode(ShapeMode.Ellipse);
            statusBar.setStatus("Ellipse mode selected");
        } else if (ActionCommand.COLOR.equals(cmd)) {
            Color selectedColor = JColorChooser.showDialog(null, "Choose a Color", Color.WHITE);
            if (selectedColor != null) {
                appService.setColor(selectedColor);
            }
        }else if(ActionCommand.FILL.equals(cmd)){
            Color selectedFillColor = JColorChooser.showDialog(null, "Choose Fill Color", null);
            if (selectedFillColor != null) {
                appService.setFill(selectedFillColor);
            }
        }
        menuBar.refreshUndoRedo();
        toolBar.refreshUndoRedo();

}

}
