package hyunzinbak.core.singleton;

public class StatefulService {

//    private int price; // 상태를 유지하는 필드

//    public void order(String name, int price) {
//        System.out.printf("name = %s price = %d%n", name, price);
//        this.price = price;
//    }

    public int order(String name, int price) {
        System.out.printf("name = %s price = %d%n", name, price);
        return price;
    }

//    public int getPrice() {
//        return price;
//    }
}
