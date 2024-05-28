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
                    view.setScaleX(1);
                } else if (item.getWireType() == 1){
                    view.setImageResource(R.drawable.wire_left_bottom);
                    view.setScaleX(1);
                } else if (item.getWireType() == 2){
                    view.setImageResource(R.drawable.wire_left_bottom);
                    view.setScaleX(-1);
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
                return;
            case "And":
                if (!item.getElement().getInputElements().isEmpty() && item.getElement().getInputElements().get(0).isActive()){
                    view.setImageResource(R.drawable.and_top_true);
                } else {
                    view.setImageResource(R.drawable.and_top_false);
                }
                if (item.getElement().getInputElements().size() == 2 && item.getElement().getInputElements().get(1).isActive()){
                    map.getElementView(item.getId()+2).setImageResource(R.drawable.bottom_in_true);
                } else {
                    map.getElementView(item.getId()+2).setImageResource(R.drawable.bottom_in_false);
                }
                map.getElementView(item.getId()+1).setImageResource(item.getElement().isActive() ? R.drawable.body_out_true : R.drawable.body_out_false);
                return;
            case "Or":
                if (!item.getElement().getInputElements().isEmpty() && item.getElement().getInputElements().get(0).isActive()){
                    view.setImageResource(R.drawable.or_top_true);
                } else {
                    view.setImageResource(R.drawable.or_top_false);
                }
                if (item.getElement().getInputElements().size() == 2 && item.getElement().getInputElements().get(1).isActive()){
                    map.getElementView(item.getId()+2).setImageResource(R.drawable.bottom_in_true);
                } else {
                    map.getElementView(item.getId()+2).setImageResource(R.drawable.bottom_in_false);
                }
                map.getElementView(item.getId()+1).setImageResource(item.getElement().isActive() ? R.drawable.body_out_true : R.drawable.body_out_false);
                return;
            case "NotAnd":
                if (!item.getElement().getInputElements().isEmpty() && item.getElement().getInputElements().get(0).isActive()){
                    view.setImageResource(R.drawable.and_top_true);
                } else {
                    view.setImageResource(R.drawable.and_top_false);
                }
                if (item.getElement().getInputElements().size() == 2 && item.getElement().getInputElements().get(1).isActive()){
                    map.getElementView(item.getId()+2).setImageResource(R.drawable.bottom_in_true);
                } else {
                    map.getElementView(item.getId()+2).setImageResource(R.drawable.bottom_in_false);
                }
                map.getElementView(item.getId()+1).setImageResource(item.getElement().isActive() ? R.drawable.body_not_out_false : R.drawable.body_not_out_true);
                return;
            case "NotOr":
                if (!item.getElement().getInputElements().isEmpty() && item.getElement().getInputElements().get(0).isActive()){
                    view.setImageResource(R.drawable.or_top_true);
                } else {
                    view.setImageResource(R.drawable.or_top_false);
                }
                if (item.getElement().getInputElements().size() == 2 && item.getElement().getInputElements().get(1).isActive()){
                    map.getElementView(item.getId()+2).setImageResource(R.drawable.bottom_in_true);
                } else {
                    map.getElementView(item.getId()+2).setImageResource(R.drawable.bottom_in_false);
                }
                map.getElementView(item.getId()+1).setImageResource(item.getElement().isActive() ? R.drawable.body_not_out_false : R.drawable.body_not_out_true);
                return;
        }
    }
}
