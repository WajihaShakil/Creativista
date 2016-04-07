package com.example.wajiha.practice;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Wajiha on 3/19/2016.
 */
public class Shapes {





    int noOfShapes;
    int size;
    float radius;
    String shapeName;
    float x,y,StartX,StartY;
    Canvas canvas;
    Paint brush;
    Bitmap mBitmap;
    Path path;
    int countTouch =0;
    float basexTriangle =0;
    float baseyTriangle =0;
    Context context;
    public Shapes(Context c )
    {

        context=c;
    }
    public Shapes(Canvas c,Paint brush,Bitmap b,Path p)
    {
        this.canvas=c;
        this.brush=brush;
        this.mBitmap=b;
        path=p;
    }
    public void setShapeName(String name)
    {
        shapeName=name;
    }
    public void setNoOfShapes(int n)
    {
        noOfShapes=n;
    }
    public int getNoOfShapes()
    {
        return noOfShapes;
    }
    public void drawShape(MotionEvent event,View v)
    {

        switch(shapeName)
        {
            case "Rectangle":
                onTouchEventRectangle(event, v);
                break;
            case "Line":
                onTouchEventLine(event, v);
                break;
            case "Circle":
                onTouchEventCircle(event, v);
                break;
            case "Oval":
                onTouchEventOval(event, v);
                break;
            case "Triangle":
                onTouchEventTriangle(event, v);
                break;

        }
    }
    public boolean onTouchEventRectangle(MotionEvent event,View v) {


        //Retrieve the point
        x = event.getX();
        y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                TouchInteraction.isDrawing = true;
                StartX = x;
                StartY = y;
                v.invalidate();
                break;
            case MotionEvent.ACTION_MOVE:

                v.invalidate();
                break;
            case MotionEvent.ACTION_UP:
                TouchInteraction.isDrawing = false;
                drawRectangle(canvas);
                v.invalidate();

                break;
        }
        return true;
    }

    public void drawRectangle(Canvas c){

        float right = StartX > x ? StartX : x;
        float left = StartX > x ? x : StartX;
        float bottom = StartY > y ? StartY : y;
        float top = StartY > y ? y : StartY;
        c.drawRect(left, top, right, bottom, brush);

    }

    public void onTouchEventLine(MotionEvent event,View v) {
        x = event.getX();
        y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                TouchInteraction.isDrawing = true;
                StartX = x;
                StartY = y;

                v.invalidate();
                break;
            case MotionEvent.ACTION_MOVE:

                v.invalidate();
                break;
            case MotionEvent.ACTION_UP:
                TouchInteraction.isDrawing = false;
                drawLine(canvas);

                v.invalidate();

                break;
        }
    }

    public void drawLine(Canvas c){

        c.drawLine(StartX, StartY, x, y,brush);

    }

    public void onTouchEventCircle(MotionEvent event,View v) {

        x = event.getX();
        y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                TouchInteraction.isDrawing = true;
                StartX = x;
                StartY = y;
                radius = 1;
                v.invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                radius = (float) Math.sqrt(Math.pow(x-StartX, 2) + Math.pow(y-StartY, 2));
                v.invalidate();
                break;
            case MotionEvent.ACTION_UP:
                TouchInteraction.isDrawing = false;
                drawCircle(canvas);

                v.invalidate();

                break;
        }
    }

    public void drawCircle(Canvas c){


        c.drawCircle(x, y, radius, brush);
    }

    public void drawTriangle(Canvas c){

        if (countTouch<3){
            c.drawLine(StartX,StartY,x,y,brush);
        }else if (countTouch==3){
            c.drawLine(x,y,StartX,StartY,brush);
            c.drawLine(x,y,basexTriangle,baseyTriangle,brush);
        }
    }

    public void onTouchEventTriangle(MotionEvent event,View v) {

        x = event.getX();
        y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                countTouch++;
                if (countTouch==1){
                    TouchInteraction.isDrawing = true;
                    StartX = x;
                    StartY = y;
                } else if (countTouch==3){
                    TouchInteraction.isDrawing = true;
                }
                v.invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                v.invalidate();
                break;
            case MotionEvent.ACTION_UP:
                countTouch++;
                TouchInteraction.isDrawing = false;
                if (countTouch<3){
                    basexTriangle=x;
                    baseyTriangle=y;
                    canvas.drawLine(StartX,StartY,x,y,brush);
                } else if (countTouch>=3){
                    canvas.drawLine(x,y,StartX,StartY,brush);
                    canvas.drawLine(x,y,basexTriangle,baseyTriangle,brush);
                    countTouch =0;
                }
                v.invalidate();

                break;
        }
    }

    public void onTouchEventOval(MotionEvent event,View v) {

        x = event.getX();
        y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                TouchInteraction.isDrawing = true;
                StartX = x;
                StartY = y;

                v.invalidate();
                break;
            case MotionEvent.ACTION_MOVE:

                v.invalidate();
                break;
            case MotionEvent.ACTION_UP:
                TouchInteraction.isDrawing = false;
                drawOval(canvas);

                v.invalidate();
                break;
        }
    }

    public void drawOval(Canvas c)
    {
        RectF ovalBounds = new RectF(StartX, StartY, x, y);
        c.drawOval(ovalBounds, brush);
    }


}
