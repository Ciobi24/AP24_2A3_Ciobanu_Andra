//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

package lab1;

/**
 * Ciobanu Andra 2A3
 */
public class Lab1 {
    public static void main(String[] args) {
        Lab1 lab1=new Lab1();
        lab1.compulsory();
    }
    void compulsory(){
        System.out.println("Hello World!");
        String languages[]={"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random() * 1_000_000);
        n=n*3;
        n=n+0b10101;
        n+=0xFF;
        n*=6;
        System.out.println(n);
        int sum=0;
        do {
            sum=0;
            do{
                sum += n % 10;
                n /= 10;
            }while(n>0);
            n=sum;
        }while(n>9);
        System.out.println(n);
        System.out.println("Willy-nilly, this semester I will learn " + languages[n]);
    }
    void homework(){

    }
    void bonus(){

    }
}
