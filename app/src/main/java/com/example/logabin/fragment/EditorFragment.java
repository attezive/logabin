package com.example.logabin.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.logabin.R;
import com.example.logabin.adapter.EditMapAdapter;
import com.example.logabin.db.model.ElementModel;
import com.example.logabin.element.InputChannel;
import com.example.logabin.element.OutputChannel;
import com.example.logabin.element.many.to.many.Wire;
import com.example.logabin.element.many.to.one.And;
import com.example.logabin.element.one.to.one.Not;
import com.example.logabin.model.MapElementItem;
import com.example.logabin.scheme.Scheme;
import com.example.logabin.utils.ConnectionController;
import com.example.logabin.utils.Coordinate;
import com.example.logabin.utils.DrawableController;
import com.example.logabin.utils.InteractionController;

import java.util.ArrayList;
import java.util.List;

public class EditorFragment extends Fragment {
    private LinearLayout infoMenu;
    private LinearLayout actionMenu;
    private MapElementItem currentItem;
    private EditMapAdapter editMapAdapter;
    private int xSize;
    private int ySize;
    private int currentResize;
    private boolean isChangedInfo;
    private ImageView btnPositive;
    private ImageView btnNegative;
    private ImageView btnConfirm;
    private ImageView btnCancel;
    private ImageView btnRefresh;
    private ImageView btnInfo;
    private TextView infoField;
    private static ElementModel elementModel;
    private RecyclerView editMapRV;
    private Scheme scheme;
    private DrawableController drawableController;

    public EditorFragment() {
        xSize = 5;
        ySize = 4;
        currentResize = 0;
        currentItem = null;
        elementModel = null;
        isChangedInfo = false;
        scheme = new Scheme();
    }

    public static void setCurrentElementModel(ElementModel element){
        elementModel = element;
    }

