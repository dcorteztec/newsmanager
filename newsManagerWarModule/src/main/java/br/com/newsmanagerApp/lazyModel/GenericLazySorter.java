package br.com.newsmanagerApp.lazyModel;

import java.lang.reflect.Field;
import java.util.Comparator;

import org.primefaces.model.SortOrder;

import br.com.newsmanagerApp.vo.PerfilVO;

public class GenericLazySorter<T> implements Comparator<T> {
 
    private String sortField;
    
    private SortOrder sortOrder;
     
    public GenericLazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }
 
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public int compare(T p1, T p2) {
        try {
        	Class<?> clazz = p1.getClass();

    	    Field field = clazz.getDeclaredField(this.sortField);
    	    field.setAccessible(true);
    	    System.out.println(field.get(p1));
            String fieldValue = field.get(p1).toString();
            String fieldValue2 = field.get(p2).toString();
        	 Object value1 = fieldValue;
             Object value2 = fieldValue2;
			int value = ((Comparable)value1).compareTo(value2);
             
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}

