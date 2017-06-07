package com.iamchiwon;

import io.reactivex.Observable;

public class SlowAnswer {
    static long start = 0;

    public static void main(String[] args) {
        System.out.println("Slow...");

        numSource()
                .doOnSubscribe(dontcare -> start = System.nanoTime())
                .doOnTerminate(() -> System.out.println("TIME : " + (System.nanoTime() - start) + " ns"))
                .filter(val -> val != 0)
                .reduce((n1, n2) -> Math.max(n1, n2))
                .subscribe(max -> {
                    System.out.println("MAX : " + max);
                });
    }

    private static Observable<Long> numSource() {
        return Observable.create(emitter -> {
            //수평으로 연속된 숫자들
            for (int y = 0; y < 20; y++) {
                for (int x = 0; x < 17; x++) {
                    emitter.onNext(horizental(x, y));
                }
            }

            //수직으로 연속된 숫자들
            for (int x = 0; x < 20; x++) {
                for (int y = 0; y < 17; y++) {
                    emitter.onNext(vertical(x, y));
                }
            }

            //오른쪽 대각선으로 연속된 숫자들 (백슬래시)
            for (int x = 0; x < 17; x++) {
                for (int y = 0; y < 17; y++) {
                    emitter.onNext(backslash(x, y));
                }
            }

            //왼쪽 대각선으로 연속된 숫자들 (슬래시)
            for (int x = 3; x < 20; x++) {
                for (int y = 0; y < 17; y++) {
                    emitter.onNext(slash(x, y));
                }
            }

            emitter.onComplete();
        });
    }

    private static long horizental(int x, int y) {
        long value = Data.getNum(x, y);
        value *= Data.getNum(x + 1, y);
        value *= Data.getNum(x + 2, y);
        value *= Data.getNum(x + 3, y);
        return value;
    }

    private static long vertical(int x, int y) {
        long value = Data.getNum(x, y);
        value *= Data.getNum(x, y + 1);
        value *= Data.getNum(x, y + 2);
        value *= Data.getNum(x, y + 3);
        return value;
    }

    private static long backslash(int x, int y) {
        long value = Data.getNum(x, y);
        value *= Data.getNum(x + 1, y + 1);
        value *= Data.getNum(x + 2, y + 2);
        value *= Data.getNum(x + 3, y + 3);
        return value;
    }

    private static long slash(int x, int y) {
        long value = Data.getNum(x, y);
        value *= Data.getNum(x - 1, y + 1);
        value *= Data.getNum(x - 2, y + 2);
        value *= Data.getNum(x - 3, y + 3);
        return value;
    }
}
