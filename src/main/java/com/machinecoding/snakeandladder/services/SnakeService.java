package com.machinecoding.snakeandladder.services;

import com.machinecoding.snakeandladder.models.Snake;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SnakeService {

    List<Snake> snakeList = new ArrayList<>();

    public boolean addSnakes(List<Snake> snakes) {
        return snakeList.addAll(snakes);
    }

    public int getEndFromStart(int start) {
        return snakeList.stream().filter(it -> it.getStart() == start).findFirst().get().getEnd();
    }

}
