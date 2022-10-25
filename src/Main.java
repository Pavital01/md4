import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Main {

    static String message = "ElectronicaDigi!";
    static String secretKey = "PV";

    //sum modulo 2 ^ 32
    static int sumMod32(int a,int b){
        return (int)((a + b) % Math.pow(2,32));
    }

    static void outMessage(ArrayList<Integer> stack){
        int forBoolAnd = (int)Math.pow(2,8) - 1;
        System.out.println(Integer.toBinaryString(forBoolAnd));
        int k = 0,temp;
        int[] arrInt = new int[16];
        if (stack.size() > 4){
            System.out.println("Error of size");
            return;
        }
        else
        for (int i :stack) {
            int valueOfStack = stack.get(k);
            System.out.println("==> I = " + i + "\n" + Integer.toBinaryString(valueOfStack));
            for (int j = 3; j >= 0; j--) {
                System.out.println("\t==> J = " + j);
                temp = forBoolAnd & valueOfStack;
                System.out.println("\t\ttemp = " + Integer.toBinaryString(temp));
                valueOfStack >>= 8;
                arrInt[4 * k + j] = temp;
            }
            k++;
        }
        for (int i :arrInt) {
            System.out.println(Integer.toBinaryString(i));
        }
    }
    public static void main(String[] args) {
        byte[] byteMessage = message.getBytes();
        int A = (byteMessage[0] << (3 * 8)) + (byteMessage[1] << (2 * 8)) + (byteMessage[2] << 8 ) + byteMessage[3];
        System.out.println("A = " + Integer.toBinaryString(A));

        int B = (byteMessage[4] << (3 * 8)) + (byteMessage[5] << (2 * 8)) + (byteMessage[6] << 8 ) + byteMessage[7];
        System.out.println("B = " + Integer.toBinaryString(B));

        int C = (byteMessage[8] << (3 * 8)) + (byteMessage[9] << (2 * 8)) + (byteMessage[10] << 8 ) + byteMessage[11];
        System.out.println("C = " + Integer.toBinaryString(C));

        int D = (byteMessage[12] << (3 * 8)) + (byteMessage[13] << (2 * 8)) + (byteMessage[14] << 8 ) + byteMessage[15];
        System.out.println("D = " + Integer.toBinaryString(D));

        int F;
        Integer temp;
        ArrayList<Integer> stack = new ArrayList<>();
        stack.add(A);
        stack.add(B);
        stack.add(C);
        stack.add(D);
        for (int i = 0; i < 16; i++) {
            System.out.println("\n==> I = " + i);

            F = stack.get(1) ^ stack.get(2) ^ stack.get(3);
            System.out.println("\t1) F = " + Integer.toBinaryString(F));

            temp = sumMod32(stack.get(0),F);
            System.out.println("\t2) (A + F) mod 2 ^ 32 = " + Integer.toBinaryString(temp));

            temp = sumMod32(temp,i);
            System.out.println("\t3)P(2) + Mi mod 2 ^ 32 = " + Integer.toBinaryString(temp));

            switch (i % 4){
                case (0) : temp = Integer.rotateLeft(temp,3);break;
                case (1) : temp = Integer.rotateLeft(temp,7);break;
                case (2) : temp = Integer.rotateLeft(temp,11);break;
                case (3) : temp = Integer.rotateLeft(temp,19);break;
                default : {
                    System.out.println("Error");
                    return;
                }
            }
            System.out.println("\t4) = " + Integer.toBinaryString(temp));
            stack.set(0,stack.get(3));//D=>A
            stack.set(3,stack.get(2));//C=>D
            stack.set(2,stack.get(1));//B=>C
            stack.set(1,temp);//A=>B
        }
        for (int i :stack) {
            System.out.println(Integer.toBinaryString(i));
        }
        outMessage(stack);
    }
}
