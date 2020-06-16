public class Sandbox {
    public static void main(String[] args) {
        String[] numbers = {"1","2","3","4","5","6","7","8","9"};

        for (String number:numbers){
            if (Integer.parseInt(number) >= 2){
                System.out.println(number);
            }
        }

        System.out.println(new String("3.5").matches("[0-9]+\\.[0-9]*"));
    }
}
