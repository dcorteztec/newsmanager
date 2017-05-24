package br.com.newsmanagerApp.lazyModel;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import br.com.newsmanagerApp.vo.PerfilVO;

public class PerfilLazySorter implements Comparator<PerfilVO> {
 
    private String sortField;
     
    private SortOrder sortOrder;
     
    public PerfilLazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }
 
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public int compare(PerfilVO p1, PerfilVO p2) {
        try {
            Object value1 = PerfilVO.class.getField(this.sortField).get(p1);
            Object value2 = PerfilVO.class.getField(this.sortField).get(p2);
 
			int value = ((Comparable)value1).compareTo(value2);
             
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}

