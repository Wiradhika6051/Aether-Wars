package com.aetherwars.controller;

import com.aetherwars.event.BoardChannel;
import com.aetherwars.event.Event;
import com.aetherwars.event.Publisher;
import com.aetherwars.event.Subscriber;
import com.aetherwars.view.Frame;

public class BoardController implements Subscriber, Publisher {
    private BoardChannel channel;
    Frame gui;
    public BoardController(BoardChannel board,Frame gui){
        this.channel = board;
        this.gui = gui;
    }
    @Override
    public void publish(Event event) {

    }

    @Override
    public void onEvent(Event event) {

    }
}
