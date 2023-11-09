package ru.mipt.bit.platformer.game.entity.state;

public class LightTankState extends TankState {
    private float lastShootedAt = 0f;

    public LightTankState(float initialSpeed, float initialHealth, float health, float reloadTime) {
        super(initialSpeed, initialHealth, health, reloadTime);
    }

    @Override
    public TankState updateState(float deltaTime) {
        lastShootedAt -= deltaTime;

        if (getRelativeHealth() < 0.15f) {
            return new HeavyTankState(initialSpeed, initialHealth, health, reloadTime);
        }

        if (getRelativeHealth() < 0.7f) {
            return new MiddleTankState(initialSpeed, initialHealth, health, reloadTime);
        }

        return this;
    }

    @Override
    public boolean isAbleToShoot() {
        return lastShootedAt <= 0;
    }


    @Override
    public float getMovementSpeed() {
        return initialSpeed;
    }

    @Override
    public void shoot() {
        lastShootedAt = reloadTime;
    }
}
