package com.iamchiwon;

import io.reactivex.Observable;

public class FastAnswer {
    static long start = 0;

    public static void main(String[] args) {
        System.out.println("Fast...");

        numSource()
                .doOnSubscribe(dontcare -> start = System.currentTimeMillis())
                .doOnTerminate(() -> System.out.println("TIME : " + (System.currentTimeMillis() - start) + " ns"))
                .filter(ar -> ar[0] != 0 && ar[1] != 0 && ar[2] != 0 && ar[3] != 0)
                .map(ar -> ar[0] * ar[1] * ar[2] * ar[3])
                .reduce((n1, n2) -> Math.max(n1, n2))
                .subscribe(max -> {
                    System.out.println("MAX : " + max);
                });
    }

    private static Observable<int[]> numSource() {
        return Observable.create(emitter -> {
            int[] array = new int[4];

            for (int y = 0; y < 20; y++) {
                for (int x = 0; x < 20; x++) {
                    //가로
                    array[0] = Data.getNum(x + 0, y);
                    array[1] = Data.getNum(x + 1, y);
                    array[2] = Data.getNum(x + 2, y);
                    array[3] = Data.getNum(x + 3, y);
                    emitter.onNext(array);
                    //세로
                    array[0] = Data.getNum(x, y + 0);
                    array[1] = Data.getNum(x, y + 1);
                    array[2] = Data.getNum(x, y + 2);
                    array[3] = Data.getNum(x, y + 3);
                    emitter.onNext(array);
                    //왼쪽대각선
                    array[0] = Data.getNum(x - 0, y + 0);
                    array[1] = Data.getNum(x - 1, y + 1);
                    array[2] = Data.getNum(x - 2, y + 2);
                    array[3] = Data.getNum(x - 3, y + 3);
                    emitter.onNext(array);
                    //오른쪽대각선
                    array[0] = Data.getNum(x + 0, y + 0);
                    array[1] = Data.getNum(x + 1, y + 1);
                    array[2] = Data.getNum(x + 2, y + 2);
                    array[3] = Data.getNum(x + 3, y + 3);
                    emitter.onNext(array);
                }
            }

            emitter.onComplete();
        });
    }
}
