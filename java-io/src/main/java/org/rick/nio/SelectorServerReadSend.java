package org.rick.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Set;

public class SelectorServerReadSend {

	public static void main(String[] args) throws IOException {
		int[] ports = { 9000, 9001, 9002, 9003 };
		for (int i = 0; i < ports.length; i++) {
			new Thread(new ServerThread(ports[i])).start();
		}
	}
}

class ServerThread implements Runnable {
	private volatile boolean stop;
	private ServerSocketChannel serverChannel;
	private ServerSocket serverSocket;
	private Selector selector;
	private String body;
	private byte[] bytes;

	public ServerThread(int port) {
		try {
			serverChannel = ServerSocketChannel.open();
			serverChannel.configureBlocking(false);
			serverSocket = serverChannel.socket();
			serverSocket.bind(new InetSocketAddress(port));
			selector = Selector.open();
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("服务器正在" + port + "端口监听。。");
		} catch (ClosedChannelException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			System.exit(1);
		}
	}

	@Override
	public void run() {
		while (!stop) {
			try {
				selector.select(1000);
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				for (SelectionKey key : selectedKeys) {
					handleInput(key);
					if(key!=null){
						key.cancel();
						if(key.channel()!=null){
							key.channel().close();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(selector!=null){
			try {
				selector.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void handleInput(SelectionKey key) throws IOException {
		if (key.isValid()) {
			if (key.isAcceptable()) {
				ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
				SocketChannel sc = ssc.accept();
				sc.configureBlocking(false);
				sc.register(selector, SelectionKey.OP_READ);
			}
			if (key.isReadable()) {
				SocketChannel sc = (SocketChannel) key.channel();
				ByteBuffer buf = ByteBuffer.allocate(1024);
				int readBytes = sc.read(buf);
				if (readBytes > 0) {
					buf.flip();
					bytes = new byte[buf.remaining()];
					buf.get(bytes);
					body = new String(bytes, "GBK");
					System.out.println("<系统消息>接收到客户端命令" + body);
					if ("STOP".equals(body)) {
						stop();
					} else {
						handleWrite(sc, body);
					}
				} else if (readBytes < 0) {
					// 对端链路关闭
					key.cancel();
					sc.close();
				} else {
					;// 读到0字节，忽略
				}

			}

		}
	}

	private void handleWrite(SocketChannel socketChannel, String c) throws IOException {
		if(c!=null&&c.trim().length()>0){
			ByteBuffer buf = ByteBuffer.allocate(1024);
			body = "DATE".equals(c) ? new Date(System.currentTimeMillis()).toString() : "wrong command!";
			buf.put(body.getBytes());
			buf.flip();
			socketChannel.write(buf);
			buf.position(0);
			byte[] bytes = new byte[buf.remaining()];
			buf.get(bytes);
			String out = new String(bytes);
			System.out.println("<系统消息>回复客户端成功！回复消息为：" + out);
		}

	}

	public void stop() {
		this.stop = true;
	}
}
