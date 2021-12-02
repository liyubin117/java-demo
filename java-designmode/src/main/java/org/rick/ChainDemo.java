package org.rick;

public class ChainDemo {
	//创建不同类型的记录器。赋予它们不同的错误级别，并在每个记录器中设置下一个记录器。每个记录器中的下一个记录器代表的是链的一部分。
	private static AbstractLogger getChainOfLoggers(){

	      AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
	      AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
	      AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

	      errorLogger.setNextLogger(fileLogger);
	      fileLogger.setNextLogger(consoleLogger);

	      return errorLogger;	
	   }

	   public static void main(String[] args) {
	      AbstractLogger loggerChain = getChainOfLoggers();

	      loggerChain.logMessage(AbstractLogger.INFO, 
	         "This is an information.");
	      System.out.println("--------------");
	      loggerChain.logMessage(AbstractLogger.DEBUG, 
	         "This is an debug level information.");
	      System.out.println("--------------");
	      loggerChain.logMessage(AbstractLogger.ERROR, 
	         "This is an error information.");
	   }
}

//抽象的记录器类
abstract class AbstractLogger {
	   public static int INFO = 1;
	   public static int DEBUG = 2;
	   public static int ERROR = 3;

	   protected int level;

	   //责任链中的下一个元素
	   protected AbstractLogger nextLogger;

	   public void setNextLogger(AbstractLogger nextLogger){
	      this.nextLogger = nextLogger;
	   }

	   public void logMessage(int level, String message){
	      if(this.level <= level){
	         write(message);
	      }
	      if(nextLogger !=null){
	         nextLogger.logMessage(level, message);
	      }
	   }

	   abstract protected void write(String message);
		
	}

//扩展了该抽象记录器类的实体类
class ConsoleLogger extends AbstractLogger {

	   public ConsoleLogger(int level){
	      this.level = level;
	   }

	   @Override
	   protected void write(String message) {		
	      System.out.println("Standard Console::Logger: " + message);
	   }
	}

class ErrorLogger extends AbstractLogger {

	   public ErrorLogger(int level){
	      this.level = level;
	   }

	   @Override
	   protected void write(String message) {		
	      System.out.println("Error Console::Logger: " + message);
	   }
	}

class FileLogger extends AbstractLogger {

	   public FileLogger(int level){
	      this.level = level;
	   }

	   @Override
	   protected void write(String message) {		
	      System.out.println("File::Logger: " + message);
	   }
	}
