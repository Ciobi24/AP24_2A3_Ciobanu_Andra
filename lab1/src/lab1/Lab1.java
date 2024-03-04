//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

package lab1;

/**
 * Ciobanu Andra 2A3
 */
public class Lab1 {
    public static void main(String[] args) {
        Lab1 lab1 = new Lab1();
        lab1.compulsory();
        if (args.length != 3) {
            System.out.println("Invalid parameters number");
        } else {
            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);
            int k = Integer.parseInt(args[2]);
            lab1.homework(a, b, k);
        }
        int n=5;
        lab1.bonus(n);
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
    void homework(int a, int b, int k){
        long t1 = System.currentTimeMillis();
//        String numbers=new String(); //StringBuilder mai eficient
        StringBuilder numbers = new StringBuilder();
        int stop=1;
        int copy_k=k;
        do {
        stop*=10;
        k/=10;
        } while(k>0);
        k=copy_k;
        for(int i=a;i<=b;i++)
        {
            int copy=i;
            int sum;
            do{
                sum=0;
                do{
                    sum+=(i%10)*(i%10);
                    i/=10;
                }while(i>0);
                i=sum;
            }while(i>=stop);
            if(i==k){
//                numbers=numbers+copy+" ";
                numbers.append(copy);
                numbers.append(" ");
            }
            i=copy;
        }
        System.out.println(k+"-reductible numbers: "+numbers);
        long t2 = System.currentTimeMillis();
        System.out.println("runtime "+ (t2-t1) + "millisec");
    }
    void bonus(int n){
        if(n < 4) {
            System.out.println("A wheel graph requires at least 4 vertices.");
            return;
        }
        int[][] adjacencyMatrix = new int[n][n];
        for(int i = 0; i < n-1; i++) {
            adjacencyMatrix[i][i+1] = 1;
            adjacencyMatrix[i+1][i] = 1;
        }
        adjacencyMatrix[0][n-2] = 1;
        adjacencyMatrix[n-2][0] = 1;
        for(int i = 0; i < n-1; i++) {
            adjacencyMatrix[i][n-1] = 1;
            adjacencyMatrix[n-1][i] = 1;
        }
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
//toate ciclurile contin nodul din centru, mai putin cel exterior
        //luam pe rand ciclurile cu 3 noduri, 4 noduri,... n-1 noduri
        int cycle_count=0;
        for(int i=3;i<=n;i++){
            System.out.println("Cicluri cu "+i+" noduri:");
            if(i==n-1){
                StringBuilder cycle=new StringBuilder();
                for(int j=0;j<=n-2;j++){
                    cycle.append(j);
                    cycle.append(" ");
                }
                cycle.append(0);
                System.out.println(cycle);
                cycle_count++;
            }
            for(int j=0;j<n-1;j++){
                cycle_count++;
                StringBuilder cycle=new StringBuilder();
                cycle.append(n-1);
                cycle.append(" ");
                cycle.append(j);
                cycle.append(" ");
                for(int l=1;l<i-1;l++) {
                    cycle.append((j+l)%(n-1));
                    cycle.append(" ");
                }
                cycle.append(n-1);
                System.out.println(cycle);
            }
        }
        if(cycle_count==n*n-3*n+3)
        {
            System.out.println("Number of cycles is correct: "+cycle_count);
        }
    }

}
