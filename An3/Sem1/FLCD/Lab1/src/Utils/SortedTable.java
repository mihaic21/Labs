package Utils;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by shobo on 09.11.2014.
 */
public class SortedTable<T extends Comparable<T>> {

    private LinkedList<T> list = new LinkedList<T>();

    public T addElement(T elem) {

        if (this.list.contains(elem)) {
            return elem;
        } else {
            Integer index = -1;

            Iterator<T> it = this.list.iterator();

            Boolean added = false;

            if (!it.hasNext()) {
                this.list.add(elem);
                added = true;
            } else {
                while (it.hasNext()) {
                    T currElem = it.next();
                    index++;

                    if (currElem.compareTo(elem) > 0) {
                        this.list.add(index, elem);
                        added = true;
                        break;
                    }
                }
            }

            if (!added) {
                this.list.add(elem);
            }

            return null;
        }
    }

    public T findElement(T elem) {
        Iterator<T> it = this.list.iterator();

        while (it.hasNext()) {
            T currElem = it.next();

            if (currElem.equals(elem)) {
                return currElem;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return this.list.toString();
    }
}
