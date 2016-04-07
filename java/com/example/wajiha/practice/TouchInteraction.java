package com.example.wajiha.practice;

import android.graphics.Paint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.text.method.Touch;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class TouchInteraction extends View
{

    private static  float TOUCH_STROKE_WIDTH = 5;
    protected Paint brush;
    protected Bitmap mBitmap;
    public Canvas mCanvas;
    public static boolean isDrawing;
    float x,y,StartX,StartY;
    public static  String  mCurrentShape="Rectangle";

    int countTouch =0;
    float basexTriangle =0;
    float baseyTriangle =0;
    Context c;
    RelativeLayout l;
    Path drawPath;
    Shapes shapes;

    public TouchInteraction(Context context) {
        super(context);
        c=context;

        initialize();

    }

    public void  setShapeName(String s)
    {
        mCurrentShape= s;
        shapes.setShapeName(s);

    }
    protected void initialize()
    {
        drawPath=new Path();
        brush = new Paint(Paint.DITHER_FLAG);
        brush.setAntiAlias(true);
        brush.setColor(getContext().getResources().getColor(android.R.color.holo_blue_dark));
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeWidth(TOUCH_STROKE_WIDTH);

    }
    protected void onSizeChanged(int width, int height, int oldwidth, int oldheight) {
        super.onSizeChanged(width, height, oldwidth, oldheight);
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        shapes=new Shapes(mCanvas,brush,mBitmap,drawPath);


    }

    public boolean onTouchEvent(MotionEvent event) {


        shapes.setShapeName(mCurrentShape);
        if(mCurrentShape=="Touch") {
            float touchX = event.getX();
            float touchY = event.getY();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    drawPath.moveTo(touchX, touchY);
                    break;
                case MotionEvent.ACTION_MOVE:
                    drawPath.lineTo(touchX, touchY);
                    break;
                case MotionEvent.ACTION_UP:
                    mCanvas.drawPath(drawPath, brush);
                    drawPath.reset();
                    break;
                default:
                    return false;
            }
            invalidate();
            return true;
        }
        else {
            x = event.getX();
            y = event.getY();
            shapes.drawShape(event, this);
        }
        return true;


    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmap, 0, 0, brush);
        if(mCurrentShape=="Touch") {
            canvas.drawBitmap(mBitmap, 0, 0, brush);
            canvas.drawPath(drawPath, brush);
        }
        //isDrawing=shapes.getDraw();
        else {
            if (isDrawing) {

                switch (mCurrentShape) {

                    case "Rectangle":
                        shapes.drawRectangle(canvas);
                        break;

                    case "Circle":
                        shapes.drawCircle(canvas);
                        break;
                    case "Triangle":
                        shapes.drawTriangle(canvas);
                        break;
                    case "Oval":
                        shapes.drawOval(canvas);
                        break;
                    case "Text":
                        // drawText(canvas, brush);
                        break;
                    case "Line":
                        shapes.drawLine(canvas);
                        break;
                }
            }
        }
    }

    public void setPolygon(float x, float y, float radius, int numOfPt){

        double section = 2.0 * Math.PI/numOfPt;

        drawPath.reset();
        drawPath.moveTo(
                (float)(x + radius * Math.cos(0)),
                (float)(y + radius * Math.sin(0)));

        for(int i=1; i<numOfPt; i++){
            drawPath.lineTo(
                    (float)(x + radius * Math.cos(section * i)),
                    (float)(y + radius * Math.sin(section * i)));
        }

        drawPath.close();

    }



}