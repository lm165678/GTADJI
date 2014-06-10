/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuliy.example;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 *
 */
public class Player {

    private Rectangle bounds;
    private Vector2 velocity;
    float time = 0;
    private TextureRegion frame = Assets.stand;
    private int lastRot;
    private boolean punch = false;

    public Player(float x, float y) {
        bounds = new Rectangle(x, y, Assets.stand.getRegionWidth(), Assets.stand.getRegionHeight());
        velocity = new Vector2(0, 0);
    }

    public void update(float deltaTime) {
        bounds.height = frame.getRegionHeight();
        bounds.width = frame.getRegionWidth();
        bounds.x += velocity.x * deltaTime;
        bounds.y += velocity.y * deltaTime;
        if (velocity.x != 0 || velocity.y != 0 || punch == true) {
            time += deltaTime;
        } else {
            time = 0;
        }
    }

    public void mouseAngle(double x, double y) {
        lastRot = (int) (Math.toDegrees(Math.atan2(Math.abs(bounds.y - y), Math.abs(bounds.x - x))));
        if (bounds.y > y && bounds.x > x) {
            lastRot += 270;
        } else if (bounds.y > y && bounds.x < x) {
            lastRot = 90 - lastRot;
        } else if (bounds.y < y && bounds.x < x) {
            lastRot += 90;
        } else if (bounds.y < y && bounds.x > x) {
            lastRot = 270 - lastRot;
        }
    }

    public void draw(SpriteBatch batch) {
        if (punch == false) {
            if (velocity.x > 0 && velocity.y == 0) {
                frame = Assets.runRight
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, 90, true);
                lastRot = 90;
            } else if (velocity.x < 0 && velocity.y == 0) {
                frame = Assets.runRight
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, 270, true);
                lastRot = 270;
            } else if (velocity.x == 0 && velocity.y > 0) {
                frame = Assets.runRight
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, 180, true);
                lastRot = 180;
            } else if (velocity.x > 0 && velocity.y > 0 && velocity.x == velocity.y) {
                frame = Assets.runRight
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, 135, true);
                lastRot = 135;
            } else if (velocity.x < 0 && velocity.y > 0 && -velocity.x == velocity.y) {
                frame = Assets.runRight
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, 225, true);
                lastRot = 225;
            } else if (velocity.x < 0 && velocity.y < 0 && velocity.x == velocity.y) {
                frame = Assets.runRight
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, 315, true);
                lastRot = 315;
            } else if (velocity.x == 0 && velocity.y < 0) {
                frame = Assets.runRight
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, 0, true);
                lastRot = 0;
            } else if (velocity.x > 0 && velocity.y < 0 && velocity.x == -velocity.y) {
                frame = Assets.runRight
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, 45, true);
                lastRot = 45;
            } else {
                frame = Assets.stand;
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, lastRot, true);
            }
        } else if (punch == true) {
            if (lastRot == 90) {
                frame = Assets.playerPunch90
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y);
            } else if (lastRot == 180) {
                frame = Assets.playerPunch180
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y);
            } else if (lastRot == 270) {
                frame = Assets.playerPunch270
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y);
            } else if (lastRot == 360 || lastRot == 0) {
                frame = Assets.playerPunch360
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y);
            }
        }
    }

    public void angleRound() {
        if (velocity.x == 0 && velocity.y == 0) {
            if (lastRot > 0 && lastRot < 90 && lastRot <= 45) {
                lastRot = 0;
            } else if (lastRot > 0 && lastRot < 90 && lastRot > 45) {
                lastRot = 90;
            } else if (lastRot > 90 && lastRot < 180 && lastRot <= 135) {
                lastRot = 90;
            } else if (lastRot > 90 && lastRot < 180 && lastRot > 135) {
                lastRot = 180;
            } else if (lastRot > 180 && lastRot < 270 && lastRot <= 225) {
                lastRot = 180;
            } else if (lastRot > 180 && lastRot < 270 && lastRot > 225) {
                lastRot = 270;
            } else if (lastRot > 270 && lastRot < 360 && lastRot <= 315) {
                lastRot = 270;
            } else if (lastRot > 270 && lastRot < 360 && lastRot > 315) {
                lastRot = 360;
            }
        }else {
            if (velocity.x > 0 && velocity.y == 0) {
                lastRot = 90;
            } else if (velocity.x < 0 && velocity.y == 0) {                
                lastRot = 270;
            } else if (velocity.x == 0 && velocity.y > 0) {                
                lastRot = 180;
            } else if (velocity.x > 0 && velocity.y > 0 && velocity.x == velocity.y) {                
                lastRot = 135;
            } else if (velocity.x < 0 && velocity.y > 0 && -velocity.x == velocity.y) {                
                lastRot = 225;
            } else if (velocity.x < 0 && velocity.y < 0 && velocity.x == velocity.y) {                
                lastRot = 315;
            } else if (velocity.x == 0 && velocity.y < 0) {                
                lastRot = 0;
            }
        }
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public float getX() {
        return bounds.x;
    }

    public float getY() {
        return bounds.y;
    }

    public float getHeight() {
        return bounds.height;
    }

    public float getWidth() {
        return bounds.width;
    }

    public float updateBoundsHeight() {
        return frame.getRegionHeight();
    }

    public float updateBoundsWidth() {
        return frame.getRegionWidth();
    }

    public void setVelocity(float dx, float dy) {
        velocity.x = dx;
        velocity.y = dy;
    }

    public void setDX(float dx) {
        velocity.x = dx;
    }

    public void setDY(float dy) {
        velocity.y = dy;
    }

    public void setX(float x) {
        bounds.x = x;
    }

    public void setY(float y) {
        bounds.y = y;
    }

    public void punch() {
        punch = true;
    }

    public void stopPunch() {
        punch = false;
    }

    //Ix = max (Ax,Bx)
    //Iy = max (Ay,By)
    //Iw = min (Ax+Aw, Bx+Bw) - x
    //Ih = min (Ay+Ah,Ay+Bh) - y
    public void handleCollision(Rectangle c) {

        float x = Math.max(bounds.x, c.x);
        float y = Math.max(bounds.y, c.y);
        float width = Math.min(bounds.x + bounds.width, c.x + c.width) - x;
        float height = Math.min(bounds.y + bounds.height, c.y + c.height) - y;
        if (width < height) {
            if (bounds.x < c.x) {
                bounds.x = bounds.x - width;
            } else {
                bounds.x = bounds.x + width;
            }
        } else {
            if (bounds.y > c.y) {
                bounds.y = bounds.y + height;
            } else {
                bounds.y = bounds.y - height;
            }
        }
    }
}