package darktestcase;

import minimal.TestUtil;

/**
 * Description:
 *
 * @author xxz
 * Created on 07/01/2020
 */
public class DarkMNodeTest {
    class MNode{
        public int value;
        public MNode next;

        public MNode(int value) {
            this.value = value;
        }
    }

    public int runTest(int a1, int a2, int a3, int a4, int a5, int get) {
        MNode mNode = new MNode(a1);
        MNode head = mNode;
        MNode node = null;
        node = new MNode(a2);
        mNode.next = node;
        mNode = new MNode(a3);
        node.next = mNode;
        node = new MNode(a4);
        mNode.next = node;
        mNode = new MNode(a5);
        node.next = mNode;
        mNode.next = head;
        mNode = head;
        for (int i = 0; i < get; i++) {
            mNode = mNode.next;
        }
        return mNode.value;
    }

    public static void main(String[] args) {
        DarkMNodeTest darkMNodeTest = new DarkMNodeTest();
        int i = darkMNodeTest.runTest(1, 2, 3, 4, 5, 6);
        TestUtil.equalInt(i, 2);
    }
}
