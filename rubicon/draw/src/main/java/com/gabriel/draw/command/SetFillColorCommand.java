package com.gabriel.draw.command;

import com.gabriel.drawfx.command.Command;
import com.gabriel.drawfx.service.AppService;

import java.awt.*;

public class SetFillColorCommand implements Command {


    AppService appService;
    Color newColorFill;
    Color oldColorFill;

    public SetFillColorCommand(AppService appService, Color oldColorFill, Color newColorFill) {
        this.appService = appService;
        this.newColorFill = newColorFill;
        if(oldColorFill == null) {
            this.oldColorFill = newColorFill;
        } else {
            this.oldColorFill = oldColorFill;
        }
    }



    @Override
    public void execute() {
        appService.setFill(newColorFill);
    }

    @Override
    public void undo() {
        appService.setFill(oldColorFill);
    }

    @Override
    public void redo() {
        appService.setFill(newColorFill);
    }
}
