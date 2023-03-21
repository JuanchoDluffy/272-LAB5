import java.util.*;

public class DoublyLinkedListDummy {
  private DIntNode head;
  private DIntNode tail;

  public DoublyLinkedListDummy() {
    head = new DIntNode();
    tail = new DIntNode();
    head.setNext(tail);
    tail.setPrev(head);
  }

  public DIntNode getHead() {
    return head.getNext();
  }

  public DIntNode getTail() {
    return tail.getPrev();
  }

  public void setHead(DIntNode node) {
    head.setNext(node);
  }

  public void setTail(DIntNode node) {
    tail.setPrev(node);
  }

  /*
   * creates a new node that contains element.
   * adds an elemts to the end of the list.
   * the space-time complexity for this method is O(1)
   */
  public void addEnd(int element) {
    DIntNode addnode = new DIntNode(element);
    tail.getPrev().setNext(addnode);
    addnode.setNext(tail);
    addnode.setPrev(tail.getPrev());
    tail.setPrev(addnode);
  }

  /*
   * removes the first node of the list
   * the space-time complexty for this method is O(1)
   */
  public void removeFromHead() {
    if ((head.getNext()) != tail) {
      head.getNext().getNext().setPrev(head);
      head.setNext(head.getNext().getNext());
    }
  }

  /*
   * Returns a string representation of a doubly linked list that contains two
   * lines:
   * one line with the list's elements in order from the beginning to the end, and
   * the second line with the list's elements in reverse order (from end to
   * beginning).
   * 
   * Time complexity: O(n), where n is the number of elements in the list.
   */
  @Override
  public String toString() {
    String fordw = "";
    String back = "";
    DIntNode curr = this.getHead();
    while (curr != this.tail) {
      fordw += String.valueOf(curr.getData());
      if (curr.getNext() != tail) {
        fordw += "<->";
      }
      curr = curr.getNext();
    }
    curr = this.getTail();
    while (curr != head) {
      back += String.valueOf(curr.getData());
      if (curr.getPrev() != head) {
        back += "<->";
      }
      curr = curr.getPrev();
    }
    return "(Forward) " + fordw + "\n" + "(Backward) " + back;
  }

  /*
   * Returns the number of occurrences of a given element in a doubly linked list.
   * 
   * Time complexity: O(n), where n is the number of elements in the list.
   */
  public int countOccurrence(int e) {
    int count = 0;
    for (DIntNode curr = getHead(); curr != tail; curr = curr.getNext()) {
      if (curr.getData() == e) {
        count++;
      }
    }

    return count;
  }

  /*
   * Removes all occurrences of a given element from a doubly linked list.
   * Returns false if there are no occurrences of the element.
   * 
   * Time complexity: O(n), where n is the number of elements in the list.
   * 
   */
  public boolean removeAll(int e) {
    int contained = 0;
    for (DIntNode curr = getHead(); curr != tail; curr = curr.getNext()) {
      if (curr.getData() == e) {
        curr.getPrev().setNext(curr.getNext());
        curr.getNext().setPrev(curr.getPrev());
        contained++;
      }
    }

    if (contained == 0) {
      return false;
    }
    return true;
  }

  /*
   * Removes all duplicate elements from a doubly linked list.
   * return A new DoublyLinkedListDummy object with duplicate elements removed.
   * 
   * Time complexity: O(n), where n is the number of elements in the list.
   */
  public DoublyLinkedListDummy removeDuplicates() {
    HashSet<Integer> occurrences = new HashSet<>();
    DoublyLinkedListDummy purified = this;
    for (DIntNode curr = getHead(); curr != tail; curr = curr.getNext()) {
      if (!occurrences.contains(curr.getData())) {
        occurrences.add(curr.getData());
      } else {
        curr.getPrev().setNext(curr.getNext());
        curr.getNext().setPrev(curr.getPrev());
      }

    }
    return purified;
  }

  /*
   * Prints out all pairs of integers in a doubly linked list whose sum is equal
   * to a given value y.
   * If no matching pairs are found, the method prints out "No matches found".
   * 
   * Time complexity: O(n), where n is the number of elements in the list.
   */
  public static void pairs(DoublyLinkedListDummy l2, int y) {
    String answer = "";
    DIntNode first = l2.getHead();
    DIntNode second = l2.getTail();

    while (second.getNext() != first && first != second) {
      if ((first.getData() + second.getData()) == y) {
        answer += "(" + first.getData() + "," + second.getData() + ")" + ",";
        first = first.getNext();
        second = second.getPrev();
      } else {
        if ((first.getData() + second.getData()) < y) {
          first = first.getNext();
        } else {
          second = second.getPrev();
        }
      }
    }
    if (answer.equals("")) {
      System.out.println("No matches found");
    } else {
      System.out.println(answer.substring(0, (answer.length() - 1)));
    }
  }

  public static void main(String[] args) {
    // create test1 with 10 occurrences of 7 followed by an 8
    DoublyLinkedListDummy test1 = new DoublyLinkedListDummy();
    for (int i = 0; i < 10; i++) {
      test1.addEnd(7);
    }
    test1.addEnd(8);

    // test toString method
    System.out.println("test1: " + test1);

    // test countOccurrence method
    int count7 = test1.countOccurrence(7);
    System.out.println("Number of occurrences of 7 in test1: " + count7);

    // test removeAll method
    boolean removed7 = test1.removeAll(7);
    System.out.println("Removed all occurrences of 7 in test1: " + removed7);
    System.out.println("test1 after removal: " + test1);

    // test removeDuplicates method
    DoublyLinkedListDummy test2 = new DoublyLinkedListDummy();
    test2.addEnd(9);
    test2.addEnd(8);
    test2.addEnd(3);
    test2.addEnd(8);
    test2.addEnd(4);
    test2.addEnd(4);
    test2.addEnd(5);
    test2.addEnd(5);
    System.out.println("test2 before duplicates removed: " + test2);
    DoublyLinkedListDummy purified = test2.removeDuplicates();
    System.out.println("test2 after duplicates removed: " + purified);

    // test pairs method
    DoublyLinkedListDummy test3 = new DoublyLinkedListDummy();
    test3.addEnd(1);
    test3.addEnd(3);
    test3.addEnd(4);
    test3.addEnd(6);
    test3.addEnd(7);
    test3.addEnd(9);
    test3.addEnd(10);
    System.out.println("test3: " + test3);
    System.out.print("Pairs in test3 that sum to 10: ");
    pairs(test3, 10);

  }
}
