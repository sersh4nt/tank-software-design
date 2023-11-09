package ru.mipt.bit.platformer.game.entity.state;

public class HeavyTankState extends TankState {
    public HeavyTankState(float initialSpeed, float initialHealth, float health, float reloadTime) {
        super(initialSpeed, initialHealth, health, reloadTime);
    }

    @Override
    public TankState updateState(float deltaTime) {
        return this;
    }

    @Override
    public boolean isAbleToShoot() {
        return false;
    }

    @Override
    public float getMovementSpeed() {
        return initialSpeed / 3f;
    }

    @Override
    public void shoot() {
    }
}
