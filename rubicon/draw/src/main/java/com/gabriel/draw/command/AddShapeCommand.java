package com.gabriel.draw.command;

import com.gabriel.drawfx.command.Command;
import com.gabriel.drawfx.model.Drawing;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;

public class AddShapeCommand implements Command{
    Shape shape;
    AppService appService;

    public AddShapeCommand(AppService appService, Shape shape){
        this.shape = shape;
        this.appService = appService;
    }
    @Override
    public void execute() {
        Drawing drawing = (Drawing) appService.getModel();
        drawing.getShapes().add(shape);   // add directly to the list
        appService.getView().repaint();

    }

    @Override
    public void undo() {
        Drawing drawing = (Drawing) appService.getModel();
        drawing.getShapes().remove(shape);  // remove directly from the list
        appService.getView().repaint();
    }

    @Override
    public void redo() {
        execute(); // redo just re-adds

    }
}