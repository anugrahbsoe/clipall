/**
* This file is part of dev.utils.
*
* dev.utils is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* You should have received a copy of the GNU General Public License
* along with dev.utils.  If not, see <http://www.gnu.org/licenses/>.
*
* File      : Reversed.java
* Created   : Jan 9, 2010, 10:01:53 PM
*/

package dev.utils.collections;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Erol Hira
 */
public class Reversed<T> implements Iterable<T> {

    private final List<T> original;

    public Reversed(List<T> original) {
        this.original = original;
    }

    public Iterator<T> iterator() {
        
        final ListIterator<T> i = original.listIterator(original.size());

        return new Iterator<T>() {

            public boolean hasNext() {
                return i.hasPrevious();
            }

            public T next() {
                return i.previous();
            }

            public void remove() {
                i.remove();
            }
        };
    }

    public static <T> Reversed<T> reversed(List<T> original) {
        return new Reversed<T>(original);
    }
}

