package com.example.chris.myapplication.ui.widgit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：
 * 创建人 Chris
 * 创建时间：2017/12/14 14:27
 */

public class Kbview extends View {

    private Paint paint;
    private float wight;
    private float height;
    private List<LinePoint> linePointsH;
    private List<LinePoint> linePointsV;
    private List<PointF> pointFList;

    public Kbview(Context context) {
        this(context,null);
    }

    public Kbview(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Kbview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        wight = getMeasuredWidth();
        height = getMeasuredHeight();
        linePointsH = measureHLine();
        linePointsV = measureVLine();
        pointFList = measureText();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x=event.getX();
                float y=event.getY();
                int locationNum = getLocationNum(x, y);
                listener.getNum(locationNum);
                break;
        }
        return super.onTouchEvent(event);
    }


    private int getLocationNum(float x,float y) {
        for (int i = 0; i < pointFList.size(); i++) {
            if((x<(i%3)*(wight/3)+wight/3)&&(y<(i/3)*(height/4)+height/4)) {
                return i;
            }
        }
        return -1;
    }

    private List<PointF> measureText() {
        List<PointF> pointFList=new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            PointF pointF=new PointF();
            pointF.set(wight/6+(i%3)*(wight/3),height/8+(i/3)*(height/4));
            pointFList.add(pointF);
        }
        return pointFList;
    }

    private List<LinePoint> measureHLine() {
        List<LinePoint> hlist=new ArrayList<>();
        for (float i = 0; i < 4; i++) {
            LinePoint linePoint=new LinePoint();
            PointF point1=new PointF();
            point1.set(0,(height/4)*i);

            PointF point2=new PointF();
            point2.set(wight,(height/4)*i);
            linePoint.setPoints(point1,point2);
            hlist.add(linePoint);
        }
        return hlist;
    }

    private List<LinePoint> measureVLine() {
        List<LinePoint> vlist=new ArrayList<>();
        for (float i = 0; i < 2; i++) {
            LinePoint linePoint=new LinePoint();
            PointF point1=new PointF();
            point1.set(wight*((i+1)/3),0);

            PointF point2=new PointF();
            point2.set(wight*((i+1)/3),height);
            linePoint.setPoints(point1,point2);
            vlist.add(linePoint);
        }
        return vlist;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLines(canvas,linePointsH);
        drawLines(canvas,linePointsV);
        drawTexts(canvas,pointFList);
    }

    private void drawTexts(Canvas canvas, List<PointF> pointFList) {
        for (int i = 0; i < pointFList.size(); i++) {
            canvas.drawText(i+"",pointFList.get(i).x,pointFList.get(i).y,paint);
        }
    }

    private void drawLines(Canvas canvas,List<LinePoint> linePoints) {
        for (LinePoint linePoint : linePoints) {
            PointF[] points = linePoint.getPoints();
            canvas.drawLine(points[0].x,points[0].y,points[1].x,points[1].y,paint);
        }
    }

    class LinePoint {
        PointF[] points=new PointF[2];

        public void setPoints(PointF point1,PointF point2) {
            points[0]=point1;
            points[1]=point2;
        }

        public PointF[] getPoints() {
            return points;
        }
    }

    public interface NumListener{
        void getNum(int num);
    }

    private NumListener listener;

    public void setNumListener(NumListener listener) {
        this.listener=listener;
    }
}
