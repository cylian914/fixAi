package fr.tess.ducttape;

import java.util.UUID;

public interface BallExtension {
    int getScore();
    void setScore(int score);
    UUID getPlayer();
}
