package com.gabriel.draw;

import com.gabriel.draw.controller.ActionController;
import com.gabriel.draw.controller.DrawingController;
import com.gabriel.draw.service.DeawingCommandAppService;
import com.gabriel.draw.service.DrawingAppService;
import com.gabriel.draw.view.*;
import com.gabriel.drawfx.service.AppService;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // App service setup
        AppService drawingAppService = new DrawingAppService();
        AppService appService = new DeawingCommandAppService(drawingAppService);

        //  Create main frame
        DrawingFrame drawingFrame = new DrawingFrame(appService);

        // Create toolbar, status bar, and menu bar
        DrawingToolBar drawingToolBar = new DrawingToolBar(null);
        StatusBar statusBar = new StatusBar();
        DrawingMenuBar drawingMenuBar = new DrawingMenuBar(null);

        //  Create ONE ActionController with all components
        ActionController actionController =
                new ActionController(appService, drawingToolBar, statusBar, drawingMenuBar);

        // Hook up listeners
        drawingToolBar.setActionListener(actionController);
        drawingMenuBar.setActionListener(actionController);

        // Attach menu bar to frame
        drawingFrame.setJMenuBar(drawingMenuBar);

        // Drawing view + controller
        DrawingView drawingView = new DrawingView(appService);
        DrawingController drawingController =
                new DrawingController(appService, drawingView, drawingToolBar, statusBar, drawingMenuBar);
        drawingView.addMouseMotionListener(drawingController);
        drawingView.addMouseListener(drawingController);

        //  Layout
        drawingFrame.setLayout(new BorderLayout());
        drawingFrame.add(drawingToolBar, BorderLayout.NORTH);
        drawingFrame.add(drawingView, BorderLayout.CENTER);
        drawingFrame.add(statusBar, BorderLayout.SOUTH);

        //  Frame settings
        drawingFrame.setSize(500, 500);
        drawingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawingFrame.setVisible(true);

        //  Ensure toolbar visible
        drawingToolBar.setVisible(true);
    }
}
