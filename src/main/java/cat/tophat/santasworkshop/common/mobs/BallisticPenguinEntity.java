package cat.tophat.santasworkshop.common.mobs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class BallisticPenguinEntity extends MonsterEntity implements IAnimatable {

    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.createKey(BallisticPenguinEntity.class, DataSerializers.BOOLEAN);
    private AnimationFactory FACTORY = new AnimationFactory(this);

    private int explosionRadius = 5;

    public BallisticPenguinEntity(EntityType<? extends MonsterEntity> entityType, World world) {
        super(entityType, world);
        experienceValue = 5;
    }

    public static AttributeModifierMap.MutableAttribute createBallisticPenguinAttributes() {
        return MonsterEntity.func_234295_eP_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 15.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.23F)
                .createMutableAttribute(Attributes.ARMOR, 1.0F);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(1, new SwimGoal(this));
        goalSelector.addGoal(2, new AvoidEntityGoal<>(this, PolarBearEntity.class, 6.0F, 0.25F, 0.35F));
        goalSelector.addGoal(2, new AvoidEntityGoal<>(this, CatEntity.class, 6.0F, 0.25F, 0.35F));
        goalSelector.addGoal(3, new MeleeAttackGoal(this, 0.25F, false));
        goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        goalSelector.addGoal(5, new LookRandomlyGoal(this));
        goalSelector.addGoal(5, new RandomWalkingGoal(this, 0.25F));
        goalSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        goalSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, CreeperEntity.class, true));
    }

    public void setAttacking(boolean attacking) {
        dataManager.set(ATTACKING, attacking);
    }

    @Override
    protected void registerData() {
        super.registerData();
        dataManager.register(ATTACKING, false);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving() && !this.dataManager.get(ATTACKING)) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walking", true));
        }

        if (!event.isMoving()) {
            return PlayState.STOP;
        }

        if (event.isMoving() && this.dataManager.get(ATTACKING)) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("launching", false).addAnimation("attacking", true));
        }

        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return FACTORY;
    }
}
