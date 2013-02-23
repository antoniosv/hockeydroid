package com.edu4java.android.killthemall;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView {

	private Bitmap bmp;
	private Bitmap bmo;
	private SurfaceHolder holder;
	private GameLoopThread gameLoopThread;
	private int x, y, xx, yy;
	private int xSpeed, ySpeed;
	public int xg, yg;
	public int nx=0, ny=0, px=356, py=173, t1, t2;

	public GameView(Context context) {
		super(context);
		gameLoopThread = new GameLoopThread(this);
		holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
			
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {}
			
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				gameLoopThread.setRunning(true);
                gameLoopThread.start();
				
			}
			
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width,
					int height) {}
		});
        	
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ojo);
        bmo = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);

	}
	
	
	@Override
    public boolean onTouchEvent(MotionEvent event) {
		
		xx=x-(bmp.getHeight()/2);
		yy=y-(bmp.getHeight()/2);
		xg=(int) event.getX();
		yg=(int) event.getY();
		if (xg>x && xg<(x+bmp.getWidth()) && yg>y && yg<(y+bmp.getHeight())){
			t1 = ((xg-xx)<0)?(xx-xg):(xg-xx);
			t2 = ((yg-yy)<0)?(yy+yg):(yg-yy);
			
		}
                 
          
          return super.onTouchEvent(event);
    }
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		nx=-(t1);
		ny=-(t2);
		px=(t1);
		py=(t2);
		 if (x >= getWidth() - bmp.getWidth()) {
             xSpeed = nx; 
      }
      if (x <= 0) {
             xSpeed = px;
      }
      
      
      if (y >= getHeight() - bmp.getHeight()) {
    	  ySpeed = ny;
      }
      if (y <= 0){
    	  ySpeed = py;
      }
      
      if (t1>0){
    	  t1=t1-1;}
      
      if (t2>0){
    	  t2=t2-1;}

      
      y = y + ySpeed;
      x = x + xSpeed;
      canvas.drawBitmap(bmo, 0, 0, null);
      
    		  canvas.drawBitmap(bmp, x, y, null);
	}

}
