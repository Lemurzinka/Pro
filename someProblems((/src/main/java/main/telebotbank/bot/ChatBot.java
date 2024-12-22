package main.telebotbank.bot;

import main.telebotbank.model.Users;
import main.telebotbank.service.UsersService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@PropertySource("classpath:telegram.properties")
public class ChatBot extends TelegramLongPollingBot {

    private static final Logger LOGGER = LogManager.getLogger(ChatBot.class);


    @Value("${bot.name}")
private  String botName;

    @Value("${bot.token}")
    private String botToken;

    private final UsersService usersService;


    public ChatBot(UsersService usersService) {this.usersService = usersService;}

    @Override
    public String getBotUsername() {return botName;}

    @Override
    public String getBotToken() {return botToken;}

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (!update.hasMessage() || !update.getMessage().hasText()) return;

            final String text = update.getMessage().getText();
            final long chatId = update.getMessage().getChatId();

            Users user = usersService.findByTelegramId(chatId);
            BotContext context;
            BotState state;

            if (user == null) {
                state = BotState.getInitialState();

                user = new Users(chatId, state.ordinal());
                usersService.addUser(user);

                context = BotContext.of(this, user, text, usersService);
                state.enter(context);

            } else {
                context = BotContext.of(this, user, text, usersService);
                state = BotState.byId(user.getStateId());
            }
            state.handleInput(context);

            do {
                state = state.nextState();
                state.enter(context);
            } while (!state.isInputNeeded());

            user.setStateId(state.ordinal());
            usersService.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

