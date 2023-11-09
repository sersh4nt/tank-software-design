package ru.mipt.bit.platformer.game.entity.state;

public abstract class TankState {
    protected final float reloadTime;
    protected final float initialSpeed;
    protected final float initialHealth;
    protected float health;


    public TankState(float initialSpeed, float initialHealth, float health, float reloadTime) {
        this.initialSpeed = initialSpeed;
        this.initialHealth = initialHealth;
        this.health = health;
        this.reloadTime = reloadTime;
    }

    public abstract TankState updateState(float deltaTime);

    public abstract boolean isAbleToShoot();

    public void damage(float damage) {
        health -= damage;
    }

    public abstract float getMovementSpeed();

    public abstract void shoot();

    public float getRelativeHealth() {
        return health / initialHealth;
    }
}
