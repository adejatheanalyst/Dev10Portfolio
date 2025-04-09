package learn;

public class NumberOfSteps {
public static int stepCountNumber(int num) {
    int stepCount = 0;
    while (num > 0){
        int value = 0;
        boolean isEven = num % 2 == 0;
        if(isEven){
            num = num / 2;
            stepCount =  stepCount + 1;
            value = num;
        } else{
            num = num - 1;
            stepCount = stepCount + 1;
            value = num;
        }
    }
    return stepCount;

}



    public static void main(String[] args) {
        int num = 123;
        stepCountNumber(num);
    }
}
