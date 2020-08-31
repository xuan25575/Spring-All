package com.springboot.demo.chart;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class GroupChatClient extends Thread {

    private SocketChannel socketChannel;
    private Selector selector;
    private static final int PORT = 6667;
    private String username;

    public GroupChatClient() {
        try {
            selector = Selector.open();
//            socketChannel = SocketChannel.open();
//            doConnect();
            socketChannel = SocketChannel.open(new InetSocketAddress("192.168.199.119",PORT));
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            username = socketChannel.getLocalAddress().toString();
            System.out.println(username + " is ok...");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doConnect() throws IOException{
        if(socketChannel.connect(new InetSocketAddress("192.168.199.119",PORT))){
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            username = socketChannel.getLocalAddress().toString();
            System.out.println(username + " is ok...");
        }
    }

    @Override
    public void run() {
        while (true){
            readInfo();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    //向服务器发送消息
    public void sendData(String msg){
        msg = username +"说："+ msg;

        try {
            byte[] bytes = msg.getBytes();
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            buffer.put(bytes);
            buffer.flip();
            socketChannel.write(buffer);
            //可以一行代码解决
            //socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读取服务端的消息
    public void readInfo(){
        try {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while(iterator.hasNext()){
                SelectionKey key = iterator.next();
                if(key.isReadable()){
                    SocketChannel sc  = (SocketChannel) key.channel();
                    ByteBuffer buffer  = ByteBuffer.allocate(1024);
                    sc.read(buffer);
//                    buffer.flip();
//                    byte[] bytes = new byte[1024];
//                    buffer.get(bytes);
//                    String info = new String(bytes,"utf-8");

                    //一行代码解决
                    String info = new String(buffer.array());
                    System.out.println(info.trim());
                }
            }
            iterator.remove();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
