package PractiseAutomate;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Practise {
    @Test
    public void SumofNum(){
        Random random = new Random();

        List<Integer> sumlist = new ArrayList<>();
        for(int i = 0; i<10; i++){
            int num =  random.nextInt(10) + 1;
            sumlist.add(num);
        }
        System.out.println(sumlist);

    }

    public Integer sumofList(List<Integer> numList){
           SumofNum();
           return null;
    }
}
