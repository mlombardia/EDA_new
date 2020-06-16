public class QueueTester {
    public static void main(String args[]){
        Queue<Integer> queue = new Queue<>();
        queue.add(70);
        queue.add(100);
        queue.add(500);
        queue.add(40);
        queue.dump();
        System.out.println(queue.size());

        queue.remove();
        queue.dump();
        System.out.println(queue.size());

        queue.add(200);
        queue.dump();
        System.out.println(queue.size());

        //queue.setLimit(2);

        for (Integer num : queue)
            System.out.println(num);
    }
}
