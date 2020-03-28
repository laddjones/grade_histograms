import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class GradeHistogram {

    public static void main(String [] args) throws IOException {
        String theirInput = args[0];
        int bucketSize;
        if (args.length == 1) {
            System.out.println("enter bucket size");
            Scanner input = new Scanner(System.in);
            bucketSize = input.nextInt();
        } else {
            bucketSize = Integer.parseInt(args[1]);
        }
        Scanner scan = new Scanner(new File(theirInput));

        int [] gradeFreq = new int[101];

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String [] grades = line.split(",");
            gradeFreq[Integer.parseInt(grades[1].trim())]++;
        }

        int numBuckets = (101/bucketSize) + 1;
        int [] frequencyVis = new int[numBuckets];

        for(int i = 0; i <= 100; i++) {
            int index = (100-i)/bucketSize;
            frequencyVis[index] += gradeFreq[i];
        }

        int lower = 0;
        int upper = 1;

        for(int i = 100; i >= 0; i-=bucketSize) {
            String firstNum = "" +i;
            String secondNum = "" + Math.max(0,(i - bucketSize + 1));
            System.out.printf("%3s - %2s | " , firstNum, secondNum);
            while (lower < upper && lower != numBuckets+1) {
                int numBrackets = frequencyVis[lower];
                lower++;
                if(numBrackets > 0) {
                    for(int t = 0; t < numBrackets; t++) {
                       System.out.print("[]");
                    }
                }
            }
            upper++;
            System.out.println();
        }

    }

}