    @Override
    public void onResume(){
        super.onResume();
        if (elementModel != null){
            actionMenu.setVisibility(View.GONE);
            infoMenu.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_editor, container, false);

        infoMenu = root.findViewById(R.id.info_menu);
        btnConfirm = root.findViewById(R.id.btn_confirm);
        btnCancel = root.findViewById(R.id.btn_cancel);
        btnRefresh = root.findViewById(R.id.btn_refresh);
        btnInfo = root.findViewById(R.id.btn_info);
        infoField = root.findViewById(R.id.info_field);

        actionMenu = root.findViewById(R.id.action_menu);
        btnPositive = root.findViewById(R.id.btn_positive);
        btnNegative = root.findViewById(R.id.btn_negative);
        ImageView btnStart = root.findViewById(R.id.btn_start);
        ImageView btnSave = root.findViewById(R.id.btn_save);
        ImageView btnResize = root.findViewById(R.id.btn_resize);

        editMapRV = root.findViewById(R.id.edit_map);


        editMapRV.setLayoutManager(new GridLayoutManager(getContext(), ySize,
                GridLayoutManager.HORIZONTAL, false));

        editMapAdapter = new EditMapAdapter(this, xSize, ySize);
        for (int i = 0; i < xSize*ySize; i++)
            editMapAdapter.Add(new MapElementItem(i));
        editMapAdapter.updateIds();
        editMapRV.setAdapter(editMapAdapter);

        drawableController = new DrawableController(editMapAdapter);

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentItem.getElement() != null){
                    isChangedInfo = !isChangedInfo;
                    updateInfoButtons();
                }
            }
        });

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isChangedInfo){
                    ImageView view = editMapAdapter.getElementView(currentItem.getId());
                    view.setRotation(view.getRotation()+90);
                    for (Coordinate coordinate : currentItem.getElement().getOutputCoordinates())
                        coordinate.angleRotate();
                }
                else {
                    if (currentItem.getElement().getName().equals("Wire")){
                        currentItem.setWireType((currentItem.getWireType()+1)%3);
                        List<Coordinate> coordinates = new ArrayList<>();
                        if (currentItem.getWireType()==0){
                            coordinates.add(currentItem.getElement().getOutputCoordinates().get(0));
                            currentItem.getElement().setOutputCount(1);
                        } else if (currentItem.getWireType()==1){
                            coordinates.add(currentItem.getCoordinate().sum(1, 0));
                            coordinates.add(currentItem.getCoordinate().sum(0, -1));
                            coordinates.get(0).setShift(1);
                            coordinates.get(1).setShift(2);
                            currentItem.getElement().setOutputCount(2);
                        } else {
                            coordinates.add(currentItem.getCoordinate().sum(0, 1));
                            coordinates.add(currentItem.getElement().getOutputCoordinates().get(0));
                            coordinates.add(currentItem.getCoordinate().sum(-1, 0));
                            currentItem.getElement().setOutputCount(3);
                        }
                        currentItem.getElement().setOutputCoordinates(coordinates);
                        drawableController.updateMap();
                    }
                }
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isChangedInfo){
                    if (elementModel != null){
                        setElement();
                        infoField.setText(elementModel.name);
                        elementModel = null;
                    }
                    editMapAdapter.getElementView(currentItem.getId()).setBackgroundResource(android.R.color.transparent);
                    infoMenu.setVisibility(View.GONE);
                    actionMenu.setVisibility(View.VISIBLE);
                } else {
                    currentItem.getElement().setInputCount(currentItem.getElement().getInputCount()+1);
                    updateInfo();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isChangedInfo){
                    if (elementModel == null){
                        currentItem.setElement(null);
                        editMapAdapter.getElementView(currentItem.getId()).setImageResource(R.drawable.empty);
                        editMapAdapter.getElementView(currentItem.getId()).clearColorFilter();
                    } else {
                        elementModel = null;
                    }
                    infoMenu.setVisibility(View.GONE);
                    actionMenu.setVisibility(View.VISIBLE);
                    editMapAdapter.getElementView(currentItem.getId()).setBackgroundResource(android.R.color.transparent);
                } else {
                    if (currentItem.getElement().getInputCount() > 1){
                        currentItem.getElement().setInputCount(currentItem.getElement().getInputCount()-1);
                        updateInfo();
                    }
                }
            }
        });


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread emulate = new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                ConnectionController.instance().updateAll(scheme);
                                InteractionController.instance().updateInteractions(scheme);
                            }
                        }
                );
                emulate.setDaemon(true);

                emulate.start();

                drawableController.updateMap();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawableController.updateMap();
                Log.d("My", elementModel.toString());
            }
        });

        btnResize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentResize = (currentResize + 1) % 3;
                updateActionButtons();
            }
        });

        infoField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentItem.getElement().getName().equals("InputChannel") && isChangedInfo){
                    currentItem.getElement().setActive(!currentItem.getElement().isActive());
                    drawableController.updateMap();
                }
            }
        });

        btnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentResize == 0){
                    editMapAdapter.addNewYLine(1);
                    GridLayoutManager gridLayoutManager = (GridLayoutManager) editMapRV.getLayoutManager();
                    gridLayoutManager.setSpanCount(editMapAdapter.getYSize());
                } else if (currentResize == 1) {
                    editMapAdapter.addNewXLine(1);
                } else {
                    editMapRV.offsetChildrenVertical(100);
                }
            }
        });

        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentResize == 0){
                    editMapAdapter.addNewYLine(-1);
                    GridLayoutManager gridLayoutManager = (GridLayoutManager) editMapRV.getLayoutManager();
                    gridLayoutManager.setSpanCount(editMapAdapter.getYSize());
                } else if (currentResize == 1) {
                    editMapAdapter.addNewXLine(-1);
                } else {

                    editMapRV.offsetChildrenVertical(-100);
                }
            }
        });

        editMapRV.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_CANCEL ||
                        event.getAction() == MotionEvent.ACTION_UP){
                    editMapAdapter.getElementView(0).setBackgroundResource(R.drawable.current_selection);
                    editMapAdapter.getElementView(0).setBackgroundResource(android.R.color.transparent);
                }
                return false;
            }
        });

        return root;
    }

    public void updateElement(MapElementItem item){
        if (currentItem != null){
            editMapAdapter.getElementView(currentItem.getId()).setBackgroundResource(android.R.color.transparent);
        }
        if (currentItem == null || !currentItem.equals(item)){
            actionMenu.setVisibility(View.GONE);
            infoMenu.setVisibility(View.VISIBLE);
            currentItem = item;
            editMapAdapter.getElementView(currentItem.getId()).setBackgroundResource(R.drawable.current_selection);
        } else {
            infoMenu.setVisibility(View.GONE);
            actionMenu.setVisibility(View.VISIBLE);
            currentItem = null;
        }
        isChangedInfo = false;
        updateInfoButtons();
    }

    private void updateActionButtons(){
        if (currentResize == 0){
            btnPositive.setImageResource(R.drawable.bottom);
            btnNegative.setImageResource(R.drawable.top);
        } else if (currentResize == 1) {
            btnPositive.setImageResource(R.drawable.left);
            btnNegative.setImageResource(R.drawable.right);
        } else {
            btnPositive.setImageResource(R.drawable.plus);
            btnNegative.setImageResource(R.drawable.minus);
        }
    }

    private void updateInfoButtons(){
        if (isChangedInfo){
            btnConfirm.setImageResource(R.drawable.plus);
            btnCancel.setImageResource(R.drawable.minus);
        } else {
            btnConfirm.setImageResource(R.drawable.confirm);
            btnCancel.setImageResource(R.drawable.cancel);
        }
        updateInfo();
    }

    private void updateInfo(){
        if (isChangedInfo){
            if (!currentItem.getElement().getName().equals("InputChannel")){
                infoField.setText("Input count: " + currentItem.getElement().getInputCount() +
                        ",\nOutput count: " + currentItem.getElement().getOutputCount());
            } else {
                infoField.setText("SWAP");
            }
        } else {
            infoField.setText("");
        }
    }

    private void setElement(){
        switch (elementModel.name){
            case "Wire":
                currentItem.setElement(new Wire(currentItem.getId(), elementModel.name,
                        1, 1, currentItem.getCoordinate()));
                scheme.add(currentItem.getElement());
                editMapAdapter.getElementView(currentItem.getId()).setImageResource(R.drawable.wire_line_horizontal);
                return;
            case "Not":
                currentItem.setElement(new Not(currentItem.getId(), elementModel.name, currentItem.getCoordinate()));
                scheme.add(currentItem.getElement());
                editMapAdapter.getElementView(currentItem.getId()).setImageResource(R.drawable.not_false);
                return;
            case "InputChannel":
                currentItem.setElement(new InputChannel(currentItem.getId(), elementModel.name, 1, currentItem.getCoordinate()));
                scheme.add(currentItem.getElement());
                editMapAdapter.getElementView(currentItem.getId()).setImageResource(R.drawable.input);
                return;
            case "OutputChannel":
                currentItem.setElement(new OutputChannel(currentItem.getId(), elementModel.name, 1, currentItem.getCoordinate()));
                scheme.add(currentItem.getElement());
                editMapAdapter.getElementView(currentItem.getId()).setImageResource(R.drawable.output);
                return;
        }
    }
}