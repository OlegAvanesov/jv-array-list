package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double ARRAY_LENGTH_MULTIPLIER = 1.5;
    private int size;
    private Object[] majorArray;
    private T removedElement;

    public ArrayList() {
        majorArray = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == majorArray.length) {
            increasingMajorArray();
        }
        majorArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is outside the array");
        }
        if (size + 1 >= majorArray.length) {
            increasingMajorArray();
        }
        System.arraycopy(majorArray, index, majorArray, index + 1, size - index);
        majorArray[index] = value;
        size++;

    }

    @Override
    public void addAll(List<T> list) {
        for (int k = 0; k < list.size(); k++) {
            if (size + list.size() == majorArray.length) {
                increasingMajorArray();
            }
            majorArray[k + size] = list.get(k);
        }
        size = size + list.size();
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) majorArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        majorArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        removedElement = (T) majorArray[index];
        System.arraycopy(majorArray, index + 1, majorArray, index, size - index - 1);
        size--;
        majorArray[size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == majorArray[i] || element != null && element.equals(majorArray[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element in ArrayList");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] increasingMajorArray() {
        return majorArray =
                Arrays.copyOf(majorArray, (int) (majorArray.length * ARRAY_LENGTH_MULTIPLIER));
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is outside the array");
        }
    }
}
