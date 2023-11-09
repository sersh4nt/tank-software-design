package ru.mipt.bit.platformer.game.entity.state;

public class MiddleTankState extends TankState {
    private float lastShootedAt = 0f;

    public MiddleTankState(float initialSpeed, float initialHealth, float health, float reloadTime) {
        super(initialSpeed, initialHealth, health, reloadTime);
    }

    @Override
    public TankState updateState(float deltaTime) {
        lastShootedAt -= deltaTime;

        if (getRelativeHealth() < 0.15f) {
            return new HeavyTankState(initialSpeed, initialHealth, health, reloadTime);
        }

        return this;
    }

    @Override
    public boolean isAbleToShoot() {
        return lastShootedAt <= 0;
    }

    @Override
    public float getMovementSpeed() {
        return initialSpeed / 2f;
    }

    @Override
    public void shoot() {
        lastShootedAt = reloadTime;
    }
}
