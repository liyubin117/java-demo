package org.rick.designmodel;
//策略模式
public class StrategyDemo {
	public static void main(String[] args) {
		Context context = new Context(new OperationAdd());
		System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

		context = new Context(new OperationSubstract());
		System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

		context = new Context(new OperationMultiply());
		System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
	}
}

// 接口
interface Strategy {
	public int doOperation(int num1, int num2);
}

// 实现接口的实体类
class OperationAdd implements Strategy {
	@Override
	public int doOperation(int num1, int num2) {
		return num1 + num2;
	}
}

class OperationSubstract implements Strategy {
	@Override
	public int doOperation(int num1, int num2) {
		return num1 - num2;
	}
}

class OperationMultiply implements Strategy {
	@Override
	public int doOperation(int num1, int num2) {
		return num1 * num2;
	}
}

// 并装、选择策略的Context类
class Context {
	private Strategy strategy;

	public Context(Strategy strategy) {
		this.strategy = strategy;
	}

	public int executeStrategy(int num1, int num2) {
		return strategy.doOperation(num1, num2);
	}
}