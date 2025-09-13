package com.gabriel.draw.command;

import com.gabriel.drawfx.command.Command;
import com.gabriel.drawfx.service.AppService;

import java.awt.*;

public class SetColorCommand implements Command {

    AppService appService;
    Color newColor;
    Color oldColor;
    public SetColorCommand(AppService appService,Color oldColor, Color newColor){
        this.appService = appService;
        this.oldColor = appService.getColor();
        this.newColor = newColor;

    }
    @Override
    public void execute() {
        this.appService.setColor(newColor);
    }

    @Override
    public void undo() {
        this.appService.setColor(oldColor);
    }

    @Override
    public void redo() {
        this.appService.setColor(newColor);
    }
}
