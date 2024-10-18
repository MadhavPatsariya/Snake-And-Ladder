package com.machinecoding.snakeandladder.controllers;

import com.machinecoding.snakeandladder.models.Ladder;
import com.machinecoding.snakeandladder.models.Snake;
import com.machinecoding.snakeandladder.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.machinecoding.snakeandladder.services.GameService;
import java.util.UUID;
import java.util.List;


@RestController
@RequestMapping("/api/v1/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/create-snakes")
    public boolean createSnakes(
            @RequestBody List<Snake> snakes
    ) {
        return gameService.addSnakes(snakes);
    }

    @PostMapping("/create-ladders")
    public boolean createLadders(
            @RequestBody List<Ladder> ladders
    ) {
        return gameService.addLadders(ladders);
    }

    @PostMapping("/create-users")
    public List<User> createUsers(
            @RequestBody List<String> users
    ) {
        return gameService.addUsers(users);
    }

    @PostMapping("/roll-dice-move/{userId}")
    public int rollDiceAndMove(
            @PathVariable("userId") UUID userId
    ) throws Exception {
        return gameService.rollDiceAndMove(userId);
    }

    @GetMapping("/test")
    public String testApi() {
        return "success";
    }
}
