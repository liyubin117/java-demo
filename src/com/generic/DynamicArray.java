package com.generic;

import java.util.Arrays;

public class DynamicArray<E> {

	private static final int DEFAULT_CAPACITY = 10;

	private int size;
	private Object[] elementData;

	public DynamicArray() {
		this.elementData = new Object[DEFAULT_CAPACITY];
	}

	private void ensureCapacity(int minCapacity) {
		int oldCapacity = elementData.length;
		if (oldCapacity >= minCapacity) {
			return;
		}
		int newCapacity = oldCapacity * 2;
		if (newCapacity < minCapacity)
			newCapacity = minCapacity;
		elementData = Arrays.copyOf(elementData, newCapacity);
	}

	public void add(E e) {
		ensureCapacity(size + 1);
		elementData[size++] = e;
	}

	@SuppressWarnings("unchecked")
	public E get(int index) {
		return (E) elementData[index];
	}

	public int size() {
		return size;
	}

	public E set(int index, E element) {
		E oldValue = get(index);
		elementData[index] = element;
		return oldValue;
	}
	
	public <T extends E> void addAll(DynamicArray<T> c) {
	    for(int i=0; i<c.size; i++){
	        add(c.get(i));
	    }
	}
	
	//<? extends E> 读，可以由<T extends E> DynamicArray(T)代替
	public void addAll2(DynamicArray<? extends E> c) {
	    for(int i=0; i<c.size; i++){
	        add(c.get(i));
	    }
	}
	
	//<? super E> 写
	public void copyTo(DynamicArray<? super E> dest){
	    for(int i=0; i<size; i++){
	        dest.add(get(i));
	    }
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DynamicArray<Number> numbers = new DynamicArray<>();
		DynamicArray<Integer> ints = new DynamicArray<>();
		ints.add(100);
		ints.add(34);
		numbers.addAll(ints);
		numbers.addAll2(ints);
		ints.copyTo(numbers);
		
		for(int i=0;i<numbers.size;i++){
			System.out.print(numbers.get(i)+" ");
		}

	}

}
