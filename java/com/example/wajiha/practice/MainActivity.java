package com.example.wajiha.practice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.graphics.Color;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.Toast;
import android.widget.PopupWindow;
import android.view.LayoutInflater;

public class MainActivity extends AppCompatActivity {

    TouchInteraction drawView;
    ImageButton shapePopUp;
    boolean click = true;
    Shapes shapes;
    Button Close;
    ImageButton circle,rectangle,polygon,line,oval,triangle;
    ViewGroup.LayoutParams params;
    LayoutInflater layoutInflater ;
    int count=0;
    float x;
    View layout1;
    SlidingDrawer toolSlider,colorSlider;

    public MainActivity getClassObject()

    {
        return this;
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        drawView = new TouchInteraction(this);
        FrameLayout frm_layout = (FrameLayout) findViewById(R.id.main_frame);
        frm_layout.addView(drawView);

        toolSlider = (SlidingDrawer) findViewById((R.id.slidingDrawerTools));
        colorSlider = (SlidingDrawer) findViewById(R.id.slidingDrawer);

        drawerOpenListenerColor();
        drawerCloseListenerColor();
        drawerOpenListenerTools();
        drawerCloseListenerTools();



        shapePopUp = (ImageButton) findViewById(R.id.shape1);
        shapePopUp.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                showPopup();
            }

        });
        shapes = new Shapes(this);
        circle = (ImageButton) findViewById(R.id.circle1);
        rectangle = (ImageButton) findViewById(R.id.rectangle);
        triangle = (ImageButton) findViewById(R.id.triangle);
        oval = (ImageButton) findViewById(R.id.oval);
        polygon = (ImageButton) findViewById(R.id.polygon);
        line = (ImageButton) findViewById(R.id.line);


    }

    public void drawerOpenListenerColor()
    {

        colorSlider.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
            @Override
            public void onDrawerOpened() {

                //Toast.makeText(MainActivity.this, "Color Picker", Toast.LENGTH_SHORT).show();
                colorSlider.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
                colorSlider.getLayoutParams().height = 200;

            }
        });
    }


    public void drawerOpenListenerTools() {toolSlider.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
        @Override
        public void onDrawerOpened() {

            // Toast.makeText(MainActivity.this, "Tools", Toast.LENGTH_SHORT).show();
            toolSlider.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
            toolSlider.getLayoutParams().width = 200;
        }
    });
    }

    public  void drawerCloseListenerColor()
    {   colorSlider.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
        @Override
        public void onDrawerClosed() {
            colorSlider.getLayoutParams().height = 80;
            colorSlider.getLayoutParams().width = 80;
            Toast.makeText(MainActivity.this, "Paint Closer", Toast.LENGTH_SHORT).show();
            colorSlider.close();
            //  drawers.setPivotX(0);
        }
    });


    }
    public  void drawerCloseListenerTools()


    {   toolSlider.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
        @Override
        public void onDrawerClosed() {


            toolSlider.getLayoutParams().height = 80;
            toolSlider.getLayoutParams().width = 80;
            Toast.makeText(MainActivity.this, "Tool Closer", Toast.LENGTH_SHORT).show();
            toolSlider.close();

            //  drawers.setPivotX(0);
        }
    });


    }



    private PopupWindow pw;
    private void showPopup() {
        try {
// We need to get the instance of the LayoutInflater
            LayoutInflater inflater = (LayoutInflater)MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup,
                    (ViewGroup) findViewById(R.id.popup_1));
            pw = new PopupWindow(layout, 300, 420, true);
            pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
            // Close= (Button) layout.findViewById(R.id.close_popup);
            circle = (ImageButton) layout.findViewById(R.id.circle1);
            rectangle = (ImageButton) layout.findViewById(R.id.rectangle);
            triangle = (ImageButton) layout.findViewById(R.id.triangle);
            oval = (ImageButton) layout.findViewById(R.id.oval);
            polygon = (ImageButton) layout.findViewById(R.id.polygon);
            line = (ImageButton) layout.findViewById(R.id.line);

            circle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    drawView.setShapeName("Circle");
                    pw.dismiss();
                }
            });

            rectangle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    // Log.d("Message","in Shapes");
                    drawView.setShapeName("Rectangle");
                    pw.dismiss();
                }
            });

            oval.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    drawView.setShapeName("Oval");
                    pw.dismiss();
                }
            });

            line.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    drawView.setShapeName("Line");
                    pw.dismiss();

                }
            });

            triangle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    drawView.setShapeName("Triangle");
                    pw.dismiss();
                }
            });

            polygon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    drawView.setShapeName("Polygon");
                    pw.dismiss();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}

