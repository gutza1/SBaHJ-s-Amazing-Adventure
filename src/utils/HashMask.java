package utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashMask implements Iterable<Point> {
	
  private Point[] hashList;
  private int size;
  
  public HashMask() {
	  hashList = new Point[4];
	  size = 0;
  }
  
  public HashMask(int capacity) {
	  hashList = new Point[capacity];
	  size = 0;
  }
  
  private void resize(int newSize) {
	  Point[] newList = new Point[newSize];
	  for (int i = 0; i < hashList.length; i++) {
		if (hashList[i] != null) {
			newList[hashList[i].hashCode() % newSize] = hashList[i];
		}
	  }
	  hashList = newList;
  }
  
  public boolean contains(Point point) {
	  int currentPlace = point.hashCode() % hashList.length;
	  while (true) {
		  if (hashList[currentPlace] == null) {
			  return false;
		  }
		  else if (!hashList[currentPlace].equals(point)) {
			  if (currentPlace == hashList.length - 1) {
				  currentPlace = 0;
			  }
			  else {
				  currentPlace++;
			  }
		  }
		  else {
			  return true;
		  }
	  }
  }
  
  public void add(Point point) {
	  int currentPlace = point.hashCode() % hashList.length;
	  while (true) {
		  if (hashList[currentPlace] == null) {
			  if (size == hashList.length) {
				  resize(hashList.length * 2);
			  }
			  hashList[currentPlace] = point;
			  size++;
			  return;
		  }
		  else if (!hashList[currentPlace].equals(point)) {
			  if (currentPlace == hashList.length - 1) {
				  currentPlace = 0;
			  }
			  else {
				  currentPlace++;
			  }
		  }
		  else {
			  return;
		  }
	  }
  }
  
  public boolean remove(Point point) {
	  int currentPlace = point.hashCode() % hashList.length;
	  while (true) {
		  if (hashList[currentPlace] == null) {
			  return false;
		  }
		  else if (!hashList[currentPlace].equals(point)) {
			  if (currentPlace == hashList.length - 1) {
				  currentPlace = 0;
			  }
			  else {
				  currentPlace++;
			  }
		  }
		  else {
			  if (size <= hashList.length / 4) {
				  resize(size / 2);
			  }
			  hashList[currentPlace] = null;
			  size--;
			  return true;
		  }
	  }
  }

  @Override
  public Iterator<Point> iterator() {
	// TODO Auto-generated method stub
	return new ListIterator();
  }
  
  private class ListIterator implements Iterator<Point> {
	  private int current;
	  
	  public ListIterator() {
          current = 0;
      }

      public boolean hasNext()  { return current != hashList.length;                     }
      public void remove()      { throw new UnsupportedOperationException();  }

      public Point next() {
          if (!hasNext()) throw new NoSuchElementException();
          Point item = hashList[current];
          current++; 
          return item;
      }
  }

}
