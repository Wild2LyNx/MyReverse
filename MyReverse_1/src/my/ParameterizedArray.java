package my;

import java.lang.reflect.Array;

public class ParameterizedArray<T> {
	private T[][] array;
	  @SuppressWarnings("unchecked")
	  public ParameterizedArray(Class<T> type, int... dimensions) {
	    array = (T[][])Array.newInstance(type, dimensions);
	  }
	  public void put(int index1, int index2, T item) {
	    array[index1][index2] = item;
	  }
	  public T get(int index1, int index2) { return array[index1][index2]; }
	  
	  public T[][] rep() { return array; }	
	  
//	  to show array: Cell[][] cells = arr.rep();	  

}
