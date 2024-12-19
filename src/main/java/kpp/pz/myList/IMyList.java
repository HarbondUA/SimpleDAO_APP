package kpp.pz.myList;

public interface IMyList<T> extends Iterable<T> {
    void add(T o);
    void clear();
    boolean remove(T o);
    public T get(int index);
    public void set(int index, T element);
    Object[] toArray();
    int size();
    boolean contains(T o);
    String toString();
}
