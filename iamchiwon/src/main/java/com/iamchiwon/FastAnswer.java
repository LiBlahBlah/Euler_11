package com.iamchiwon;

import com.sun.tools.javac.util.List;
import io.reactivex.Observable;

public class FastAnswer {
    public static void main(String[] args) {
        System.out.println("Fast...");

        numSource()
                .map(array -> List.from(array))
                .filter(list -> !list.contains(0))
                .flatMap(list -> Observable.fromArray(list))
                .map(array -> array.stream().reduce((n1, n2) -> n1 * n2).get())
                .reduce((n1, n2) -> Math.max(n1, n2))
                .subscribe(max -> {
                    System.out.println("MAX : " + max);
                });
    }

    private static Observable<Integer[]> numSource() {
        return Observable.create(emitter -> {
            for (int y = 0; y < 20; y++) {
                for (int x = 0; x < 20; x++) {
                    //가로
                    emitter.onNext(new Integer[]{
                            Data.getNum(x + 0, y),
                            Data.getNum(x + 1, y),
                            Data.getNum(x + 2, y),
                            Data.getNum(x + 3, y)
                    });
                    //세로
                    emitter.onNext(new Integer[]{
                            Data.getNum(x, y + 0),
                            Data.getNum(x, y + 1),
                            Data.getNum(x, y + 2),
                            Data.getNum(x, y + 3)
                    });
                    //왼쪽대각선
                    emitter.onNext(new Integer[]{
                            Data.getNum(x - 0, y + 0),
                            Data.getNum(x - 1, y + 1),
                            Data.getNum(x - 2, y + 2),
                            Data.getNum(x - 3, y + 3)
                    });
                    //오른쪽대각선
                    emitter.onNext(new Integer[]{
                            Data.getNum(x + 0, y + 0),
                            Data.getNum(x + 1, y + 1),
                            Data.getNum(x + 2, y + 2),
                            Data.getNum(x + 3, y + 3)
                    });
                }
            }

            emitter.onComplete();
        });
    }
}
