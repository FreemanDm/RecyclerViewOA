package com.freeman.recyclerviewoa.recyclerviewoa;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Freeman on 24.01.2017.
 */

public class MyOnItemTouchListener implements RecyclerView.OnItemTouchListener {

    ClickListener clickListener;
    GestureDetector gestureDetector;
    RecyclerView recyclerView;

    public MyOnItemTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
        this.clickListener = clickListener;
        this.recyclerView = recyclerView;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (view != null && clickListener != null){
                    clickListener.onLongClick(view, recyclerView.getChildPosition(view));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View view = rv.findChildViewUnder(e.getX(), e.getY());
        boolean resultGestureDetector = gestureDetector.onTouchEvent(e);

        if (view != null && clickListener != null && resultGestureDetector){
            clickListener.onClick(view, rv.getChildPosition(view));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public interface ClickListener{
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }
}
