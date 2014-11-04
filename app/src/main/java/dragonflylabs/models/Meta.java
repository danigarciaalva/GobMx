package dragonflylabs.models;

public class Meta {

    private Integer limit;
    private Object next;
    private int offset;
    private Object previous;
    private Integer total_count;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Object getNext() {
        return next;
    }

    public void setNext(Object next) {
        this.next = next;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Object getPrevious() {
        return previous;
    }

    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    public Integer getTotalCount() {
        return total_count;
    }

    public void setTotalCount(Integer totalCount) {
        this.total_count = totalCount;
    }

}