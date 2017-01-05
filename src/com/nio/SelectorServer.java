package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Set;

public class SelectorServer {

	public static void main(String[] args) throws IOException {
		Selector selector = Selector.open();
		int[] ports = { 8000, 8001, 8002, 8003 }; // 监听五个接口
		for (int i = 0; i < ports.length; i++) {
			ServerSocketChannel initChannel = ServerSocketChannel.open(); // 打开一个通道
			initChannel.configureBlocking(false); // 通道配置为非阻塞
			ServerSocket initSocket = initChannel.socket(); // 获得通道对应的ServerSocket
			initSocket.bind(new InetSocketAddress(ports[i])); // 绑定端口
			initChannel.register(selector, SelectionKey.OP_ACCEPT); // 等待连接
			System.out.println("服务器正在" + ports[i] + "端口监听。。");
		}

		// 接收全部生成的key，并通过连接进行判断是否获取客户端的输出
		int keysAdd = 0;
		String nowDate;
		while ((keysAdd = selector.select()) > 0) {
			Set<SelectionKey> selectedKeys = selector.selectedKeys(); // 取出全部生成的key
			for (SelectionKey key : selectedKeys) {
				if (key.isAcceptable()) {
					ServerSocketChannel server = (ServerSocketChannel) key.channel(); // 取出key对应的通道
					SocketChannel client = server.accept(); // 接收新连接
					client.configureBlocking(false); // 设置非阻塞
					ByteBuffer buff = ByteBuffer.allocate(1024);
					nowDate = (new Date(System.currentTimeMillis())).toString();
					buff.put(nowDate.getBytes());
					buff.flip();
					client.write(buff);
					client.close(); // 关闭
				}
			}
			selectedKeys.clear(); // 清除全部的key
		}

	}

}
