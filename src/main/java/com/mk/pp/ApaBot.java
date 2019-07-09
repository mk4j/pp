package com.mk.pp;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class ApaBot extends TelegramLongPollingBot {

    static ApaBot instance = null;

    protected ApaBot(){

    }

    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        sendMsg(update.getMessage().getChatId().toString(), message);
    }

    public static ApaBot getBot(){
        if(instance == null){
            instance = new ApaBot();
        }

        return instance;
    }

    public synchronized void sendMsg(String chatId, String s){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return "mkapa_bot";
    }

    public String getBotToken() {
        return "AAGF-gblEr0Zkr-9Ms7iy0CStfQj6CZFig4";
    }
}
