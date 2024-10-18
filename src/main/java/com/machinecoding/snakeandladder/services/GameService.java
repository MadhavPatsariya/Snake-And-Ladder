package com.machinecoding.snakeandladder.services;

import com.machinecoding.snakeandladder.models.Ladder;
import com.machinecoding.snakeandladder.models.Snake;
import com.machinecoding.snakeandladder.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class GameService {
    private final LadderService ladderService;
    private final SnakeService snakeService;

    @Autowired
    public GameService(LadderService ladderService, SnakeService snakeService) {
        this.ladderService = ladderService;
        this.snakeService = snakeService;
    }

    List<User> usersList = new ArrayList<>();
    char[] board = new char[101];
    Random random = new Random();

    public boolean addSnakes(List<Snake> snakes) {
        snakes.forEach(it -> board[it.getStart()] = 'S');
        return snakeService.addSnakes(snakes);
    }

    public boolean addLadders(List<Ladder> ladders) {
        ladders.forEach(it -> board[it.getStart()] = 'L');
        return ladderService.addLadders(ladders);
    }

    public List<User> addUsers(List<String> userNames) {
        List<User> users = userNames.stream().map(User::new).toList();
        usersList.addAll(users);
        return usersList;
    }

    public int rollDiceAndMove(UUID userId) throws Exception {
        boolean isUserPresent = usersList.stream().anyMatch(it -> it.getId().equals(userId));
        if(!isUserPresent) {
            throw new Exception("User does not exists");
        }
        else{
            User currentUser = usersList.stream().filter(it -> it.getId().equals(userId)).findAny().get();
            int numberOnDice = rollDice();
            int intermediatePosition = currentUser.getCurrentPosition() + numberOnDice;
            if(board[intermediatePosition] == 'L') {
                int ladderEnd = ladderService.getEndFromStart(intermediatePosition);
                currentUser.setCurrentPosition(ladderEnd);
            }
            if(board[intermediatePosition] == 'S') {
                int snakeEnd = snakeService.getEndFromStart(intermediatePosition);
                currentUser.setCurrentPosition(snakeEnd);
            }
            if(board[intermediatePosition] == '.') {
                currentUser.setCurrentPosition(intermediatePosition);
            }
            return currentUser.getCurrentPosition();
        }
    }

    private int rollDice() {
        return random.nextInt(6) + 1;
    }
}
