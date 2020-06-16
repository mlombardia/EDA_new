public class BTreeTester {
    public static void main(String[] args) {
        BTree<Integer> st = new BTree<>(1);

        /*for(int rec= 0; rec < 20; rec++)
        {
            st.add(rec);
            System.out.println( st.toString() );

            System.out.println("....");

        }*/
        st.add(0);System.out.println( st.toString() );System.out.println("....");
        st.add(8);System.out.println( st.toString() );System.out.println("....");
        st.add(109);System.out.println( st.toString() );System.out.println("....");
        st.add(220);System.out.println( st.toString() );System.out.println("....");
        st.add(222);System.out.println( st.toString() );System.out.println("....");
        st.add(241);System.out.println( st.toString() );System.out.println("....");
        st.add(149);System.out.println( st.toString() );System.out.println("....");
        st.add(107);System.out.println( st.toString() );System.out.println("....");
        st.add(75);System.out.println( st.toString() );System.out.println("....");
        st.add(248);System.out.println( st.toString() );System.out.println("....");
        st.add(254);System.out.println( st.toString() );System.out.println("....");
        st.add(140);System.out.println( st.toString() );System.out.println("....");
        st.add(16);System.out.println( st.toString() );System.out.println("....");
        st.add(66);System.out.println( st.toString() );System.out.println("....");
        st.add(74);System.out.println( st.toString() );System.out.println("....");
        st.add(21);System.out.println( st.toString() );System.out.println("....");
        st.add(211);System.out.println( st.toString() );System.out.println("....");
        st.add(47);System.out.println( st.toString() );System.out.println("....");
        st.add(80);System.out.println( st.toString() );System.out.println("....");
        st.add(242);System.out.println( st.toString() );System.out.println("....");
        st.remove(66);System.out.println( st.toString() );System.out.println("....");
        st.remove(21);System.out.println( st.toString() );System.out.println("....");
        st.remove(109);System.out.println( st.toString() );System.out.println("....");
        st.remove(241);System.out.println( st.toString() );System.out.println("....");
        st.remove(149);System.out.println( st.toString() );System.out.println("....");
        st.remove(140);System.out.println( st.toString() );System.out.println("....");
        st.remove(211);System.out.println( st.toString() );System.out.println("....");
        st.remove(220);System.out.println( st.toString() );System.out.println("....");
        st.remove(242);System.out.println( st.toString() );System.out.println("....");
    }

}
