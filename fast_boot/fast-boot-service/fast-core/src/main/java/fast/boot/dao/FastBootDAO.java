package fast.boot.dao;

import java.util.HashMap;
import java.util.Map;

public class FastBootDAO {
    public static void main(String[] args) {
        Map<String, Long> map = new HashMap<>();
        map.put("a", 1L);

        Long n = map.get("b");
        System.out.println(n);
        System.out.println(n.intValue());
        int a = 123;
    }

    private void longParamCheck(int a, int b, int c, int d,int f,int k){

    }
}
