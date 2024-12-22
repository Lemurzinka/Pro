package main.telebotbank.bot;

import main.telebotbank.model.Users;
import main.telebotbank.service.UsersService;

public class BotContext {
    private final ChatBot bot;
    private final Users user;
    private final String input;
    private final UsersService usersService;

    public static BotContext of(ChatBot bot, Users user, String text, UsersService usersService) {
        return new BotContext(bot, user, text, usersService);
    }



    private BotContext(ChatBot bot, Users user, String input, UsersService usersService) {
        this.bot = bot;
        this.user = user;
        this.input = input;
        this.usersService = usersService;
    }


    public ChatBot getBot() {
        return bot;
    }

    public Users getUser() {
        return user;
    }

    public String getInput() {
        return input;
    }

    public UsersService getUsersService() {
        return usersService;
    }
}
