package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double ARRAY_LENGTH_MULTIPLIER = 1.5;
    private int size;
    private Object[] majorArray;
    private T[] oldArray;
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
        } else {
            if (size + 1 >= majorArray.length) {
                increasingMajorArray();
            }
            System.arraycopy(majorArray, index, majorArray, index + 1, size - index);
            majorArray[index] = value;
            size++;
        }
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
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is outside the array");
        } else {
            return (T) majorArray[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is outside the array");
        } else {
            majorArray[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is outside the array");
        } else {
            removedElement = (T) majorArray[index];
            System.arraycopy(majorArray, index + 1, majorArray, index, size - index - 1);
            size--;
            majorArray[size] = null;
            return removedElement;
        }
    }

    @Override
    public T remove(T element) {
        removedElement = element;
        for (int i = 0; i < size; i++) {
            if (element == majorArray[i] || element != null && element.equals(majorArray[i])) {
                System.arraycopy(majorArray, i + 1, majorArray, i, size - i - 1);
                majorArray[size] = null;
                size--;
                return removedElement;
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
        oldArray = (T[]) majorArray;
        majorArray = new Object[(int) (majorArray.length * ARRAY_LENGTH_MULTIPLIER)];
        System.arraycopy(oldArray, 0, majorArray, 0, oldArray.length);
        return majorArray;
    }
}
