package com.machinecoding.snakeandladder.services;

import com.machinecoding.snakeandladder.models.Ladder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LadderService {

    List<Ladder> laddersList = new ArrayList<>();

    public boolean addLadders(List<Ladder> ladders) {
        return laddersList.addAll(ladders);
    }

    public int getEndFromStart(int start) {
        return laddersList.stream().filter(it -> it.getStart() == start).findFirst().get().getEnd();
    }
}
