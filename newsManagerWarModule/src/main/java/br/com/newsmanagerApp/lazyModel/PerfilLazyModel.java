package br.com.newsmanagerApp.lazyModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.newsmanagerApp.vo.PerfilVO;


public class PerfilLazyModel extends LazyDataModel<PerfilVO> implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<PerfilVO> datasource;
    
    public PerfilLazyModel(List<PerfilVO> datasource) {
        this.datasource = datasource;
    }
     
    @Override
    public PerfilVO getRowData(String rowKey) {
        for(PerfilVO p : datasource) {
            if(p.getId().toString().equals(rowKey))
                return p;
        }
 
        return null;
    }
 
    @Override
    public Object getRowKey(PerfilVO p) {
        return p.getId();
    }
 
    @Override
    public List<PerfilVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<PerfilVO> data = new ArrayList<PerfilVO>();
 
        //filter
        for(PerfilVO p : datasource) {
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = p.getNome();
 
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
                data.add(p);
            }
        }
 
        //sort
        if(sortField != null) {
            Collections.sort(data, new PerfilLazySorter(sortField, sortOrder));
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
