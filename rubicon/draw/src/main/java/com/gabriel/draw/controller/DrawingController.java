package com.gabriel.draw.controller;

import com.gabriel.draw.model.Ellipse;
import com.gabriel.draw.model.Line;
import com.gabriel.draw.model.Rectangle;
import com.gabriel.draw.view.DrawingMenuBar;
import com.gabriel.draw.view.DrawingToolBar;
import com.gabriel.draw.view.StatusBar;
import com.gabriel.drawfx.DrawMode;
import com.gabriel.drawfx.ShapeMode;
import com.gabriel.draw.view.DrawingView;
import com.gabriel.drawfx.service.AppService;
import com.gabriel.drawfx.model.Shape;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

public class DrawingController  implements MouseListener, MouseMotionListener {
    private Point end;
    final private DrawingView drawingView;
    private final DrawingToolBar toolBar;
    private final StatusBar statusBar;
    private  final DrawingMenuBar menuBar;

    Shape currentShape;
    private final AppService appService;
     public DrawingController(AppService appService, DrawingView drawingView, DrawingToolBar toolBar,
                              StatusBar statusBar, DrawingMenuBar menuBar){
       this.appService = appService;
         this.drawingView = drawingView;
            this.toolBar = toolBar;
            this.statusBar = statusBar;
            this.menuBar = menuBar;
         drawingView.addMouseListener(this);
         drawingView.addMouseMotionListener(this);
     }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point start;
        if(appService.getDrawMode() == DrawMode.Idle) {
            start = e.getPoint();
            switch (appService.getShapeMode()){
                case Line:  currentShape = new Line(start, start);
                    currentShape.setColor(appService.getColor());
                    currentShape.setFill(appService.getFill());
                    currentShape.getRendererService().render(drawingView.getGraphics(), currentShape,false );
                    appService.setDrawMode(DrawMode.MousePressed);
                    break;
                case Rectangle:
                    currentShape = new Rectangle(start, start);
                    currentShape.setColor(appService.getColor());
                    currentShape.setFill(appService.getFill());
                    currentShape.getRendererService().render(drawingView.getGraphics(), currentShape,false );
                    appService.setDrawMode(DrawMode.MousePressed);
                    break;
                case  Ellipse:
                    currentShape = new Ellipse(start, start);
                    currentShape.setColor(appService.getColor());
                    currentShape.setFill(appService.getFill());
                    currentShape.getRendererService().render(drawingView.getGraphics(), currentShape,false );
                    appService.setDrawMode(DrawMode.MousePressed);
                    break;

                default:
                    return;
            }

            statusBar.setStatus("Started drawing " + appService.getShapeMode());

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
         if(appService.getDrawMode() == DrawMode.MousePressed){
             end = e.getPoint();
             currentShape.getRendererService().render(drawingView.getGraphics(), currentShape,true );
             appService.scale(currentShape,end);
             currentShape.getRendererService().render(drawingView.getGraphics(), currentShape,false );
             appService.create(currentShape);

             appService.setDrawMode(DrawMode.Idle);
             toolBar.refreshUndoRedo();
             menuBar.refreshUndoRedo();


         }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        statusBar.setMousePosition(x, y);

        if(appService.getDrawMode() == DrawMode.MousePressed) {
                end = e.getPoint();
                currentShape.getRendererService().render(drawingView.getGraphics(), currentShape,true );
                appService.scale(currentShape,end);
                currentShape.getRendererService().render(drawingView.getGraphics(), currentShape,true );
           }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        statusBar.setMousePosition(x, y);

    }
}
