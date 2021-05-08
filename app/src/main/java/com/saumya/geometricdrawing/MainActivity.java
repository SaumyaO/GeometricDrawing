 package com.saumya.geometricdrawing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
Spinner ColorStoke;
Spinner ColorFill;
Spinner GeometricDesign;
RelativeLayout rl;
    List<String> design = new ArrayList<String>();

    private ArrayList<Path> paths = new ArrayList<Path>();
    private ArrayList<Path> undonePaths = new ArrayList<Path>();

ImageView imageview;
Canvas canvas;
    Canvas canvasNew;
Bitmap bitmap;
Bitmap BackupBitmap;
   Paint paint = new Paint();
   Path path =new Path();
   Path Roundpath =new Path();
   float startX=0,startY=0,stopX=0,stopY=0;
    float intLineStartX = 0, intLineStartY = 0, intLineEndX = 0, intLineEndY = 0;
    float intRectangleStartX = 0, intRectangleStartY = 0, intRectangleEndX = 0, intRectangleEndY = 0;
    int distanceX=0, distanceY=0;

    float intCircleStartX = 0, intCircleStartY = 0;
    float distanceGlobal = 0;
    float centerCircleX=0;
    float centerCircleY=0;

    float intTriangleStartX = 0, intTriangleStartY=0;
    float distanceleftX =0, distancerightX =0, distanceTriangleUpY =0, distanceTriangleDownY =0;
    float intTriangleleftX=0, intTrianglerightX=0,intTriangleUpY =0,intTriangleDownY ;

    float ErasepointX=0, ErasepointY=0;

    private Button blue;
    private Button red;
    private Button brown;
    private Button yellow;
    private Button orange;
    private Button black;
    private Button violet;
    private Button green;

    private RadioButton rdStroke;
    private RadioButton rdStrokeandFill;
    private Button clear;


    int Shapes =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // ColorStoke = (Spinner) findViewById(R.id.ColorStoke);

        paint = new Paint();
        path =new Path();
        imageview = findViewById(R.id.imageview);
        //ColorStoke = (Spinner) findViewById(R.id.ColorStoke);
        GeometricDesign = (Spinner) findViewById(R.id.design_dropdown);
        rl = findViewById(R.id.imagedrwan);
       // clear =findViewById(R.id.clear);
       // ColorFill = (Spinner) findViewById(R.id.ColorFill);

        float dw = Resources.getSystem().getDisplayMetrics().widthPixels;
        float dh = Resources.getSystem().getDisplayMetrics().heightPixels;
       // float dw_xcord = Resources.getSystem().getDisplayMetrics().
/*
* Bitmap animation = BitmapFactory.decodeResource(mContext.getResources(), resourceId, mBitmapOptions); //Get a bitmap from a image file

// Create a bitmap for the part of the screen that needs updating.
Bitmap bitmap = Bitmap.createBitmap(animation.getWidth(), animation.getHeight(), BITMAP_CONFIG);
bitmap.setDensity(DisplayMetrics.DENSITY_DEFAULT);
Canvas canvas = new Canvas(bitmap);
* */
       // Bitmap animation = BitmapFactory.decodeResource(R, R.id.imagedrwan, null);
        //int dw1=rl.getWidth();
        //int dh1=rl.getHeight();

        System.out.println(" dw : " + dw + " dh: "+dh );
        //System.out.println(" dw_xcord : " + dw + " dh_cord: "+dh );





       // bitmap = Bitmap.createBitmap((int) dw, (int) dh,
               // Bitmap.Config.ARGB_8888);

        bitmap = Bitmap.createBitmap(1200, 1500,
                Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        //canvasNew= new Canvas();
        int dw1= bitmap.getScaledWidth(canvas);
        int dh1=bitmap.getScaledHeight(canvas);


        System.out.println(" dw1_image : " + dw1 + " dh1_image: "+dh1 );



        paint.setAntiAlias(true);

        paint.setStrokeJoin(Paint.Join.ROUND);
         //paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);


        GeometricDesign.setOnItemSelectedListener(this);
       // ColorStoke.setOnItemSelectedListener(this);


        design.add("Freehand");
        design.add("Line");
        design.add("Circle");
        design.add("Rectangle");
        design.add("Triangle");
        design.add("Erase");
        design.add("Undo");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.custom_spinner_items, design);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        GeometricDesign.setAdapter(dataAdapter);


        blue = findViewById(R.id.blue);
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paint.setColor(Color.BLUE);
                blue.setPressed(true);
            }
        });

        red = findViewById(R.id.red);
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paint.setColor(Color.RED);
            }
        });

        brown = findViewById(R.id.brown);
        brown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paint.setColor(Color.parseColor("#964B00"));
            }
        });
        yellow = findViewById(R.id.yellow);
        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paint.setColor(Color.parseColor("#FFFF00"));
            }
        });
        orange = findViewById(R.id.orange);
        orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paint.setColor(Color.parseColor("#FF8C00"));
            }
        });
        black = findViewById(R.id.black);
        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paint.setColor(Color.parseColor("#000000"));
            }
        });
        violet = findViewById(R.id.violet);
        violet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paint.setColor(Color.parseColor("#9400d3"));
                violet.setFocusableInTouchMode(true);
            }
        });
        green = findViewById(R.id.green);
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paint.setColor(Color.parseColor("#006400"));
            }
        });

        rdStroke=findViewById(R.id.stroke);
        rdStrokeandFill=findViewById(R.id.strokeandFill);
        clear=findViewById(R.id.clear);

        rdStroke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rdStroke.isChecked())
                {
                    rdStrokeandFill.setChecked(false);

                }
            }
        });

        rdStrokeandFill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rdStrokeandFill.isChecked())
                {   rdStroke. setChecked(false);

                }
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    bitmap.eraseColor(Color.TRANSPARENT);
                    path.reset();
                    imageview.invalidate();


            }
        });





