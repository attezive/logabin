package com.example.logabin.utils;

import android.graphics.Color;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.logabin.R;
import com.example.logabin.adapter.EditMapAdapter;
import com.example.logabin.model.MapElementItem;
import com.example.logabin.scheme.Scheme;

import java.util.List;

public class DrawableController {
    private EditMapAdapter map;
    public DrawableController(EditMapAdapter map){
        this.map = map;
    }

    public void updateMap(){
        List<MapElementItem> list = map.getList();
        List<ImageView> imageViews = map.getMap();
        for (int i = 0; i < list.size() && i < imageViews.size(); i++){
            updateElement(list.get(i), imageViews.get(i));
        }
    }

    private void updateElement(MapElementItem item, ImageView view){
        if (item.getElement() == null) return;
        switch (item.getElement().getName()){
            case "Wire":
                if (view.getTag() == null){
                    view.setTag(R.drawable.empty);
                }
                if (item.getWireType() == 0){
                    view.setImageResource(R.drawable.wire_line_horizontal);
                } else if (item.getWireType() == 1){
                    view.setImageResource(R.drawable.wire_left_bottom);
                } else {
                    view.setImageResource(R.drawable.wire_crosshair);
                }
                view.setColorFilter(item.getElement().isActive() ? Color.GREEN : Color.RED);
                return;
            case "InputChannel":
                if (view.getTag() == null){
                    view.setTag(R.drawable.empty);
                    view.setImageResource(R.drawable.input);
                }
                view.setColorFilter(item.getElement().isActive() ? Color.GREEN : Color.RED);
                return;
            case "OutputChannel":
                if (view.getTag() == null){
                    view.setTag(R.drawable.empty);
                    view.setImageResource(R.drawable.output);
                }
                view.setColorFilter(item.getElement().isActive() ? Color.GREEN : Color.RED);
                return;
            case "Not":
                view.setImageResource(item.getElement().isActive() ? R.drawable.not_false : R.drawable.not_true);
        }
    }
}
