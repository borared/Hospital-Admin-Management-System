package adminmangementsystem.com.management;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractManagementSystem<T> {
    
    protected List<T> records = new ArrayList<>();
    
    public boolean isEmpty() {
        return records.isEmpty();
    }
    
    public int getCount() {
        return records.size();
    }
    
    public List<T> getAll() {
        return new ArrayList<>(records);
    }
    
    public abstract void displayAll();
}
