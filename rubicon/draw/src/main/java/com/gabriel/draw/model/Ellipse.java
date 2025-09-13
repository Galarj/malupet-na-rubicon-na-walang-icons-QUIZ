package com.gabriel.draw.model;

import com.gabriel.draw.service.EllipseRenderer;
import com.gabriel.draw.service.RectangleRendererService;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;

import java.awt.*;

public class Ellipse extends Shape {
    AppService appService;

       public Ellipse(Point start, Point end){
        super(start);
        this.setEnd(end);
        this.setColor(Color.RED);
        this.setRendererService(new EllipseRenderer());
    }
 }