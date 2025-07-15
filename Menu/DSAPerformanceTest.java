package Menu;

import DSA.arrayList;
import DSA.hashmap;
import DSA.hashset;
import DSA.linkedList;
import DSA.stack;
import DSA.priorityqueue;
import DSA.myTreeMap;
import java.time.YearMonth;


public class DSAPerformanceTest {
    public static void run() {
        System.out.println("\n=== 📊 DSA PERFORMANCE TESTS ===");

        testArrayListSort();
        testLinkedList();
        testHashMap();
        testHashSet();
        testStack();
        testPriorityQueue();
        testTreeMap();
    }

    private static void testArrayListSort() {
        arrayList<Integer> al = new arrayList<>();
        for (int i = 1000; i > 0; i--) al.add(i);
        long s = System.nanoTime();
        for (int i = 0; i < al.size()-1; i++)
            for (int j = i+1; j < al.size(); j++)
                if (al.get(i) > al.get(j))
                    al.swap(i, j);
        long e = System.nanoTime();
        System.out.println("arrayList bubble‑sort (1k ints): " + (e-s)/1e6 + " ms");
    }

    private static void testLinkedList() {
        linkedList ll = new linkedList();
        long s = System.nanoTime();
        for (int i = 0; i < 10000; i++) ll.addLast("X"+i);
        long e = System.nanoTime();
        System.out.println("linkedList addLast (10k): " + (e-s)/1e6 + " ms");
    }

    private static void testHashMap() {
        hashmap<String,Integer> hm = new hashmap<>();
        long s1 = System.nanoTime();
        for (int i=0;i<10000;i++) hm.put("key"+i, i);
        long s2 = System.nanoTime();
        for (int i=0;i<10000;i++) hm.get("key"+i);
        long s3 = System.nanoTime();

        System.out.println("hashmap put (10k): " + (s2-s1)/1e6 + " ms");
        System.out.println("hashmap get (10k): " + (s3-s2)/1e6 + " ms");
    }

    private static void testHashSet() {
        hashset hs = new hashset();
        long s = System.nanoTime();
        for (int i=0;i<10000;i++) hs.add("val"+i);
        long e = System.nanoTime();
        System.out.println("hashset add (10k): " + (e-s)/1e6 + " ms");
    }

    private static void testStack() {
        stack<Integer> st = new stack<>();
        long s1 = System.nanoTime();
        for (int i=0;i<10000;i++) st.push(i);
        long s2 = System.nanoTime();
        for (int i=0;i<10000;i++) st.pop();
        long s3 = System.nanoTime();

        System.out.println("stack push (10k): " + (s2-s1)/1e6 + " ms");
        System.out.println("stack pop (10k): " + (s3-s2)/1e6 + " ms");
    }

    private static void testPriorityQueue() {
        priorityqueue<Integer> pq = new priorityqueue<>();
        long s1 = System.nanoTime();
        for (int i=10000;i>0;i--) pq.add(i);
        long s2 = System.nanoTime();
        for (int i=0;i<10000;i++) pq.poll();
        long s3 = System.nanoTime();

        System.out.println("priorityqueue add (10k): " + (s2-s1)/1e6 + " ms");
        System.out.println("priorityqueue poll (10k): " + (s3-s2)/1e6 + " ms");
    }

    private static void testTreeMap() {
        myTreeMap reportMap = new myTreeMap();
        System.out.println("\n📈 Testing TreeMap (Reports by Month)...");

        long start = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            // Generate rotating YearMonth keys
            int year = 2020 + (i % 5);        // 2020 to 2024
            int month = 1 + (i % 12);         // Month 1 to 12
            YearMonth ym = YearMonth.of(year, month);
            reportMap.put(ym, 10.0);
        }
        long end = System.nanoTime();
        System.out.println("⏱ treeMap put/update (10k): " + ((end - start) / 1e6) + " ms");
    }
}
