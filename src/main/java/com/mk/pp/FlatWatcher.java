package com.mk.pp;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.io.IOException;


public class FlatWatcher
{
    static String kom = "https://www.avito.ru/sankt-peterburg/kvartiry/sdam/na_dlitelnyy_srok?user=1&s_trg=4&metro=168";

    public static void main(String[] args) throws IOException, InterruptedException{
        Watcher komWatcher = new Watcher(kom);


        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(ApaBot.getBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

        komWatcher.monitor();



    }
}
