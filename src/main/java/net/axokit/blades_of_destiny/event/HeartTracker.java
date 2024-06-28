package net.axokit.blades_of_destiny.event;

import java.util.ArrayList;
import java.util.List;

public class HeartTracker {
    private final List<HeartData> hearts;

    public HeartTracker() {
        this.hearts = new ArrayList<>();
    }

    public void updateHeartPosition(int index, int x, int y, boolean isCursed) {
        if (index < hearts.size()) {
            hearts.get(index).setPosition(x, y, isCursed);
        } else {
            hearts.add(new HeartData(x, y, isCursed));
        }
    }

    public List<HeartData> getHearts() {
        return hearts;
    }

    public static class HeartData {
        private int x;
        private int y;
        private boolean isCursed;

        public HeartData(int x, int y, boolean isCursed) {
            this.x = x;
            this.y = y;
            this.isCursed = isCursed;
        }

        public void setPosition(int x, int y, boolean isCursed) {
            this.x = x;
            this.y = y;
            this.isCursed = isCursed;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public boolean isCursed() {
            return isCursed;
        }

        @Override
        public String toString() {
            return "HeartData{" +
                    "x=" + x +
                    ", y=" + y +
                    ", isCursed=" + isCursed +
                    '}';
        }
    }
}