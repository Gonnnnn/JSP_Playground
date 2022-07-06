package inflearnSpring.springCore.singleton;

public class SingletonService {
//    test case 작성은 아니고 그냥 시험해보는 class이다.

//    static 키워드! class 레벨에 올라가기 때문에 딱 하나만 만들어져 올라간다.
    private static final SingletonService instance = new SingletonService();

//    얘를 조회하는 함수만 만들어놓는 것이다!!!
    public static SingletonService getInstance() {
        return instance;
    }

//    private 생성자를 만들어 밖에서 new 키워드로 생성하지 못하게 막아버린다!
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 로직 호출됨");
    } 
}