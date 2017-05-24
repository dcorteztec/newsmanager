package br.com.newsmanagerApp.lazyModel;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;


public class GenericLazyModel<T> extends LazyDataModel<T> implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<T> datasource;
    
    public GenericLazyModel(List<T> datasource) {
        this.datasource = datasource;
    }
     
    @Override
    public T getRowData(String rowKey) {
        for(T t : datasource) {
            try {
            	 Class<?> clazz = t.getClass();

            	    Field field = clazz.getDeclaredField("id");
            	    field.setAccessible(true);
            	    System.out.println(field.get(t));
				if(field.get(t).toString().equals(rowKey))
				    return t;
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
        }
 
        return null;
    }
 
    @Override
    public Object getRowKey(T t) {
        try {
        	Class<?> clazz = t.getClass();

    	    Field field = clazz.getDeclaredField("id");
    	    field.setAccessible(true);
    	    System.out.println(field.get(t));
			return field.get(t);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
    }
 
    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<T> data = new ArrayList<T>();
 
        //filter
        for(T t : datasource) {
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        Class<?> clazz = t.getClass();

                	    Field field = clazz.getDeclaredField(filterProperty);
                	    field.setAccessible(true);
                	    System.out.println(field.get(t));
                        String fieldValue = field.get(t).toString();
 
                        if(filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                    }
                    else {
                            match = false;
                            break;
                        }
                    } catch(Exception e) {
                        match = false;
                    }
                }
            }
 
            if(match) {
                data.add(t);
            }
        }
 
        //sort
        if(sortField != null) {
            Collections.sort(data, new GenericLazySorter<T>(sortField, sortOrder));
        }
 
        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);
 
        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }
}
