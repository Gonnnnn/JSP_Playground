package inflearnSpring.springCore.singleton;

public class StatefulService {
//    실무 장애 예시를 위한 임의의 service 파일

    private int price; // 상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; // 여기가 문제!
    }

    public int getPrice() {
        return price;
    }
}
