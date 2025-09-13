package com.gabriel.draw.command;

import com.gabriel.drawfx.command.Command;
import com.gabriel.drawfx.service.AppService;

public class MoveShapeCommand implements Command {

    /*
    =====================================================
    NOT YET IMPLEMENTED
    =====================================================
     */
    AppService appService;
    int x;
    int y;
    int oldX;
    int oldY;

    public MoveShapeCommand(AppService appService, int oldX, int oldY, int x, int y) {
        this.appService = appService;
        this.x = x;
        this.y = y;
        this.oldX = oldX;
        this.oldY = oldY;
    }
    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

}
