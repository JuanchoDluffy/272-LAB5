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

  public void addEnd(int element) {
    DIntNode addnode = new DIntNode(element);
    tail.getPrev().setNext(addnode);
    addnode.setNext(tail);
    addnode.setPrev(tail.getPrev());
    tail.setPrev(addnode);
  }

  public void removeFromHead() {
    if ((head.getNext()) != tail) {
      head.getNext().getNext().setPrev(head);
      head.setNext(head.getNext().getNext());
    }
  }

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

  public int countOccurrence(int e) {
    int count = 0;
    for (DIntNode curr = getHead(); curr != tail; curr = curr.getNext()) {
      if (curr.getData() == e) {
        count++;
      }
    }

    return count;
  }

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

  public DoublyLinkedListDummy removeDuplicates() {
    HashSet<Integer> occurances = new HashSet<>();
    DoublyLinkedListDummy purified = this;
    for (DIntNode curr = getHead(); curr != tail; curr = curr.getNext()) {
      if (!occurances.contains(curr.getData())) {
        occurances.add(curr.getData());
      } else {
        curr.getPrev().setNext(curr.getNext());
        curr.getNext().setPrev(curr.getPrev());
      }

    }
    return purified;
  }

  public static void pairs(DoublyLinkedListDummy l2, int y) {

  }

  public static void main(String[] args) {
    DoublyLinkedListDummy test1 = new DoublyLinkedListDummy();
    for (int i = 0; i < 10; i++) {
      test1.addEnd(7);
    }
    test1.addEnd(8);
    System.out.println(test1.toString());

    System.out.println(test1.countOccurrence(7));
    test1.removeAll(7);
    System.out.println(test1.toString());

    DoublyLinkedListDummy test2 = new DoublyLinkedListDummy();
    test2.addEnd(9);
    test2.addEnd(8);
    test2.addEnd(3);
    test2.addEnd(8);
    test2.addEnd(4);
    test2.addEnd(4);
    test2.addEnd(5);
    test2.addEnd(5);
    System.out.println("before \n" + test2.toString());

    System.out.println("after \n" + test2.removeDuplicates());

  }
}
