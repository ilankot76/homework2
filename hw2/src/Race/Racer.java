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
        scanner.close();
    }

    @Override
    public void run() {
        go();
    }

    public synchronized void go() {
        Thread.currentThread().setPriority(speed);

        for (int i = 0; i <= 100; i++) {
            System.out.println("Runner " + id + " ran " + i + " meters");
        }
        switch (track.finishedRacers) {
            case 1:
                System.out.println("Runner " + id + " finished 1st!");
                track.finishedRacers++;
                break;
            case 2:
                System.out.println("Runner " + id + " finished 2nd!");
                track.finishedRacers++;
                break;
            case 3:
                System.out.println("Runner " + id + " finished 3rd!");
                track.finishedRacers++;
                break;

            default:
                System.out.println("Runner " + id + " finished " + (track.finishedRacers + 1) + "th!");
                track.finishedRacers++;
                break;
        }
    }
}