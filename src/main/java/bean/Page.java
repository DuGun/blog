package bean;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Page <T>{



    //总记录数
    private long total;

    //总页数
    private long pages;

    //结果集
    private List<T> list;

    public Page(){
        super();
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