/*
        colorstroke.add("RED");
        colorstroke.add("Green");
        colorstroke.add("Black");
        colorstroke.add("Blue");
        colorstroke.add("MAGENTA");
        colorstroke.add("CYAN");
        colorstroke.add("DarkGRAY");
        colorstroke.add("ORANGE");
        ArrayAdapter<String> dataAdaptercolor = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, colorstroke);
        dataAdaptercolor.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ColorStoke.setAdapter(dataAdaptercolor);

 */
/*
        colorfills.add("Fill:RED");
        colorfills.add("Fill:Green");
        colorfills.add("Fill:Black");
        colorfills.add("Fill:Blue");
        colorfills.add("Fill:MAGENTA");
        colorfills.add("Fill:CYAN");
        colorfills.add("Fill:DarkGRAY");
        colorfills.add("Fill:ORANGE");
        ArrayAdapter<String> dataAdaptercolorfill = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, colorfills);
        dataAdaptercolorfill.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ColorFill.setAdapter(dataAdaptercolorfill);
*/
        imageview.setImageBitmap(bitmap);
        imageview.setOnTouchListener(View::onTouchEvent);


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int[] point =new int[2];
        imageview.getLocationOnScreen(point);
        System.out.println("The Screen : x: "+point[0]+" y: "+point[1]);
        float touchX, touchY,imageX, imageY, touchNewX, touchNewY;
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                BackupBitmap =cloneBitmap(bitmap);
                path = new Path();
                Roundpath =new Path();
                canvasNew= new Canvas(BackupBitmap);

                if(Shapes== 5)
                {
                    paint.setColor(Color.WHITE);
 
                    paint.setStyle(Paint.Style.FILL);

                }
               else{
                   if(rdStroke.isChecked() || rdStrokeandFill.isChecked())
                   {
                       if(paint.getColor() == Color.WHITE )
                       {
                           paint.setColor(Color.BLACK);
                       }
                       paint.setStyle(Paint.Style.STROKE);
                   }


                }

                touchX =  event.getRawX();
                touchY = event.getRawY();
                touchNewX =  event.getX();
                touchNewY = event.getY();
                imageX = touchX - point[0]; // viewCoords[0] is the X coordinate
                imageY = touchY - point[1];
              //  imageX = touchX ; // viewCoords[0] is the X coordinate
               // imageY = touchY;
                System.out.println("The Screen : x_down: "+touchX+"y_down: "+touchY);
                System.out.println("The Screen : x_down_New: "+touchNewX+" y_down_New: "+touchNewY);
                System.out.println("The Screen : x_down: "+imageX+"y_down: "+imageY);
                //path.moveTo(touchX,touchY);
               // startX=imageX;
                //startY=imageY;
                switch (Shapes) {
                    case 0: break;
                    case 1:
                        intLineStartX=imageX;

                        intLineStartY=imageY;
                        break;
                    case 2:
                        intCircleStartX=imageX;
                        intCircleStartY=imageY;
                        break;
                    case 3:
                        intRectangleStartX=imageX;
                        intRectangleStartY=imageY;
                        distanceX=0;
                        distanceY=0;
                        break;
                    case 4:
                        intTriangleStartX=imageX;
                        intTriangleStartY=imageY;
                        distancerightX=0;
                        distanceTriangleUpY=0;
                        distanceTriangleDownY=0;
                        distanceleftX=0;
                        break;
                    case 5:
                        ErasepointX=imageX;
                        ErasepointY=imageY;

                        break;
                    case 6:
                         break;
                }

               // startX=touchX;
               // startY=touchX;
                path.moveTo(imageX,imageY);
                canvas.drawPath(path, paint);



                // canvas.drawBitmap(bitmap,event.getX(),event.getY(),paint);

                //imageview.setImageBitmap(bitmap);
                break;
            case MotionEvent.ACTION_MOVE:
                touchX =  event.getRawX();
                touchY =  event.getRawY();
                imageX = touchX - point[0]; // viewCoords[0] is the X coordinate
                imageY = touchY - point[1];
                //imageX = touchX ; // viewCoords[0] is the X coordinate
                //imageY = touchY;

                path.lineTo(imageX,imageY);
                canvas.drawPath(path, paint);
                canvas.drawPath(Roundpath, paint);
                imageview.setImageBitmap(bitmap);
                switch (Shapes) {
                    case 1:
                        break;
                    case 2:
                        getDistanceforCircle(imageX,imageY);
                        break;
                    case 3:
                        if(distanceX<Math.abs(intRectangleStartX-imageX))
                        {
                            intRectangleEndX = imageX;
                            distanceX = (int)Math.abs(intRectangleStartX-imageX);
                        }
                        if(distanceY<Math.abs(intRectangleStartY-imageY))
                        {
                            intRectangleEndY = imageY;
                            distanceY= (int)Math.abs(intRectangleStartY-imageY);
                        }
                        break;
                    case 4:
                        // drawTriangle(event.getRawX(),event.getRawY());
                        if(distanceleftX<(intTriangleStartX-imageX))
                        {
                            intTriangleleftX = imageX;
                            distanceleftX = (intTriangleStartX-imageX);
                        }
                        if(distanceTriangleUpY<(intTriangleStartY-imageY))
                        {
                            intTriangleUpY = imageY;
                            distanceTriangleUpY= (intTriangleStartY-imageY);
                        }
                        if(distanceTriangleDownY>(intTriangleStartY-imageY))
                        {
                            intTriangleDownY = imageY;
                            distanceTriangleDownY= (intTriangleStartY-imageY);
                        }
                        if(distancerightX>(intTriangleStartX-imageX))
                        {
                            intTrianglerightX = imageX;
                            distancerightX= (intTriangleStartX-imageX);
                        }
                        break;
                    case 5:
                        ErasepointX=imageX;
                        ErasepointY=imageY;
                        Roundpath.addCircle(ErasepointX,ErasepointY,50,Path.Direction.CW);
                        break;
                }
                break;
            case MotionEvent.ACTION_UP:


                canvas.drawColor(Color.WHITE);


               this.canvas.drawBitmap(this.BackupBitmap, 0, 0, null);
                touchX =  event.getRawX();
                touchY = event.getRawY();
                paths.add(path);

              //  paint.setStyle(Paint.Style.FILL);

               imageX = touchX - point[0]; // viewCoords[0] is the X coordinate
                imageY = touchY - point[1];
               // imageX = touchX ; // viewCoords[0] is the X coordinate
                //imageY = touchY ;
                intLineEndX=imageX;
                intLineEndY=imageY;

                DesignShapes(Shapes);

        }

        return true;
    }

    public void DesignShapes(int intShape)
    {
        if(rdStrokeandFill.isChecked())
        {
        paint.setStyle(Paint.Style.FILL);
        }

        switch (intShape) {
            case 0:
                canvas.drawPath(path, paint);
                imageview.invalidate();
                break;
            case 1:
                //canvas.drawColor(Color.WHITE);
                canvas.drawLine(intLineStartX, intLineStartY, intLineEndX, intLineEndY, paint);
                imageview.invalidate();

                break;
            case 2:
                canvas.drawCircle(centerCircleX, centerCircleY, distanceGlobal / 2, paint);
                imageview.invalidate();
                distanceGlobal = 0;
                break;
            case 3:
                canvas.drawRect(intRectangleStartX, intRectangleStartY, intRectangleEndX, intRectangleEndY, paint);
                imageview.invalidate();
                break;
            case 4:
                if(rdStrokeandFill.isChecked())
                {
                    Path path = new Path();

                    paint.setStyle(Paint.Style.FILL_AND_STROKE);

                    path.setFillType(Path.FillType.EVEN_ODD);
                    if(Math.abs(distanceTriangleDownY)<Math.abs(distanceTriangleUpY))
                    {
                        path.moveTo(intTriangleStartX,intTriangleStartY);
                        path.lineTo(intTriangleleftX,intTriangleUpY);
                        path.lineTo(intTrianglerightX,intTriangleUpY);
                        path.lineTo(intTriangleStartX,intTriangleStartY);
                        path.close();
                        canvas.drawPath(path, paint);
                    }
                    else
                    {
                        path.moveTo(intTriangleStartX,intTriangleStartY);
                        path.lineTo(intTriangleleftX,intTriangleDownY);
                        path.lineTo(intTrianglerightX,intTriangleDownY);
                        path.lineTo(intTriangleStartX,intTriangleStartY);
                        path.close();
                        canvas.drawPath(path, paint);
                    }

                }
                else
                {
                    if(Math.abs(distanceTriangleDownY)<Math.abs(distanceTriangleUpY))
                    {
                        canvas.drawLine(intTriangleStartX, intTriangleStartY, intTriangleleftX, intTriangleUpY, paint);
                        canvas.drawLine(intTriangleleftX, intTriangleUpY, intTrianglerightX, intTriangleUpY, paint);
                        canvas.drawLine(intTrianglerightX, intTriangleUpY, intTriangleStartX, intTriangleStartY, paint);
                    }
                    else
                    {
                        canvas.drawLine(intTriangleStartX, intTriangleStartY, intTriangleleftX, intTriangleDownY, paint);
                        canvas.drawLine(intTriangleleftX, intTriangleDownY, intTrianglerightX, intTriangleDownY, paint);
                        canvas.drawLine(intTrianglerightX, intTriangleDownY, intTriangleStartX, intTriangleStartY, paint);
                    }
                }

                imageview.invalidate();
                break;
            case 5:
               canvas.drawPath(Roundpath, paint);
                imageview.invalidate();
                break;
            case 6:
                break;
        }
    }
    private void getDistanceforCircle(float x,float y)
    {
        float distance = (float) Math.sqrt(((x-intCircleStartX)*(x-intCircleStartX))+((y-intCircleStartY)*(y-intCircleStartY)));
        if(distance > distanceGlobal)
        {
            distanceGlobal=distance;
            centerCircleX=((x+intCircleStartX)/2);
            centerCircleY=((y+intCircleStartY)/2);
        }
    }
    public static Bitmap cloneBitmap(Bitmap bitmap) {
        return bitmap.copy(bitmap.getConfig(),true);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        if(item.equals("Freehand"))
        {
            Shapes=0;
            alertDialog(
                    "\n" +
                    "1. User can select the color from the Color Pallet, Default Color : Black\n\n" +
                    "2. User can Select the paint style to be Stroke or Fill, Default : Stroke .\n\n" +
                    "3. Select the Option from the Dropdown Menu, Default : Freehand\n\n" +
                    "   a. Lines, To draw Lines\n "+
                    "  b. Circle, To draw Circle\n"+
                    "   c. Rectangle, To draw Rectangles \n"+
                    "   d. Triangle, To draw Triangles \n\n"+
                    " The Edit option: \n\n"+
                    "   a. Erase, To erase the canvas requested on touch of the user \n"+
                    "   b. Undo, To erase the previous object \n\n"+
                    "4. Click on CLEAR at any point of time to clear the canvas.\n" +

                    "\n" +
                    "\n The app is a Geometric Drawing using Finger Gesture so Enjoy Painting :) \n"
                   ,"Instruction");
        }
        if(item.equals("Line"))
        {
            Shapes=1;
        }
        if(item.equals("Circle"))
        {
            Shapes=2;
        }
        if(item.equals("Rectangle"))
        {
            Shapes=3;
        }
        if(item.equals("Triangle"))
        {
            Shapes=4;
        }
        if(item.equals("Erase"))
        {
            Shapes=5;
            //paint.setStyle(Paint.Style.FILL);

            //GeometricDesign.setSelection(design.indexOf("Freehand"));

            //imageview.invalidate();
        }
        if(item.equals("Undo"))
        {
            Shapes=6;

            //GeometricDesign.setSelection(design.indexOf("Freehand"));

            //imageview.invalidate();
            canvas.drawColor(Color.WHITE);
            this.canvas.drawBitmap(this.BackupBitmap, 0, 0, null);
            imageview.invalidate();

        }


        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void alertDialog(String msg, String Title) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(msg);
        dialog.setTitle(Title);
        dialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {

                        //Toast.makeText(getApplicationContext(), "OK is clicked", Toast.LENGTH_LONG).show();
                    }
                });

        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
        dialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
    }
}
