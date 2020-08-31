package com.springboot.demo.chart;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        GroupChatClient chatClient = new GroupChatClient();
        chatClient.start();


        //发送数据给服务器端
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            chatClient.sendData(s);
        }
    }
}
