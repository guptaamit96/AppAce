package inmortal.amit.appace;

public class PlayerState {

    boolean isPaused,isPlaying,isStoped;

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public boolean isStoped() {
        return isStoped;
    }

    public void setStoped(boolean stoped) {
        isStoped = stoped;
    }
}
