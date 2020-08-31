package com.springboot.demo.chart;

import javafx.util.Pair;
import org.apache.tomcat.util.buf.ByteBufferUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class GroupChatServer  extends Thread{

    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT = 6667;


    public GroupChatServer() {
        try {
            selector =  Selector.open();
            listenChannel = ServerSocketChannel.open();
            //设置非阻塞
            listenChannel.configureBlocking(false);
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            //注册通道
            listenChannel.register(selector,SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        while (true){
            try {
                //会一直阻塞到有一个通道在你注册的事件上就绪了
                int select = selector.select();
                if(select >0){
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while(iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        // 监听连接
                        if(key.isAcceptable()){
                            //ServerSocketChannel listenChannel = (ServerSocketChannel) key.channel();
                            SocketChannel sc = listenChannel.accept();
                            sc.configureBlocking(false);
                            sc.register(selector,SelectionKey.OP_READ);
                            System.out.println(sc.getRemoteAddress() + " 上线 ");
                        }
                        //可读的。
                        if(key.isReadable()){
                            readData(key);
                            int num =onlineNum(selector);
                            System.out.println("在线用户:"+num);
                        }
                        //当前的 key 删除，防止重复处理
                        iterator.remove();
                    }
                } else {
                    System.out.println("等待中.....");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //TODO 要是能检测下线，就不用这么统计了
    public static int onlineNum(Selector selector) {
        int res = 0;
        for(SelectionKey key : selector.keys()) {
            Channel targetChannel = key.channel();
            if(targetChannel instanceof SocketChannel) {
                res++;
            }
        }
        return res;
    }


    private void readData(SelectionKey key)  {
        SocketChannel sc = null;
        try{
            sc = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int read = sc.read(buffer);
            if(read > 0){
                //切换读写
                buffer.flip();
                byte[] bytes = new byte[buffer.remaining()];
                //将缓冲区的数据读到bytes数组
                buffer.get(bytes);
                String body = new String(bytes, "UTF-8");
                //String msg = new String(buffer.array());
                System.out.println("from 客户端信息:"+ body);
                sendOtherClients(body,sc);
            }else{// 只能在这里处理下线了。

                SocketAddress ip = sc.getLocalAddress();
                sc.close();
                key.cancel(); // Some JDK implementations run into an infinite loop without this.
                System.out.println("客户端"+ ip +"下线了");
            }

        }catch (IOException e){
            // 不能走到异常来。
            e.printStackTrace();
            // 出现异常下线了
            try {
                SocketAddress ip = sc.getLocalAddress();
                System.out.println(ip.toString() +"下线了");
                //取消key
                key.cancel();
                //关闭通道
                sc.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void sendOtherClients(String msg,SocketChannel self) throws IOException {
        System.out.println("服务器转发消息中...");
        //keys() 获取所有的连接
        Set<SelectionKey> selectionKeys = selector.keys();

        for (SelectionKey selectionKey : selectionKeys) {
            Channel channel = selectionKey.channel();
            if(channel instanceof SocketChannel &&  channel != self){
                SocketChannel other = (SocketChannel)channel;
                other.write(ByteBuffer.wrap(msg.getBytes()));
            }
        }
    }

    public static void main(String[] args) {
        GroupChatServer server = new GroupChatServer();
        server.start();
    }
}
