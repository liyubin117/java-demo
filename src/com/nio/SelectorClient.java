package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.Set;

public class SelectorClient {

	public static void main(String[] args) {
		System.out.print("请输入命令：");
		Scanner scanner=new Scanner(System.in);
		String command=scanner.nextLine();
		new Thread(new ClientThread("127.0.0.1", 9001, command)).start();
	}
	
}

class ClientThread implements Runnable {
	private Selector selector;
	private SocketChannel socketChannel;
	private String host;
	private int port;
	private String command;
	private volatile boolean stop;

	public ClientThread(String host, int port, String command) {
		try {
			this.socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			socketChannel.socket().setReuseAddress(true);
			socketChannel.socket().setReceiveBufferSize(1024);;
			socketChannel.socket().setSendBufferSize(1024);;
			this.selector = Selector.open();
			this.host = host;
			this.port = port;
			this.command = command;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void stop() {
		this.stop = true;
	}

	@Override
	public void run() {
		try {
			doConnect();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		while (!stop) {
			try {
				selector.select(1000);
				Set<SelectionKey> keys = selector.selectedKeys();
				for (SelectionKey key : keys) {
					try {
						handleInput(key);
					} catch (Exception e) {
						if (key != null) {
							key.cancel();
							if (key.channel() != null) {
								key.channel().close();
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.print(1);
			}
		}
		if(selector!=null){
			try{
				selector.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	private void handleInput(SelectionKey key) throws IOException {
		if (key.isValid()) {
			SocketChannel sc = (SocketChannel) key.channel();
			if (key.isConnectable()) {
				if (sc.finishConnect()) {
					sc.register(selector, SelectionKey.OP_READ);
					doWrite(sc);
				} else {
					System.exit(1);
				}
			}
			if (key.isReadable()) {
				ByteBuffer readBuffer = ByteBuffer.allocate(1024);
				int readBytes = sc.read(readBuffer);
				if (readBytes > 0) {
					readBuffer.flip();
					byte[] bytes = new byte[readBuffer.remaining()];
					readBuffer.get(bytes);
					String receive = new String(bytes, "GBK");
					System.out.println("Now is:" + receive);
					stop();
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

	private void doConnect() throws IOException {
		// 如果直接连接成功，则注册到多路复用器上，发送请求信息，读应答
		if (socketChannel.connect(new InetSocketAddress(this.host, this.port))) {
			socketChannel.register(this.selector, SelectionKey.OP_READ);
			doWrite(socketChannel);
		} else {
			socketChannel.register(this.selector, SelectionKey.OP_CONNECT);
		}
	}

	private void doWrite(SocketChannel socketChannel) {
		ByteBuffer buf = ByteBuffer.allocate(1024);
		try {
			buf.put(command.getBytes());
			buf.flip();
			socketChannel.write(buf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!buf.hasRemaining()) {
			System.out.println("<系统命令>发送命令" + command + "成功！");
		}

	}

}
