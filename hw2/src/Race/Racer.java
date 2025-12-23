package Race;

import java.util.Scanner;

public class Racer implements Runnable {

    static private int globalid = 1;
    private int id;
    private int speed;
    private Track track;

    public Racer(int speed, Track track) {

        this.track = track;
        this.id = globalid++;
        Scanner scanner = new Scanner(System.in);

        while (speed < 1 || speed > 10) {
            System.out.println("Invalid speed for racer " + id + ". Please enter a speed between 1 and 10:");
            speed = scanner.nextInt();
        }
        this.speed = speed;
    }

    @Override
    public void run() {
        go();
    }

   public void go() {
    Thread.currentThread().setPriority(this.speed);

    for (int i = 1; i <= 100; i++) {
        System.out.println("Runner " + id + " ran " + i + " meters");

        if (i == 100) {
            synchronized (track) {
                track.finishedRacers++;          // increment first
                int place = track.finishedRacers;

                if (place == 1) {
                    System.out.println("Runner " + id + " finished 1st!");
                } else if (place == 2) {
                    System.out.println("Runner " + id + " finished 2nd!");
                } else if (place == 3) {
                    System.out.println("Runner " + id + " finished 3rd!");
                } else {
                    System.out.println("Runner " + id + " finished " + place + "th!");
                }
            }
        }
    }
}

}