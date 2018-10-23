package com.example.edu.myimagescale;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
//이미지 화면 늘렸다 줄였다
public class MyImageView extends View {
    private Drawable image;
    private ScaleGestureDetector gestureDetector;
    private  float scale =1.0f;


    public MyImageView(Context context){
        super(context);
        image = context.getDrawable(R.drawable.dog2);
        setFocusable(true);
        image.setBounds(0,0,image.getIntrinsicWidth(),image.getIntrinsicHeight());
        gestureDetector = new ScaleGestureDetector(context, new ScaleGestureDetector.SimpleOnScaleGestureListener(){
            //손가락 눌렀을때 어떻게 정의 할꺼냐 데이터는 detector
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                scale *= detector.getScaleFactor();//비율 스케일 값
                if(scale < 0.1f) scale = 0.1f;
                if(scale > 10.0f) scale = 10.0f;
                return true;
            }
        });
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(scale, scale);
        image.draw(canvas);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);

        return true;
    }
}
