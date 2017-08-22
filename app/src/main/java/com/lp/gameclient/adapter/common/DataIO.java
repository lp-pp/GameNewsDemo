package com.lp.gameclient.adapter.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LP on 2017/8/21/19:57.
 */

public interface DataIO<T> {

    void add(T elem);

    void addAt(int location, T elem);

    void addAll(List<T> elements);

    void addAllAt(int location, List<T> elements);

    void remove(T elem);

    void removeAll(List<T> elements);

    void removeAt(int index);

    void clear();

    void replace(T oldElem, T newElem);

    void replaceAll(List<T> elements)

    void replaceAt(int index, T elem);

    ArrayList<T> getAll();

    T get(int position);

    int getSize();

    boolean contains(T elem);

    void onEmptyData();

    void onHasData();
}
