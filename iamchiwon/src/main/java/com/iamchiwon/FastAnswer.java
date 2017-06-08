package com.iamchiwon;

import io.reactivex.Observable;

public class FastAnswer {
    static long start = 0;

    public static void main(String[] args) {
        System.out.println("Fast...");

        numSource()
                .doOnSubscribe(dontcare -> start = System.nanoTime())
                .doOnTerminate(() -> System.out.println("TIME : " + (System.nanoTime() - start) + " ns"))
                .reduce((n1, n2) -> n1 > n2 ? n1 : n2)
                .subscribe(max -> {
                    System.out.println("MAX : " + max);
                });
    }

    private static Observable<Long> numSource() {
        return Observable.create(emitter -> {

            for (int b = 0; b < 17; b++) {
                for (int a = 0; a < 20; a++) {
                    //수평으로 연속된 숫자들
                    emitter.onNext(horizental(b, a));
                    //수직으로 연속된 숫자들
                    emitter.onNext(vertical(a, b));
                }

                //오른쪽 대각선으로 연속된 숫자들 (백슬래시)
                for (int a = 0; a < 17; a++) {
                    emitter.onNext(backslash(a, b));
                }

                //왼쪽 대각선으로 연속된 숫자들 (슬래시)
                for (int a = 3; a < 20; a++) {
                    emitter.onNext(slash(a, b));
                }
            }

            emitter.onComplete();
        });
    }

    private static long horizental(int x, int y) {
        int position = y * 20 + x;
        long value = Data.numberTable[position];
        value *= Data.numberTable[position + 1];
        value *= Data.numberTable[position + 2];
        value *= Data.numberTable[position + 3];
        return value;
    }

    private static long vertical(int x, int y) {
        int position = y * 20 + x;
        long value = Data.numberTable[position];
        value *= Data.numberTable[position + 20];
        value *= Data.numberTable[position + 40];
        value *= Data.numberTable[position + 60];
        return value;
    }

    private static long backslash(int x, int y) {
        int position = y * 20 + x;
        long value = Data.numberTable[position];
        value *= Data.numberTable[position + 20 + 1];
        value *= Data.numberTable[position + 40 + 2];
        value *= Data.numberTable[position + 60 + 3];
        return value;
    }

    private static long slash(int x, int y) {
        int position = y * 20 + x;
        long value = Data.numberTable[position];
        value *= Data.numberTable[position + 20 - 1];
        value *= Data.numberTable[position + 40 - 2];
        value *= Data.numberTable[position + 60 - 3];
        return value;
    }
}
